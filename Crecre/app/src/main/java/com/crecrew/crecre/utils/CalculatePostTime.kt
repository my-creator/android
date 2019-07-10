package com.crecrew.crecre.utils

import java.text.SimpleDateFormat
import java.util.*

class CalculatePostTime{

    public fun calculatePostTime(time : String) : String {

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val write_time: Date = sdf.parse(time)
        val cur_time = Date()

        val diff: Long = cur_time.time - write_time.time
        var minute = diff / (1000 * 60)

        var result: String
        if (minute >= 0 && minute < 60)
            result = "$minute" + "분 전"
        else if (minute >= 60 && minute < 60 * 24) {
            minute /= 60
            result = "$minute" + "시간 전"
        } else if (minute >= 60 * 24 && minute < 60 * 24 * 30) {
            minute /= (60 * 24)
            result = "$minute" + "일 전"
        } else if (minute >= 60 * 24 * 30 && minute < 60 * 24 * 365) {
            minute /= (60 * 24 * 30)
            result = "$minute" + "달 전"
        } else {
            minute /= (60 * 24 * 365)
            result = "$minute" + "년 전"
        }
        return result
    }
}