package com.crecrew.crecre.Data

data class RankData(

    /* 1: 전체 구독자
       2: 전체 조회수
       3: 일간 구독자
       4: 일간 조회수

    */
    var flag : Int,
    var gap: Int,
    var image : String,
    var category: String,
    var name: String,
    var rank_img: String,
    var number: Long
)