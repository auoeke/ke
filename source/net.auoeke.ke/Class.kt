@file:Suppress("NOTHING_TO_INLINE", "unused")

package net.auoeke.ke

import java.io.InputStream
import java.nio.file.Path
import java.security.CodeSource
import java.util.*
import java.util.jar.Manifest
import kotlin.io.path.inputStream
import kotlin.reflect.KClass

inline val Class<*>.loader: ClassLoader? get() = this.classLoader
inline val Class<*>.hierarchy: List<Class<*>> get() = this.hierarchy(null)
inline val Class<*>.codeSource: CodeSource? get() = this.protectionDomain?.codeSource
inline val Class<*>.path: Path? get() = this.resource("/${this.name.slashed}.class")
inline val Class<*>.source: Path? get() = this.path?.ascend(this.name.count('.') + 1)
inline val Class<*>.manifest: Manifest get() = Manifest(this.localResource("/META-INF/MANIFEST.MF")!!.inputStream())

inline fun Class<*>.resource(name: String): Path? = this.getResource(name)?.asPath
inline fun Class<*>.resourceStream(name: String): InputStream? = this.getResourceAsStream(name)
inline fun Class<*>.localResource(name: String): Path? = this.path?.resolve(name)
inline fun Class<*>.properties(name: String): Properties = Properties(this.resource(name)!!)

fun Class<*>.hierarchy(limit: Class<*>?): List<Class<*>> = ArrayList<Class<*>>().also {
	var type: Class<*>? = this

	while (type !== limit) {
		it += type!!.apply {type = this.superclass}
	}
}

fun Class<*>.hierarchy(excludeObject: Boolean) = this.hierarchy(when {
	excludeObject -> null
	else -> type<Any>()
})

inline fun <reified T> Class<*>.hierarchy() = this.hierarchy(type<T>())

inline val KClass<*>.superclass: Class<*> get() = this.java.superclass
inline val KClass<*>.loader: ClassLoader? get() = this.java.loader
inline val KClass<*>.hierarchy: List<Class<*>> get() = this.java.hierarchy
inline val KClass<*>.codeSource: CodeSource? get() = this.java.codeSource
inline val KClass<*>.path: Path? get() = this.java.path
inline val KClass<*>.source: Path? get() = this.java.source

inline fun KClass<*>.resource(name: String): Path? = this.java.resource(name)
inline fun KClass<*>.resourceStream(name: String): InputStream? = this.java.resourceStream(name)
inline fun KClass<*>.localResource(path: String): Path? = this.java.localResource(path)
