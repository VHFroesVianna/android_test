package com.example.dynamicforms.models

import com.example.dynamicforms.models.Field

data class Section(
    val uuid: String,
    val title: String,
    val from: Int,
    val to: Int,
    val index: Int,
    val fields: List<Field>
)