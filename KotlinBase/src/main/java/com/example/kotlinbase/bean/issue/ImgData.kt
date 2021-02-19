package com.example.kotlinbase.bean.issue

//TODO 图标编辑页面数据
data class ImgData(
    var path: String?,
    var tags: MutableList<Tag>
) {
    data class Tag(
        var id: Int,
        var x: Float,
        var y: Float,
        var type: Int,
        var name: String,
        var lng: Double,
        var lat: Double
    )
}
