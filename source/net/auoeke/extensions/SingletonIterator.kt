package net.auoeke.extensions

class SingletonIterator<T>(private val element: T) : Iterator<T> {
    private var done: Boolean = false

    override fun hasNext(): Boolean = !this.done

    override fun next(): T = this.element.also {this.done = true}
}
