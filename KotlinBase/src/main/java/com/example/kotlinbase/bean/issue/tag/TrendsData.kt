package com.example.kotlinbase.bean.issue.tag

data class TrendsData(
    val address: String,
    val avater: String,
    val channelid: Int,
    val date: String,
    val goods: Int,
    val id: Int,
    val lat: Int,
    val lng: Int,
    val mood: String,
    val nickname: String,
    val res: List<Re>,
    val themeid: Int,
    val title: String,
    val type: Int,
    val uid: String,
    val url: String
)

data class Re(
    val tags: List<Tags>,
    val url: String
)

data class Tags(
    val tagtype:Int,
    val tagid:Int,
    val tagname:String,
    val tag_x:Int,
    val tag_y:Int,
    var resid:Int,
    val lng:Int,
    val lat:Int
)