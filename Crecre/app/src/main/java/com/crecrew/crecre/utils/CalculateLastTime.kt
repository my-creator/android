package com.crecrew.crecre.utils

import java.text.SimpleDateFormat
import java.util.*

public fun CalculateLastime(time : String) : Long {

    var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
    var write_time: Date = sdf.parse(time)
    var cur_time = Date()

    var diff: Long =  write_time.time - cur_time.time

    var datediff: Long = diff / (1000*60*24)

    return datediff
}
