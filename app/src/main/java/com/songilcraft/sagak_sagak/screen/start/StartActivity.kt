package com.songilcraft.sagak_sagak.screen.start

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.songilcraft.sagak_sagak.MainActivity
import com.songilcraft.sagak_sagak.R
import com.songilcraft.sagak_sagak.config.BaseActivity
import com.songilcraft.sagak_sagak.databinding.ActivityStartBinding

class StartActivity : BaseActivity<ActivityStartBinding>(R.layout.activity_start){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)
    }
}