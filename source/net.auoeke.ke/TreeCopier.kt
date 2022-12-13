package net.auoeke.ke

import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes

open class TreeCopier(private val fromRoot: Path, private val toRoot: Path) : SimpleFileVisitor<Path>() {
    override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
        Files.createDirectories(this.newPath(dir))

        return FileVisitResult.CONTINUE
    }

    override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
        file.copy(this.newPath(file), StandardCopyOption.REPLACE_EXISTING)

        return FileVisitResult.CONTINUE
    }

    protected fun newPath(path: Path): Path = this.toRoot.resolve(this.fromRoot.relativize(path))
}
