package com.hous.hous_aos.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.hous.hous_aos.databinding.FragmentHomeBinding
import com.hous.hous_aos.ui.home.adapter.EventAdapter
import com.hous.hous_aos.ui.home.adapter.HomieAdapter
import com.hous.hous_aos.ui.home.adapter.RulesAdapter
import com.hous.hous_aos.ui.home.adapter.ToDoAdapter
import com.hous.hous_aos.ui.main.MainActivity
import com.hous.hous_aos.util.showToast

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var eventAdapter: EventAdapter? = null
    private var rulesAdapter: RulesAdapter? = null
    private var todoAdapter: ToDoAdapter? = null
    private var homieAdapter: HomieAdapter? = null
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        binding.rvProfile.layoutManager = manager
        initEventAdapter()
        initRulesAdapter()
        initToDoAdapter()
        initHomieAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        eventAdapter = null
        rulesAdapter = null
        todoAdapter = null
        homieAdapter = null
    }

    private fun initEventAdapter() {
        viewModel.eventList.observe(viewLifecycleOwner) {
            eventAdapter = EventAdapter(onClickListener = ::onClickListener)
            binding.rvEvent.adapter = eventAdapter
            requireNotNull(eventAdapter).submitList(
                it
            )
        }
    }

    private fun initRulesAdapter() {
        viewModel.keyRulesList.observe(viewLifecycleOwner) {
            rulesAdapter = RulesAdapter()
            binding.rvRules.adapter = rulesAdapter
            rulesAdapter!!.rulesList.addAll(it)
            if (it.isEmpty()) binding.tvRulesEmpty.visibility = View.VISIBLE
            else binding.tvRulesEmpty.visibility = View.INVISIBLE
        }
    }

    private fun initToDoAdapter() {
        viewModel.todoList.observe(viewLifecycleOwner) {
            todoAdapter = ToDoAdapter()
            binding.rvToDo.adapter = todoAdapter
            requireNotNull(todoAdapter).submitList(
                it
            )
            if (it.isEmpty()) binding.tvToDoEmpty.visibility = View.VISIBLE
            else binding.tvToDoEmpty.visibility = View.INVISIBLE
        }
    }

    private fun initHomieAdapter() {
        viewModel.homieList.observe(viewLifecycleOwner) {
            homieAdapter = HomieAdapter(
                showToast = ::showToast,
                onClickMe = ::onClickMe,
                onClickHomie = ::onClickHomie
            )
            binding.rvProfile.adapter = homieAdapter
            requireNotNull(homieAdapter).submitList(
                it
            )
        }
    }

    private fun onClickListener() {
        // 다이얼로그 띄우기
    }

    private fun roomCode() {

    }

    private fun showToast() {
        requireActivity().showToast("초대코드가 복사되었습니다.")
    }

    private fun onClickMe() {
        (activity as MainActivity).replace(2)
    }

    private fun onClickHomie() {
        val intent = Intent(context, RoommateCardActivity::class.java)
        startActivity(intent)
    }
}
