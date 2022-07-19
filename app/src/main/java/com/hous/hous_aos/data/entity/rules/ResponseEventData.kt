package com.hous.hous_aos.data.entity.rules

import com.google.gson.annotations.SerializedName

data class ResponseEventData(
    @SerializedName("id")
    val id: String,
    val eventName: String,
    val eventIcon: String,
    var date: String,
    val participants: List<HomieData>,
)

// 이벤트 조회 get
// "_id": "62d4335e17e70062873f3d28",
//        "eventName": "여기에 이벤트를 추가하세요.",
//        "eventIcon": "PARTY",
//        "date": "2022-07-28",
//        "participants": [
//            {
//                "_id": "62d3ec80d06d572177b39317",
//                "userName": "손손연주",
//                "isChecked": false,
//                "typeColor": "GRAY"
//            },
//            {

// 이벤트 수정 put
// "_id": "62d55945e5ceec02744e65c3",
//        "eventName": "이벤트 수정",
//        "eventIcon": "CAKE",
//        "date": "2022-07-12",
//        "participants": [
//            "62d3ec80d06d572177b39317"
//        ]
//    }

// 이벤트 삭제 delete
// room id
// event id