package com.songilcraft.sagak_sagak.screen.profile


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.songilcraft.sagak_sagak.R
import com.songilcraft.sagak_sagak.config.BaseFragment
import com.songilcraft.sagak_sagak.config.GlobalApplication
import com.songilcraft.sagak_sagak.databinding.FragmentProfileBinding
import com.songilcraft.sagak_sagak.screen.login.LoginActivity

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::bind, R.layout.fragment_profile) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileLogoutTv.setOnClickListener {
            GlobalApplication.globalSharedPreferences.edit().remove(GlobalApplication.USER_IDX).apply()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }

    }

}