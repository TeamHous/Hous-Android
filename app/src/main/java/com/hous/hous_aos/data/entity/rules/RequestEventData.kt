package com.hous.hous_aos.data.entity.rules

import com.google.gson.annotations.SerializedName

/**
 * EventData에서 paricipants랑 지네릭 T이 달라서 따로 빼야할 듯??*/
data class RequestEventData(
    @SerializedName("_id")
    val id: String,
    val eventName: String,
    val eventIcon: String,
    val date: String,
    val participants: List<String>
)

// 이벤트 추가 put
// "eventName": "혜정이 생파",
//    "eventIcon": "PARTY",
//    "date": "2023-03-04",
//    "participants": [ put할 때
// 			"62c871289bfca489f95f6a0a",
// 			"62c8712b9bfca489f95f6a0d"
// 		],
