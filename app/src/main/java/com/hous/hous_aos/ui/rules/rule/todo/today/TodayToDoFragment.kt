package com.hous.hous_aos.ui.rules.rule.todo.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hous.hous_aos.databinding.FragmentTodayToDoBinding

class TodayToDoFragment : Fragment() {

    private var _binding: FragmentTodayToDoBinding? = null
    private val binding get() = _binding ?: error("binding에 null 들어감")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodayToDoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
