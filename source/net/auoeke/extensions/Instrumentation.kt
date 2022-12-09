@file:Suppress("NOTHING_TO_INLINE", "unused")

package net.auoeke.extensions

import java.lang.instrument.*
import java.security.*

inline fun Instrumentation.transform(transformer: ClassTransformer) = this.addTransformer(transformer)

inline fun Instrumentation.pretransform(crossinline transformer: (Module, ClassLoader?, String, ProtectionDomain?, ByteArray) -> ByteArray) = this.transform {module, loader, name, type, domain, bytecode ->
	when (type) {
		null -> transformer(module, loader, name, domain, bytecode)
		else -> bytecode
	}
}

inline fun Instrumentation.retransform(transformer: ClassTransformer) = this.transform {module, loader, name, type, domain, bytecode ->
	when (type) {
		null -> bytecode
		else -> transformer.transform(module, loader, name, type, domain, bytecode)
	}
}
