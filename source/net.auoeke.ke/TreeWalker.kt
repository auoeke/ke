package net.auoeke.ke

import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

class TreeWalker(val action: (Path) -> Unit) : SimpleFileVisitor<Path>() {
	override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult = super.preVisitDirectory(dir, attrs).also {
		this.action(dir)
	}

	override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult = super.visitFile(file, attrs).also {
		this.action(file)
	}
}
