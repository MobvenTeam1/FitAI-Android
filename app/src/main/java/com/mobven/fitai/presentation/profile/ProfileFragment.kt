package com.mobven.fitai.presentation.profile

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobven.fitai.LoginActivity
import com.mobven.fitai.R
import com.mobven.fitai.common.SharedPreferencesHelper
import com.mobven.fitai.databinding.FragmentProfileBinding
import com.mobven.fitai.presentation.base.BaseFragment
import com.mobven.fitai.presentation.profile.adatper.ProfileItem
import com.mobven.fitai.presentation.profile.adatper.ProfileListAdapter
import com.mobven.fitai.util.enums.ProfileItemType

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val personalizationAdapter = ProfileListAdapter()
    private val connectedAccountsAdapter = ProfileListAdapter()
    private val trackAdapter = ProfileListAdapter()
    private val notificationAdapter = ProfileListAdapter()
    private val supportAdapter = ProfileListAdapter()
    private val legalAdapter = ProfileListAdapter()

    override fun observeUi() {
        with(binding){
            rvPersonalization.adapter = personalizationAdapter
            rvPersonalization.layoutManager = NonScrollableLinearLayoutManager(requireContext())

            binding.btnLogout.setOnClickListener {
                SharedPreferencesHelper.deleteUserAuthKey(requireActivity())
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }

            val personalization = listOf(
                ProfileItem(R.drawable.ic_personalization, "Kişisel Bilgiler", ProfileItemType.SIMPLE),
                ProfileItem(R.drawable.ic_meal, "Beslenme Planı", ProfileItemType.SIMPLE),
                ProfileItem(R.drawable.ic_dumbell, "Antreman Planı", ProfileItemType.SIMPLE),
                ProfileItem(R.drawable.ic_kcal, "Kalori Takibi", ProfileItemType.SIMPLE),
                ProfileItem(R.drawable.ic_glass, "Su Takibi", ProfileItemType.SIMPLE))

            personalizationAdapter.submitList(personalization)

            rvConnectedAccounts.adapter = connectedAccountsAdapter
            rvConnectedAccounts.layoutManager = NonScrollableLinearLayoutManager(requireContext())

            val connectedAccounts = listOf(
                ProfileItem(R.drawable.apple_health, "Apple Health", ProfileItemType.SIMPLE),
                ProfileItem(R.drawable.googl_calendar, "Google", ProfileItemType.SIMPLE),
                ProfileItem(R.drawable.ic_watch, "Watch", ProfileItemType.SIMPLE))

            connectedAccountsAdapter.submitList(connectedAccounts)

            rvTrack.adapter = trackAdapter
            rvTrack.layoutManager = NonScrollableLinearLayoutManager(requireContext())

            val track = listOf(
                ProfileItem(R.drawable.ic_kg, "Kilo Güncellemesi", ProfileItemType.ON_OFF),
                ProfileItem(R.drawable.ic_glass, "Su Tüketim", ProfileItemType.ON_OFF),
                ProfileItem(R.drawable.ic_step, "Adım", ProfileItemType.ON_OFF))

            trackAdapter.submitList(track)

            rvNotification.adapter = notificationAdapter
            rvNotification.layoutManager = NonScrollableLinearLayoutManager(requireContext())

            val notification = listOf(
                ProfileItem(R.drawable.ic_clock, "Görev Anımsatıcısı", ProfileItemType.TOGGLE),
                ProfileItem(R.drawable.ic_motivation, "Motivasyon Bildirimi", ProfileItemType.TOGGLE),
                ProfileItem(R.drawable.ic_diary, "Gün Sonu Özeti", ProfileItemType.TOGGLE)
            )

            notificationAdapter.submitList(notification)

            rvSupport.adapter = supportAdapter
            rvSupport.layoutManager = NonScrollableLinearLayoutManager(requireContext())

            val support = listOf(
                ProfileItem(R.drawable.ic_question_mark, "Yardım Merkezi", ProfileItemType.SIMPLE),
                ProfileItem(R.drawable.ic_security, "Güvenlik", ProfileItemType.SIMPLE),
                ProfileItem(R.drawable.ic_how_it_works, "FitAI Nasıl Çalışır?", ProfileItemType.SIMPLE),
                ProfileItem(R.drawable.ic_feedback, "Geri Bildirimde Bulunun", ProfileItemType.SIMPLE)
            )

            supportAdapter.submitList(support)

            rvLegal.adapter = legalAdapter
            rvLegal.layoutManager = NonScrollableLinearLayoutManager(requireContext())

            val legal = listOf(
                ProfileItem(R.drawable.ic_term_of_use, "Kullanım Şartları", ProfileItemType.SIMPLE),
                ProfileItem(R.drawable.ic_privacy_policy, "Gizlilik Politikası", ProfileItemType.SIMPLE))

            legalAdapter.submitList(legal)
        }
    }
}


class NonScrollableLinearLayoutManager(context: Context) : LinearLayoutManager(context) {

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }
}
