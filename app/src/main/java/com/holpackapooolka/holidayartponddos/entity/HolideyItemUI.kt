package com.holpackapooolka.holidayartponddos.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class HolideyItemUI(
    val id: Long = Random.nextLong(from = 0, until = 1000000),
    val name: String,
    val date: String,
    val day: String,
    val country: String,
    val iso: String
) : Parcelable
