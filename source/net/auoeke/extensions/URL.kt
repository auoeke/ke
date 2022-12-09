@file:Suppress("unused")

package net.auoeke.extensions

import java.io.*
import java.net.*
import java.nio.file.*

inline val URL.exists: Boolean get() = this.asPath.exists
inline val URL.asPath: Path get() = this.asURI.asPath
inline val URL.asFile: File get() = this.asURI.asFile
inline val URL.asURI: URI get() = this.toURI()
