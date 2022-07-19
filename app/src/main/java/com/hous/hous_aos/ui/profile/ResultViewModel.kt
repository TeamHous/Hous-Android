package com.hous.hous_aos.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel() {
    private val _resultData = MutableLiveData(
        ResultData(
            userName = "이영주",
            typeName = "룰 세터 육각이",
            typeColor = "GREEN",
            typeImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png",
            typeOneComment = "한번 더 생각하고 배려하기",
            typeDesc = "여러 생활영역에 대해 존중의 기준이\n" +
                    "대체로 높은 편이에요. 함께 생활하는\n" +
                    "룸메이트에 대해 미리 살펴보고 배려한다면\n" +
                    "더 편안한 공동생활이 이루어질 수 있을 거예요.",
            typeRulesTitle = "오각이와 함께 정하면 좋은 Rule!",
            typeRules = listOf(
                "미사용 전자제품 코드 뽑아두기",
                "외출 시 가스벨브,전등 확인하기",
            ),
            good = ResultData.Homie(
                typeName = "동글이",
                typeImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png",
            ),
            bad = ResultData.Homie(
                typeName = "사각이",
                typeImg = "https://team-hous.s3.ap-northeast-2.amazonaws.com/Type/test/type_test_1.png",
            ),
        )
    )
    val resultData get() = _resultData
}