package com.example.neowsapp.data.mapper

abstract class DataMapper<T, V> {
    abstract fun transform(target: T): V
    abstract fun transform(target: List<T>): List<V>
}