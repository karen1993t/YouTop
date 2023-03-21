package com.youtop.core.mapper

interface Mappable<T> {
    fun map(): T
}

interface MappableFrom<I, O> {
    fun mapFrom(input: I): O
}
