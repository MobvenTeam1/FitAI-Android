package com.mobven.fitai.presentation.home.training

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mobven.fitai.presentation.home.screens.HealthProblemFragment
import com.mobven.fitai.presentation.home.screens.IntroductionFragment
import com.mobven.fitai.presentation.home.screens.PreferredSportFragment
import com.mobven.fitai.presentation.home.screens.SportBodyPartFragment
import com.mobven.fitai.presentation.home.screens.SportOftenFragment

class TrainingPagerAdapter(
    fragmentActivity: FragmentActivity,
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> IntroductionFragment("training")
            1 -> HealthProblemFragment()
            2 -> PreferredSportFragment()
            3 -> SportOftenFragment()
            4 -> SportBodyPartFragment()
            else -> SportBodyPartFragment()
        }
    }
}
