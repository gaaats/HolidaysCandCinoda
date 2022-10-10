package com.holpackapooolka.holidayartponddos.entity


import com.google.gson.annotations.SerializedName

data class ListHolidaysItem(
    @SerializedName("country")
    val country: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("day")
    val day: String?,
    @SerializedName("iso")
    val iso: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("year")
    val year: Int?
) {
    fun mapToUiModel() = HolideyItemUI(
        name = name ?: "none",
        date = date ?: "none",
        day = day ?: "none",
        country = country ?: "none",
        iso = iso ?: "none"
    )
}