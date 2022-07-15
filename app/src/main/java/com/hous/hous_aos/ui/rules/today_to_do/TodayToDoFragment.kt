package com.hous.hous_aos.ui.rules.today_to_do

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentTodayToDoBinding
import com.hous.hous_aos.ui.rules.RulesViewModel
import com.hous.hous_aos.ui.rules.ToDoViewType
import com.hous.hous_aos.ui.rules.adapter.TodayTodoAdapter
import com.hous.hous_aos.ui.rules.my_to_do.MyToDoFragment

class TodayToDoFragment : Fragment() {

    private var _binding: FragmentTodayToDoBinding? = null
    private val binding get() = _binding ?: error("binding에 null 들어감")
    private val viewModel: RulesViewModel by activityViewModels()
    private lateinit var todayTodDoAdapter: TodayTodoAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_today_to_do, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeCategory()
        setOnClickMyToDO()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        todayTodDoAdapter = TodayTodoAdapter()
        binding.rvToDo.adapter = todayTodDoAdapter
    }

    private fun observeCategory() {
        viewModel.todayTodoList.observe(viewLifecycleOwner) {
            todayTodDoAdapter.submitList(it.toList())
        }
    }

    private fun setOnClickMyToDO() {
        binding.clMyToDo.setOnClickListener {
            viewModel.setToDoViewType(ToDoViewType.MY_TO_DO)
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<MyToDoFragment>(R.id.frg_bottom)
            }
        }
    }
}
