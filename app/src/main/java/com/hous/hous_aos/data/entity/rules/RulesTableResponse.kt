package com.hous.hous_aos.data.entity.rules

import com.google.gson.annotations.SerializedName

data class RulesTableResponse(
    val keyRulesResponse: List<KeyRulesData>,
    val generalRules: List<GeneralRulesData>
)

data class KeyRulesData(
    @SerializedName("_id")
    val id: String,
    val ruleName: String
)
// [
//            {
//                "_id": "62cc81ac1a034f0287c5c6ec",
//                "ruleName": " 상단규칙"
//            }
//        ]

data class GeneralRulesData(
    @SerializedName("_id")
    val id: String,
    val ruleName: String,
    val membersCnt: Int,
    val typeColors: List<String>
)
// "_id": "62cc74e8d7868591384e4ecd",
//                "ruleName": "설거지하기",
//                "membersCnt": 2,
//                "typeColors": [
//                    "GRAY"
//                ]
