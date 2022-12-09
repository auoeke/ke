@file:Suppress("unused")

package net.auoeke.extensions

inline val String.capitalized: String get() = this.replaceFirstChar(Char::uppercaseChar)
inline val String.slashed: String get() = this.replace('.', '/')
inline val String.dotted: String get() = this.replace('/', '.')
