package com.bitage.daysentence.dto

import io.github.newagewriter.mapper.Mapper

@Mapper
data class SentenceDTO(
        val id: String,
        val title: String,
        val content: String,
        val author: String
)
