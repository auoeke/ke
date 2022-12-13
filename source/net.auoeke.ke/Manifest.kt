@file:Suppress("NOTHING_TO_INLINE", "unused")

package net.auoeke.ke

import java.util.jar.Attributes
import java.util.jar.Manifest

inline val Manifest.specificationVersion: String? get() = this.mainAttributes("Specification-Version")
inline val Manifest.implementationVersion: String? get() = this.mainAttributes("Implementation-Version")

inline operator fun Attributes.invoke(name: String): String? = this.getValue(name)
