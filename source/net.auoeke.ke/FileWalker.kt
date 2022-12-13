package net.auoeke.ke

import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

class FileWalker(private val action: (Path) -> Unit) : SimpleFileVisitor<Path>() {
	override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult = super.visitFile(file, attrs).also {
		this.action(file)
	}
}
