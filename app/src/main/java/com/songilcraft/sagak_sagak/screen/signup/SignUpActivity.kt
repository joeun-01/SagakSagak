package com.songilcraft.sagak_sagak.screen.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.songilcraft.sagak_sagak.R
import com.songilcraft.sagak_sagak.config.BaseActivity
import com.songilcraft.sagak_sagak.databinding.ActivitySignupIdBinding

class SignUpActivity : BaseActivity<ActivitySignupIdBinding>(R.layout.activity_signup_id) {
    private val gson : Gson = Gson()
    private var user = User("", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.signupNextTv.setOnClickListener {

            user.id = binding.signupIdEt.text.toString()

            if(user.id.isNotEmpty()) {
                val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                val userJson = gson.toJson(user)

                editor.putString("userInfo", userJson)
                editor.apply()

                Log.d("check", userJson.toString())

                val intent = Intent(this, SignUpPasswordActivity::class.java)
                startActivity(intent)
            }
        }
    }
}