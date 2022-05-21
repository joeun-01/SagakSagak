package com.songilcraft.sagak_sagak.screen.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.songilcraft.sagak_sagak.R
import com.songilcraft.sagak_sagak.config.BaseActivity
import com.songilcraft.sagak_sagak.databinding.ActivitySignupPasswordBinding

class SignUpPasswordActivity : BaseActivity<ActivitySignupPasswordBinding>(R.layout.activity_signup_password) {
    private val gson : Gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.signupPasswordNextTv.setOnClickListener {

            val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
            var userJson = sharedPreferences.getString("userInfo", "")

            val info = gson.fromJson(userJson, User::class.java)

            info.password = binding.signupPasswordEt.text.toString()

            if(info.password.isNotEmpty()) {
                val editor = sharedPreferences.edit()
                userJson = gson.toJson(info)

                editor.putString("userInfo", userJson)
                editor.apply()

                Log.d("check", userJson.toString())

                val intent = Intent(this, SignUpNameActivity::class.java)
                startActivity(intent)
            }

        }

    }
}