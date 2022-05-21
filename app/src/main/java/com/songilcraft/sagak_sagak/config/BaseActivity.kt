package com.songilcraft.sagak_sagak.config

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.songilcraft.sagak_sagak.R
import com.songilcraft.sagak_sagak.utils.LoadingDialog

open class BaseActivity<B : ViewBinding>  (@LayoutRes val layoutRes: Int) : AppCompatActivity() {
    protected lateinit var binding : B
    protected val exitVertical by lazy { overridePendingTransition(R.anim.none, R.anim.to_bottom) }
    protected val exitHorizontal by lazy { overridePendingTransition(R.anim.from_left_30, R.anim.to_right) }
    lateinit var mLoadingDialog : LoadingDialog
    // 네트워크 에러를 관찰할 observer
    protected val baseNetworkErrorObserver = Observer<BaseViewModel.FetchState>{ fetchState ->
        when (fetchState){
            BaseViewModel.FetchState.BAD_INTERNET -> {
                showSimpleToastMessage(getString(R.string.bad_internet))
            }
            BaseViewModel.FetchState.FAIL -> {
                showSimpleToastMessage(getString(R.string.bad_internet))
            }
            BaseViewModel.FetchState.WRONG_CONNECTION -> {
                showSimpleToastMessage(getString(R.string.bad_internet))
            }
            BaseViewModel.FetchState.PARSE_ERROR -> {}
            null -> {}
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding = DataBindingUtil.setContentView(this, layoutRes)
    }

    /**
     * 아래에서 올라오는 애니메이션을 사용하면서 다른 액티비티를 생성하고자 할 때 사용합니다.
     */
    fun startActivityVertical(intent : Intent){
        startActivity(intent)
        overridePendingTransition(R.anim.from_bottom, R.anim.none)
    }

    /**
     * 오른쪽에서 올라오는 애니메이션을 사용하면서 다른 액티비티를 생성하고자 할 때 사용합니다.
     */
    fun startActivityHorizontal(intent : Intent){
        startActivity(intent)
        overridePendingTransition(R.anim.from_right, R.anim.to_left)
    }

    /**
     * 단순한 toast message 를 호출합니다.
     */
    fun showSimpleToastMessage(message : String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * 로딩 다이얼로그를 호출합니다.
     */
    fun showLoadingDialog(){
        mLoadingDialog = LoadingDialog(this)
        mLoadingDialog.show()
    }

    /**
     * 로딩 다이럴로그를 화면에서 제거합니다.
     */
    fun dismissLoadingDialog(){
        if (mLoadingDialog.isShowing){
            mLoadingDialog.dismiss()
        }
    }

    /**
     * 현재 화면의 크기를 px 단위로 리턴합니다. [너비, 높이]
     */
    protected fun getWindowSize() : ArrayList<Int> {
        val height : Int
        val width : Int
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //val windowMetrics = requireActivity().windowManager.currentWindowMetrics
            val displayMetrics = resources.displayMetrics
            height = displayMetrics.heightPixels//windowMetrics.bounds.height()
            width = displayMetrics.widthPixels//windowMetrics.bounds.width()
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getRealMetrics(displayMetrics)
            height = displayMetrics.heightPixels
            width = displayMetrics.widthPixels
        }
        return arrayListOf(width, height)
    }
}