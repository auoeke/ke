package net.auoeke.extensions

class ArrayIterator<T>(private val array: Array<T>, private var index: Int = 0) : MutableListIterator<T> {
    private val size: Int = this.array.size

    override fun hasPrevious(): Boolean = this.index > 0

    override fun nextIndex(): Int = this.index

    override fun previous(): T = this.array[--this.index]

    override fun previousIndex(): Int = this.index - 1

    override fun add(element: T) = throw UnsupportedOperationException()

    override fun hasNext(): Boolean = this.index < this.size

    override fun next(): T = this.array[this.index++]

    override fun remove() = throw UnsupportedOperationException()

    override fun set(element: T) {
        this.array[this.index] = element
    }
}
