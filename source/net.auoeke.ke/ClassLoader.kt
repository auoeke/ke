@file:Suppress("NOTHING_TO_INLINE", "unused")

package net.auoeke.ke

import java.io.InputStream
import java.nio.file.Path

inline fun ClassLoader.resource(name: String): Path? = this.getResource(name)?.asPath
inline fun ClassLoader.resourceStream(name: String): InputStream? = this.getResourceAsStream(name)
