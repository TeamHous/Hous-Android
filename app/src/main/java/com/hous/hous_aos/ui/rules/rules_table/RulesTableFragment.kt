package com.hous.hous_aos.ui.rules.rules_table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentRulesTableBinding
import com.hous.hous_aos.ui.rules.RulesViewModel

class RulesTableFragment : Fragment() {

    private var _binding: FragmentRulesTableBinding? = null
    private val binding get() = _binding ?: error("binding에 null이 들어갔어요!!")
    private val viewModel: RulesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_rules_table, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
