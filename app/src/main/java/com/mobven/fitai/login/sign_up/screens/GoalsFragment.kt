package com.mobven.fitai.login.sign_up.screens

import androidx.viewpager2.widget.ViewPager2
import com.mobven.fitai.R
import com.mobven.fitai.base.BaseFragment
import com.mobven.fitai.databinding.FragmentGoalsBinding
import com.mobven.fitai.login.sign_up.adapter.SignUpListAdapter
import com.mobven.fitai.login.sign_up.model.SignUpSelectorItem
import com.mobven.fitai.util.enums.SignUpSelectorType

class GoalsFragment : BaseFragment<FragmentGoalsBinding>(FragmentGoalsBinding::inflate) {

    private val adapter = SignUpListAdapter()
    override fun observeUi() {

        val goalsList = listOf(
            SignUpSelectorItem(
                title = getString(R.string.calorie_control),
                isSelected = false,
                type = SignUpSelectorType.RADIO
            ),
            SignUpSelectorItem(
                title = getString(R.string.muscle_gain),
                isSelected = false,
                type = SignUpSelectorType.RADIO
            ),
            SignUpSelectorItem(
                title = getString(R.string.weight_gain),
                isSelected = false,
                type = SignUpSelectorType.RADIO
            ),
            SignUpSelectorItem(
                title = getString(R.string.weight_loss),
                isSelected = false,
                type = SignUpSelectorType.RADIO
            )
        )

        adapter.submitList(goalsList)
        binding.rvGoals.adapter = adapter

        binding.btnGoalsContinue.setOnClickListener {
            val currentItem = requireActivity().findViewById<ViewPager2>(R.id.vp_nutrition).currentItem
            val nextItem = currentItem + 1
            requireActivity().findViewById<ViewPager2>(R.id.vp_nutrition).setCurrentItem(nextItem, true)
        }

    }
}