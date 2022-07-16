package com.hous.hous_aos.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TendencyViewModel : ViewModel() {
    private val _uiList = MutableLiveData<List<TendencyData>>(
        listOf(
            TendencyData(
                0, "알람 소리에 눈을 뜬 당신\n방 안은 어떤가요?",
                "https://www.youtube.com/channel/UCz7gPLMK3PAXBTIEavZIXEg", listOf(
                    "음식 냄새가 나지만 내 방까지\n" +
                            "풍기는 것도 아니니 괜찮다.",
                    "와, 맛있겠다! 맛있는 음식의 냄새가 난다", "조용히 창문을 열어 환기시킨다",
                )
            ),
            TendencyData(
                1, "외출준비를 하려는데\n샴푸통이 비어있는 것 같다",
                "https://www.insight.co.kr/news/259411", listOf(
                    "침대에는 옷들, 책상 위는 보던 책.\n" +
                            "섞여 있지만 나만의 규칙은 있다.", "침대에는 옷들, 책상 위는 보던 책.\n" +
                            "섞여 있지만 나만의 규칙은 있다.", "1233"
                )
            ),
            TendencyData(
                2, "옷을 입으려 옷장을 열었다.\n옷장의 상태는?",
                "https://www.insight.co.kr/news/353985", listOf(
                    "침대에는 옷들, 책상 위는 보던 책.\n" +
                            "섞여 있지만 나만의 규칙은 있다.", "침대에는 옷들, 책상 위는 보던 책.\n" +
                            "섞여 있지만 나만의 규칙은 있다.", "침대에는 옷들, 책상 위는 보던 책.\n" +
                            "섞여 있지만 나만의 규칙은 있다."
                )
            ),
            TendencyData(
                3, "집을 나가는 순간, 룸메이트가\n옷을 내밀며 냄새가 나는지 묻는다면?",
                "https://www.cashfeed.co.kr/posts/3762754", listOf(
                    "qwer1", "2qwer", "침대에는 옷들, 책상 위는 보던 책.\n" +
                            "섞여 있지만 나만의 규칙은 있다."
                )
            ),
            TendencyData(
                4,
                "알람 소리에 눈을 뜬 당신\n방 안은 어떤가요?",
                "https://talk.op.gg/s/lol/humor/4716821/%EA%B4%B4%EB%AC%BC%EC%A5%90%EC%82%AC%EA%B1%B4",
                listOf("1qwer", "2qwer", "qwer3")
            ),
            TendencyData(
                5, "외출준비를 하려는데\n샴푸통이 비어있는 것 같다",
                "https://www.cashfeed.co.kr/posts/3782683", listOf("qwe1", "rwer2", "3qwe")
            ),
            TendencyData(
                6, "알람 소리에 눈을 뜬 당신\n방 안은 어떤가요?",
                "https://www.insight.co.kr/news/353985", listOf("1rew", "qwer2", "3rew")
            ),
            TendencyData(
                7, "외출준비를 하려는데\n샴푸통이 비어있는 것 같다",
                "https://www.cashfeed.co.kr/posts/3762754", listOf("qwe1", "2", "3")
            ),
            TendencyData(
                8, "알람 소리에 눈을 뜬 당신\n방 안은 어떤가요?",
                "https://www.youtube.com/channel/UCz7gPLMK3PAXBTIEavZIXEg", listOf("1", "2", "3")
            ),
            TendencyData(
                9, "외출준비를 하려는데\n샴푸통이 비어있는 것 같다",
                "https://www.insight.co.kr/news/259411", listOf("1", "2", "3")
            ),
            TendencyData(
                10, "알람 소리에 눈을 뜬 당신\n방 안은 어떤가요?",
                "https://www.insight.co.kr/news/353985", listOf("1", "2", "3")
            ),
            TendencyData(
                11, "외출준비를 하려는데\n샴푸통이 비어있는 것 같다",
                "https://www.cashfeed.co.kr/posts/3762754", listOf("1", "2", "3")
            ),
            TendencyData(
                12,
                "알람 소리에 눈을 뜬 당신\n방 안은 어떤가요?",
                "https://talk.op.gg/s/lol/humor/4716821/%EA%B4%B4%EB%AC%BC%EC%A5%90%EC%82%AC%EA%B1%B4",
                listOf("1", "2", "3")
            ),
            TendencyData(
                13, "외출준비를 하려는데\n샴푸통이 비어있는 것 같다",
                "https://www.cashfeed.co.kr/posts/3782683", listOf("1", "2", "3")
            ),
            TendencyData(
                14, "알람 소리에 눈을 뜬 당신\n방 안은 어떤가요?",
                "https://www.insight.co.kr/news/353985", listOf("1", "2", "3")
            ),
        )
    )
    val uiList get() = _uiList

    var index = 0
        private set

    private var totalIndex = 0

    private val _move = MutableLiveData<Boolean>()
    val move: LiveData<Boolean> = _move

    private val scoreList = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    fun increaseIndex() {
        _move.value = true
        index += 1
        Log.d("setScore", "value $scoreList, index $index, totalIndex $totalIndex")

    }

    fun decreaseIndex() {
        _move.value = false
        index -= 1
        Log.d("setScore", "value $scoreList, index $index, totalIndex $totalIndex")
    }

    fun setScore(score: Int) {  // adapter에서 버튼을 클랙했을 때 1. 화면 넘어감, 2. 점수 삽입, 3. 현재 인덱스++, 4. 점수가 없을 때 토탈++
        _move.value = true
        if (scoreList[index] == 0) totalIndex += 1
        scoreList[index] = score
        index += 1
        Log.d("setScore", "value $scoreList, index $index, totalIndex $totalIndex")
    }

    fun visibilityButton(): Boolean {
        return index < totalIndex
    }

    fun sumScore() {
        scoreList.sum()
    }
}
