package com.crecrew.crecre.Data

data class VoteEndData (
    var ImageURL: String,
    var title: String,
    var explain: String,
    var item1: ArrayList<VoteItemData>,
    var item2: ArrayList<VoteItemData>,
    var item3: ArrayList<VoteItemData>,
    var item4: ArrayList<VoteItemData>,
    var item5: ArrayList<VoteItemData>
    )