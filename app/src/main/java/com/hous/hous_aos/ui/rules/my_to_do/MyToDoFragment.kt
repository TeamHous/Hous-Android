package com.hous.hous_aos.ui.rules.my_to_do

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentMyToDoBinding
import com.hous.hous_aos.ui.rules.RulesViewModel
import com.hous.hous_aos.ui.rules.ToDoViewType
import com.hous.hous_aos.ui.rules.today_to_do.TodayToDoFragment

class MyToDoFragment : Fragment() {

    private var _binding: FragmentMyToDoBinding? = null
    private val binding get() = _binding ?: error("binding에 null 들어감")
    private val viewModel: RulesViewModel by activityViewModels()
    private var myToDoAdapter: MyToDoAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_to_do, container, false)
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
        myToDoAdapter = null
    }

    private fun initAdapter() {
        myToDoAdapter = MyToDoAdapter(::onClickCheckBox)
        binding.rvMyToDo.adapter = myToDoAdapter
    }

    private fun observeCategory() {
        viewModel.myTodoList.observe(viewLifecycleOwner) {
            requireNotNull(myToDoAdapter).submitList(it.toList())
        }
    }

    private fun onClickCheckBox(position: Int) {
        viewModel.setMyToDoCheckBoxSelected(position)
    }

    private fun setOnClickMyToDO() {
        binding.clMyToDo.setOnClickListener {
            viewModel.setToDoViewType(ToDoViewType.TODAY_TO_DO)
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<TodayToDoFragment>(R.id.frg_bottom)
            }
        }
    }
}
