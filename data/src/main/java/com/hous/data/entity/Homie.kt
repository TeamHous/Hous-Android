package com.hous.data.entity

import com.google.gson.annotations.SerializedName
import com.hous.domain.model.HomieInfo

data class Homie(
    @SerializedName("_id") val id: String? = null,
    val userName: String = "",
    val typeName: String = "",
    val typeId: String = "",
    val typeColor: String = "",
    val job: String = "",
    val introduction: String = "",
    val hashTag: List<String> = emptyList(),
    val typeScore: List<Int> = emptyList(),
    var isChecked: Boolean = false,
    val notificationState: Boolean = false
) {
    fun toHomieInfo(): HomieInfo = HomieInfo(
        id,
        userName,
        typeName,
        typeId,
        typeColor,
        job,
        introduction,
        hashTag,
        typeScore,
        isChecked,
        notificationState
    )
}
