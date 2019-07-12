package com.crecrew.crecre.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

@SuppressLint("SimpleDateFormat")
public fun calculateLastime(time : String) : Long {
    var sliced = time.slice(IntRange(0,9))
    var sdf = SimpleDateFormat("yyyy-MM-dd")
    var write_time: Date = sdf.parse(sliced)
    var cur_time = Date()

 //   val dateString = "2018-08-04T10:11:30"
 //   val parsedLocalDateTime = LocalDateTime.parse(dateString)

    var diff: Long =  write_time.time - cur_time.time

    var datediff: Long = diff / (1000*60*24*60)

    return datediff
}
