@file:Suppress("NOTHING_TO_INLINE", "unused")

package net.auoeke.ke

import java.io.*
import java.net.*
import java.nio.file.*
import java.nio.file.attribute.*
import java.time.*
import kotlin.io.path.*

inline val Path.exists: Boolean get() = Files.exists(this)
inline val Path.asFile: File get() = this.toFile()
inline val Path.asURI: URI get() = this.toUri()
inline val Path.asURL: URL get() = this.asURI.toURL()
inline val Path.newFilesystem: FileSystem get() = FileSystems.newFileSystem(this)
inline val Path.attributes: BasicFileAttributes get() = this.readAttributes()
inline val Path.mtime: FileTime get() = this.getLastModifiedTime()
inline val Path.real: Path get() = this.mapIf(this.exists) {this.toRealPath()}

inline fun Path.mtime(vararg options: LinkOption): FileTime = this.getLastModifiedTime(*options)
inline fun Path.updateMtime() = this.setLastModifiedTime(FileTime.from(Instant.now()))
inline fun Path.copy(destination: Path, vararg options: CopyOption): Path = Files.copy(this, destination, *options)
inline fun Path.copy(destination: OutputStream): Long = Files.copy(this, destination)
inline fun Path.list(glob: String): List<Path> = this.listDirectoryEntries(glob)

inline fun Path.list(recurse: Boolean = false): List<Path> = when {
    recurse -> ArrayList<Path>().apply {walk {this.add(it)}}
    else -> Files.list(this).toList()
}

inline fun Path.listFiles(recurse: Boolean = false): List<Path> = when {
    recurse -> ArrayList<Path>().apply {walkFiles(this::add)}
    else -> Files.list(this).filter {!it.isDirectory()}.toList()
}

inline fun Path.delete(recurse: Boolean = false): Path = when {
    recurse -> this.walk(TreeDeleter)
    else -> this.apply {this.deleteExisting()}
}

inline fun Path.tryDelete(recurse: Boolean = false): Path = this.apply {
	if (this.exists) {
		this.delete(recurse)
	}
}

inline fun Path.move(destination: Path, vararg options: CopyOption): Path = Files.move(this, destination, *options)
inline fun Path.write(contents: String, vararg options: OpenOption): Path = Files.writeString(this, contents, *options)
inline fun Path.write(contents: ByteArray, vararg options: OpenOption): Path = Files.write(this, contents, *options)
inline fun Path.mkdirs(vararg attributes: FileAttribute<*>): Path = Files.createDirectories(this, *attributes)
inline fun Path.walk(visitor: FileVisitor<Path>, depth: Int = Int.MAX_VALUE, options: Set<FileVisitOption>): Path = Files.walkFileTree(this, options, depth, visitor)
inline fun Path.walk(visitor: FileVisitor<Path>, depth: Int = Int.MAX_VALUE, vararg options: FileVisitOption): Path = this.walk(visitor, depth, options.toSet())
inline fun Path.walk(visitor: FileVisitor<Path>, vararg options: FileVisitOption): Path = this.walk(visitor, Int.MAX_VALUE, *options)
inline fun Path.walk(vararg options: FileVisitOption, noinline action: (Path) -> Unit): Path = Files.walkFileTree(this, options.toSet(), Int.MAX_VALUE, TreeWalker(action))
inline fun Path.walkFiles(noinline action: (Path) -> Unit): Path = Files.walkFileTree(this, FileWalker(action))
inline fun Path.walkFiles(vararg options: FileVisitOption, noinline action: (Path) -> Unit): Path = Files.walkFileTree(this, options.toSet(), Int.MAX_VALUE, FileWalker(action))
inline fun Path.newFilesystem(loader: ClassLoader? = null): FileSystem = FileSystems.newFileSystem(this, loader)
inline fun Path.newFilesystem(env: Map<String, *>, loader: ClassLoader? = null): FileSystem = FileSystems.newFileSystem(this, env, loader)
inline fun Path.same(other: Path): Boolean = this.exists && other.exists && this.isSameFileAs(other)
inline fun Path.parent(level: Int): Path = this.root.mapIf(level > 0) {this.resolve(this.subpath(0, level))}
inline fun Path.ascend(levels: Int): Path = this.parent(this.nameCount - levels)
