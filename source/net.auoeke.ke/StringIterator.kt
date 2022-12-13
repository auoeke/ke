package net.auoeke.ke

class StringIterator(private val sequence: CharSequence, var index: Int = 0) : ListIterator<Char> {
	private val length: Int = this.sequence.length

	override fun hasNext(): Boolean = this.index < this.length

	override fun hasPrevious(): Boolean = this.index > 0

	override fun next(): Char = this.sequence[this.index++]

	override fun nextIndex(): Int = this.index

	override fun previous(): Char = this.sequence[--this.index]

	override fun previousIndex(): Int = this.index - 1
}
