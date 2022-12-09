@file:Suppress("NOTHING_TO_INLINE", "unused")

package net.auoeke.extensions

import java.io.*
import java.net.*
import java.nio.file.*

inline val File.exists: Boolean get() = this.exists()
inline val File.asPath: Path get() = this.toPath()
inline val File.asURI: URI get() = this.toURI()
inline val File.asURL: URL get() = this.asURI.asURL

inline operator fun File.div(relative: File) = this.resolve(relative)
inline operator fun File.div(relative: String) = this.resolve(relative)
