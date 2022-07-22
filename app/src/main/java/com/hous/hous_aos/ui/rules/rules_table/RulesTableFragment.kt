package com.hous.hous_aos.ui.rules.rules_table

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentRulesTableBinding
import com.hous.hous_aos.ui.newrules.NewRuleActivity
import com.hous.hous_aos.ui.rules.RulesViewModel

class RulesTableFragment : Fragment() {

    private var _binding: FragmentRulesTableBinding? = null
    private val binding get() = _binding ?: error("binding에 null이 들어갔어요!!")
    private val viewModel: RulesViewModel by activityViewModels()
    private var keyRulesAdapter: KeyRulesAdapter? = KeyRulesAdapter()
    private var generalRulesAdapter: GeneralRulesAdapter? = GeneralRulesAdapter()
    private var conCatAdapter: ConcatAdapter? = ConcatAdapter(keyRulesAdapter, generalRulesAdapter)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_rules_table, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initConcatAdapter()
        observeRulesTable()
        clickPlusButton()
    }

    private fun initConcatAdapter() {
        conCatAdapter = ConcatAdapter(keyRulesAdapter, generalRulesAdapter)
        binding.rvRulesTable.adapter = conCatAdapter
    }

    private fun observeRulesTable() {
        viewModel.keyRulesTableList.observe(viewLifecycleOwner) {
            requireNotNull(keyRulesAdapter).submitList(it.toList())
        }

        viewModel.generalRulesTableList.observe(viewLifecycleOwner) {
            requireNotNull(generalRulesAdapter).submitList(it.toList())
        }
    }

    private fun clickPlusButton() {
        binding.clPlusRules.setOnClickListener {
            val intent = Intent(activity, NewRuleActivity::class.java)
            intent.putExtra("categoryName", viewModel.categoryName.value)
            intent.putExtra("categoryId", viewModel.categoryName.value)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        conCatAdapter = null
        keyRulesAdapter = null
        generalRulesAdapter = null
    }
}
