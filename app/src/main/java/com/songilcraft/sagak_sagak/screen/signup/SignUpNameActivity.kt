package com.songilcraft.sagak_sagak.screen.signup

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.songilcraft.sagak_sagak.R
import com.songilcraft.sagak_sagak.config.BaseActivity
import com.songilcraft.sagak_sagak.databinding.ActivitySignupNameBinding
import com.songilcraft.sagak_sagak.screen.login.LoginActivity

class SignUpNameActivity : BaseActivity<ActivitySignupNameBinding>(R.layout.activity_signup_name) {
    private lateinit var signUpViewModel: SignUpNameViewModel
    private val gson : Gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signUpViewModel = ViewModelProvider(this).get(SignUpNameViewModel::class.java)

        binding.signupCompleteTv.setOnClickListener {

            val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
            val userJson = sharedPreferences.getString("userInfo", "")

            val info = gson.fromJson(userJson, User::class.java)

            info.name = binding.signupNameEt.text.toString()

            if(info.name.isNotEmpty()) {
                signUpViewModel.saveUserName(info)

                val editor = sharedPreferences.edit()
                editor.remove("user")
                editor.apply()

                signUpViewModel.signUp()

                /*val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)*/

            }
        }

        setObserver()
    }

    private fun setObserver() {
        val signUpResultObserver = Observer<Boolean> { isSuccess ->
            if (isSuccess) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        signUpViewModel.signUpSuccess.observe(this, signUpResultObserver)
    }
}