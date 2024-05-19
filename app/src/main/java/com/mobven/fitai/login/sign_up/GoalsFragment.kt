package com.mobven.fitai.login.sign_up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobven.fitai.R
import com.mobven.fitai.databinding.FragmentGoalsBinding
import com.mobven.fitai.login.sign_up.adapter.SignUpListAdapter
import com.mobven.fitai.login.sign_up.model.SignUpSelectorItem
import com.mobven.fitai.util.SignUpSelectorType

class GoalsFragment : Fragment() {

    private lateinit var binding: FragmentGoalsBinding
    private val adapter = SignUpListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGoalsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goalsList = listOf(
            SignUpSelectorItem(
                title = getString(R.string.weight_loss),
                isSelected = false,
                type = SignUpSelectorType.CHECKBOX
            ),
            SignUpSelectorItem(
                title = getString(R.string.weight_gain),
                isSelected = false,
                type = SignUpSelectorType.CHECKBOX
            ),
            SignUpSelectorItem(
                title = getString(R.string.muscle_gain),
                isSelected = false,
                type = SignUpSelectorType.CHECKBOX
            ),
            SignUpSelectorItem(
                title = getString(R.string.calorie_control),
                isSelected = false,
                type = SignUpSelectorType.CHECKBOX
            )
        )

        adapter.submitList(goalsList)
        binding.rvGoals.adapter = adapter
    }
}