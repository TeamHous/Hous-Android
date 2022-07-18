package com.hous.hous_aos.data.entity.rules

import com.google.gson.annotations.SerializedName

data class TemporaryRulesResponse(
    @SerializedName("_id")
    val id: String,
    val homies: List<HomieData>
)

data class HomieData(
    @SerializedName("_id")
    val id: String,
    val userName: String,
    var isChecked: Boolean,
    val typeColor: String
)
// "_id": "62cc7409c7db06c46adf652f",
//                "userName": " 감자2",
//                "isChecked": true,
//                "typeColor": "GRAY"
