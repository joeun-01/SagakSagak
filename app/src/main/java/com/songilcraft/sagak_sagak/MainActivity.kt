package com.songilcraft.sagak_sagak

import android.content.Intent
import android.os.Bundle
import com.songilcraft.sagak_sagak.config.BaseActivity
import com.songilcraft.sagak_sagak.databinding.ActivityMainBinding
import com.songilcraft.sagak_sagak.screen.gallery.GalleryActivity
import com.songilcraft.sagak_sagak.screen.home.HomeFragment
import com.songilcraft.sagak_sagak.screen.profile.ProfileFragment
import com.songilcraft.sagak_sagak.screen.storywrite.StoryWriteActivity


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
    }

    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.layout_fragment, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.homeFragment -> {  // home 화면 실행
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.layout_fragment, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.addFragment -> {
                    /*supportFragmentManager.beginTransaction()
                        .replace(R.id.layout_fragment, HomeFragment())
                        .commitAllowingStateLoss()*/
                    startActivity(Intent(this, StoryWriteActivity::class.java))
                    return@setOnItemSelectedListener false
                }

                R.id.profileFragment -> {  // 검색 화면 실행
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.layout_fragment, ProfileFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1000 -> {
                moveToImagePicker()
            }
        }
    }

    private fun moveToImagePicker() {
        startActivity(Intent(this, GalleryActivity::class.java))
    }
}