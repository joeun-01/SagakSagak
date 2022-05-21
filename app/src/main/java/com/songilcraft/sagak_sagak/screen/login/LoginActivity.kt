package com.songilcraft.sagak_sagak.screen.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.songilcraft.sagak_sagak.MainActivity
import com.songilcraft.sagak_sagak.R
import com.songilcraft.sagak_sagak.config.BaseActivity
import com.songilcraft.sagak_sagak.config.GlobalApplication
import com.songilcraft.sagak_sagak.databinding.ActivityLoginSagakBinding
import com.songilcraft.sagak_sagak.screen.signup.SignUpActivity
import com.songilcraft.sagak_sagak.screen.start.StartActivity

class LoginActivity : BaseActivity<ActivityLoginSagakBinding>(R.layout.activity_login_sagak) {

    private val viewModel: LoginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setButton()
        setObserver()
    }

    private fun setObserver() {
        val loginObserver = Observer<Int> { LoginResult ->
            when (LoginResult){
                1000 -> {
                    showSimpleToastMessage("로그인성공")
                    //GlobalApplication.globalSharedPreferences.edit().putString(GlobalApplication.X_ACCESS_TOKEN, viewModel.jwt).apply()
                    GlobalApplication.globalSharedPreferences.edit().putInt(GlobalApplication.USER_IDX, viewModel.userIdx).apply()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                else -> {
                    showSimpleToastMessage("로그인 중 문제가 발생했습니다.")
                }
            }
        }
        viewModel.loginResult.observe(this, loginObserver)

    }

    private fun setButton(){
        binding.loginBtnTv.setOnClickListener{
            viewModel.tryLogin()
        }

        binding.loginSignupTv.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

}