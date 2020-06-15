package org.example.mpp.models

data class TableRowModel(
    val title: String,
    val value: String,
    val icon: Int? = null,
    val options: String? = null
)