package com.hous.hous_aos.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.hous.hous_aos.R
import com.hous.hous_aos.databinding.FragmentHomeBinding
import com.hous.hous_aos.ui.home.adapter.EventAdapter
import com.hous.hous_aos.ui.home.adapter.HomieAdapter
import com.hous.hous_aos.ui.home.adapter.RulesAdapter
import com.hous.hous_aos.ui.home.adapter.ToDoAdapter
import com.hous.hous_aos.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var eventAdapter: EventAdapter? = null
    private var rulesAdapter: RulesAdapter? = null
    private var todoAdapter: ToDoAdapter? = null
    private var homieAdapter: HomieAdapter? = null
    private val viewModel: EventViewModel by activityViewModels()

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
        viewModel.homeInfo()
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
            eventAdapter = EventAdapter(
                onClickEventIcon = ::onClickEventIcon
            )
            binding.rvEvent.adapter = eventAdapter
            requireNotNull(eventAdapter).submitList(
                it
            )
        }
    }

    private fun onClickEventIcon(position: Int) {
        if (position == 0) {
            viewModel.setSelectedEvent(EventIcon.FIRST)
        }
        val dialog = EventDialogFragment()
        dialog.show(childFragmentManager, HOME_FRAGMENT)
        viewModel.getEventDetail(position)
    }

    private fun initRulesAdapter() {
        viewModel.keyRulesList.observe(viewLifecycleOwner) {
            rulesAdapter = RulesAdapter()
            binding.rvRules.adapter = rulesAdapter
            rulesAdapter!!.rulesList.addAll(it)
            if (it.isEmpty()) {
                binding.tvRulesEmpty.visibility = View.VISIBLE
                binding.rvRules.visibility = View.INVISIBLE
            }
            else {
                binding.tvRulesEmpty.visibility = View.INVISIBLE
                binding.rvRules.visibility = View.VISIBLE
            }
        }
    }

    private fun initToDoAdapter() {
        viewModel.todoList.observe(viewLifecycleOwner) {
            todoAdapter = ToDoAdapter()
            binding.rvToDo.adapter = todoAdapter
            requireNotNull(todoAdapter).submitList(
                it
            )
            if (it.isEmpty()) {
                binding.tvToDoEmpty.visibility = View.VISIBLE
                binding.rvToDo.visibility = View.INVISIBLE
            }
            else {
                binding.tvToDoEmpty.visibility = View.INVISIBLE
                binding.rvToDo.visibility = View.VISIBLE
            }
        }
    }

    private fun initHomieAdapter() {
        viewModel.homieList.observe(viewLifecycleOwner) {
            homieAdapter = HomieAdapter(
                showToast = ::showToast,
                onClickHomie = ::onClickHomie
            )
            binding.rvProfile.adapter = homieAdapter
            requireNotNull(homieAdapter).submitList(it.toList())
        }
    }

    private fun showToast() {
        requireActivity().showToast(getString(R.string.copy_code))
    }

    private fun onClickHomie(position: Int) {
        val currentId = viewModel.homieList.value!![position].id
        when (viewModel.homieList.value!![position].typeColor) {
            "GRAY" -> return
            else -> {
                val intent = Intent(requireActivity(), RoommateCardActivity::class.java)
                intent.putExtra("position", currentId)
                startActivity(intent)
            }
        }
    }

    companion object {
        const val HOME_FRAGMENT = "HOME_FRAGMENT"
    }
}
