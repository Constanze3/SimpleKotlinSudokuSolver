package org.example

/**
 * Cell with a 2-dimensional position and some or no value.
 */
open class Cell<T>(val position: Position2D, var value: T?) {
    constructor(x: Int, y: Int, value: T?) : this(Position2D(x, y), value)
}