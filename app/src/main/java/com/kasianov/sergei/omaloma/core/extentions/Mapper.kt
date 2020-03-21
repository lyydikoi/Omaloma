package com.kasianov.sergei.omaloma.core.extentions

interface Mapper<I, O> {
    fun mapDto(input: I): O
}