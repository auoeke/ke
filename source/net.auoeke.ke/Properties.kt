@file:Suppress("NOTHING_TO_INLINE", "unused", "UNCHECKED_CAST")

package net.auoeke.ke

import java.io.*
import java.nio.file.*
import java.util.*
import kotlin.io.path.*

inline fun Properties(stream: InputStream): Properties = Properties().apply {this.load(stream)}
inline fun Properties(path: Path): Properties = Properties(path.inputStream())

inline operator fun Properties.invoke(name: String): String? = this.getProperty(name)

inline fun Properties.each(action: (Map.Entry<String, String>) -> Unit) = this.forEach {action(it as Map.Entry<String, String>)}
inline fun Properties.each(action: (String, String) -> Unit) = this.withEach {action(this.key, this.value)}
inline fun Properties.withEach(action: Map.Entry<String, String>.() -> Unit) = this.each(action)
