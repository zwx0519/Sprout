package com.example.kotlinbase.bean.issue

data class Issue_GoodBean(
    val count: Int,
    val currentPage: Int,
    val `data`: List<Data>,
    val pageSize: Int,
    val totalPages: Int
){
    data class Data(
        val id: Int,
        val name: String,
        val sort: Int,
        val title: String,
        val url: Any
    )
}