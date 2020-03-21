package com.kasianov.sergei.omaloma.core.extentions

interface Mapper<I, O> {
    fun mapDto(input: I): O
}

// Non-nullable to non-nullable
interface ListMapper<I, O> : Mapper<List<I>, List<O>>

class ListMapperImpl<I, O>(
    private val mapper: Mapper<I, O>
) : ListMapper<I, O> {
    override fun mapDto(input: List<I>): List<O> {
        return input.map { mapper.mapDto(it) }
    }
}

// Nullable to non-nullable
interface NullableInputListMapper<I, O> : Mapper<List<I>?, List<O>>

class NullableInputListMapperImpl<I, O>(
    private val mapper: Mapper<I, O>
) : NullableInputListMapper<I, O> {
    override fun mapDto(input: List<I>?): List<O> {
        return input?.map { mapper.mapDto(it) }.orEmpty()
    }
}

// Non-nullable to nullable
interface NullableOutputListMapper<I, O>: Mapper<List<I>, List<O>?>

class NullableOutputListMapperImpl<I, O>(
    private val mapper: Mapper<I, O>
) : NullableOutputListMapper<I, O> {
    override fun mapDto(input: List<I>): List<O>? {
        return if (input.isEmpty()) null else input.map { mapper.mapDto(it) }
    }
}

