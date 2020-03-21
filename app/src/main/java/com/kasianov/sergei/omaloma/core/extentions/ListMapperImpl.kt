package com.kasianov.sergei.omaloma.core.extentions

open class ListMapperImpl<I, O>(
    private val mapper: Mapper<I, O>
) : ListMapper<I, O> {
    override fun mapDto(input: List<I>): List<O> {
        return input.map { mapper.mapDto(it) }
    }
}