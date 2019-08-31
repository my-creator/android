package com.crecrew.crecre.data

data class VoteTestData(
    var ImageURL : String,
    var isVotefinish : Boolean,
    var date : Int,
    var title : String,
    var explain : String,
    var choice: ArrayList<VoteItemData>
    )