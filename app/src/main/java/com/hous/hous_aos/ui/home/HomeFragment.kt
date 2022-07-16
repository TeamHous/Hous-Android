package com.hous.hous_aos.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentHomeBinding
import com.hous.hous_aos.ui.home.adapter.ComingUpAdapter
import com.hous.hous_aos.ui.home.adapter.ProfileAdapter
import com.hous.hous_aos.ui.home.adapter.RulesAdapter
import com.hous.hous_aos.ui.home.adapter.ToDoAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var comingUpAdapter: ComingUpAdapter? = null
    private var rulesAdapter: RulesAdapter? = null
    private var toDoAdapter: ToDoAdapter? = null
    private var profileAdapter: ProfileAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        binding.rvProfile.layoutManager = manager
        initAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rulesPosition = rules.size
            toDoPosition = toDo.size
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        comingUpAdapter = null
        rulesAdapter = null
        toDoAdapter = null
        profileAdapter = null
    }

    private fun initAdapter() {
        comingUpAdapter = ComingUpAdapter()
        binding.rvComingUp.adapter = comingUpAdapter
        requireNotNull(comingUpAdapter).submitList(
            comingUp
        )

        rulesAdapter = RulesAdapter()
        binding.rvRules.adapter = rulesAdapter
        requireNotNull(rulesAdapter).submitList(
            rules
        )

        toDoAdapter = ToDoAdapter()
        binding.rvToDo.adapter = toDoAdapter
        requireNotNull(toDoAdapter).submitList(
            toDo
        )

        profileAdapter = ProfileAdapter()
        binding.rvProfile.adapter = profileAdapter
        requireNotNull(profileAdapter).submitList(
            profile
        )
    }

    companion object {
        val comingUp = listOf<ComingUpData>(
            ComingUpData(R.drawable.ic_party, "D-1"),
            ComingUpData(R.drawable.ic_party, "D-4"),
            ComingUpData(R.drawable.ic_beer, "D-6"),
            ComingUpData(R.drawable.ic_coffee, "D-10"),
            ComingUpData(R.drawable.ic_pancake, "D-15"),
            ComingUpData(R.drawable.ic_party, "D-18"),
            ComingUpData(R.drawable.ic_coffee, "D-20"),
            ComingUpData(R.drawable.ic_beer, "D-25"),
            ComingUpData(R.drawable.ic_pancake, "D-80"),
        )

        val rules = listOf<RulesData>(
//            RulesData("00시~ 불 끄기!밤새모니터에튀긴침이마르기도전에"),
//            RulesData("23시~ 이어폰 필수!밤새모니터에튀긴침이마르기도전에"),
//            RulesData("세탁기는 화,수,토밤새모니터에튀긴침이마르기도전에"),
//            RulesData("일 - 청소하는 날!밤새모니터에튀긴침이마르기도전에"),
//            RulesData("2,4주 토- 장보기밤새모니터에튀긴침이마르기도전에"),
//            RulesData("00시~ 불 끄기!밤새모니터에튀긴침이마르기도전에"),
//            RulesData("23시~ 이어폰 필수!밤새모니터에튀긴침이마르기도전에"),
//            RulesData("세탁기는 화,수,토밤새모니터에튀긴침이마르기도전에"),
//            RulesData("일 - 청소하는 날!밤새모니터에튀긴침이마르기도전에"),
//            RulesData("2,4주 토- 장보기밤새모니터에튀긴침이마르기도전에"),

        )

        val toDo = listOf<ToDoData>(
            ToDoData("퇴근하고 마트ㄹㄹㄹ밤새모니터에튀긴침이마르기도전에"),
            ToDoData("저녁 설거지ㅇㅇㅇㅇㅇㅇㅇㅇㅇ밤새모니터에튀긴침이마르기도전에"),
            ToDoData("아침 설거지ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ밤새모니터에튀긴침이마르기도전에"),
            ToDoData("물 사기밤새모니터에튀긴침이마르기도전에"),
            ToDoData("야식 먹지 말자밤새모니터에튀긴침이마르기도전에"),
        )

        val profile = listOf<ProfileData?>(
            ProfileData(1, "이영주"),
            ProfileData(1, "강원용"),
            ProfileData(1, "이준원"),
            ProfileData(1, "김아무개"),
            ProfileData(1, "나까무라"),
            ProfileData(1, "이영주"),
            ProfileData(1, "강원용"),
            ProfileData(1, "이준원"),
            ProfileData(1, "김아무개"),
            ProfileData(1, "나까무라"),
            null
        )
    }
}
