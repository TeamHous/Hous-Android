package com.hous.hous_aos.ui.rules.rule.todo

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentToDoBinding
import com.hous.hous_aos.ui.rules.rule.todo.my.MyToDoFragment
import com.hous.hous_aos.ui.rules.rule.todo.today.TodayToDoFragment

class ToDoFragment : Fragment() {

    private var _binding: FragmentToDoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ToDoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_to_do, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@ToDoFragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTransaction()
        setOnClickMyToDO()
    }

    private fun initTransaction() {
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<TodayToDoFragment>(R.id.frg_to_do)
        }
    }

    private fun setOnClickMyToDO() {
        binding.clMyToDo.setOnClickListener {
            when (viewModel.isSelectedMyToDo.value) {
                false -> traverseToMyToDo()
                true -> traverseToTodayToDo()
                else -> throw IllegalArgumentException("viewModel.isSelectedMyToDo.value: ${viewModel.isSelectedMyToDo.value}")
            }
        }
    }

    private fun traverseToTodayToDo() {
        viewModel.setIsSelectedMyToDo(false)
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<TodayToDoFragment>(R.id.frg_to_do)
        }
    }

    private fun traverseToMyToDo() {
        viewModel.setIsSelectedMyToDo(true)
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<MyToDoFragment>(R.id.frg_to_do)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
