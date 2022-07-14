package com.hous.hous_aos.ui.rules.rule.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentToDoBinding
import com.hous.hous_aos.ui.rules.rule.todo.today.TodayToDoFragment

class ToDoFragment : Fragment() {

    private var _binding: FragmentToDoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentToDoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTransaction()
    }

    private fun initTransaction() {
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<TodayToDoFragment>(R.id.frg_to_do)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
