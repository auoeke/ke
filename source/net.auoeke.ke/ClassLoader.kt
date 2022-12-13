@file:Suppress("NOTHING_TO_INLINE", "unused")

package net.auoeke.ke

import java.io.*
import java.nio.file.*

inline fun ClassLoader.resource(name: String): Path? = this.getResource(name)?.asPath
inline fun ClassLoader.resourceStream(name: String): InputStream? = this.getResourceAsStream(name)
