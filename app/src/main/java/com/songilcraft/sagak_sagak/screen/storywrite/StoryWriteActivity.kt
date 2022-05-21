package com.songilcraft.sagak_sagak.screen.storywrite

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.songilcraft.sagak_sagak.R
import com.songilcraft.sagak_sagak.config.BaseActivity
import com.songilcraft.sagak_sagak.databinding.ActivityStorywriteBinding
import com.songilcraft.sagak_sagak.recycler.decoration.StoryPhotoDecoration
import com.songilcraft.sagak_sagak.recycler.sender.StoryPhotoEventSender
import com.songilcraft.sagak_sagak.recycler.viewholder.StoryPhotoAdapter
import com.songilcraft.sagak_sagak.screen.gallery.GalleryActivity
import java.util.*

class StoryWriteActivity : BaseActivity<ActivityStorywriteBinding>(R.layout.activity_storywrite), StoryPhotoEventSender{
    lateinit var imagePickerResult : ActivityResultLauncher<Intent>
    private var datePickerIsShow = false


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRecyclerView()
        setEdittext()
        setButton()

        setDatePicker()
        val year = binding.datePicker.year
        val month = binding.datePicker.month + 1
        val day = binding.datePicker.dayOfMonth
        binding.tvDate.text = "$year.$month.$day"

        imagePickerResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val imageList = it.data?.getStringArrayListExtra("imageList")
                (binding.rvPhoto.adapter as StoryPhotoAdapter).applyData(imageList ?: arrayListOf())
                //viewModel.setImageUriList(imageList ?: arrayListOf()) /*changeUriToPath(imageList)*/
            }
            //viewModel.checkAvailable()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setButton(){
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnCalendar.setOnClickListener {
            showDatePicker()
        }

        binding.backgroundFilter.setOnClickListener {
            hideDatePicker()
        }

        binding.tvbtnDateYes.setOnClickListener {
            /*if (viewModel.dateConfirmBtnActivate.value == true) {
                val year = binding.datePicker.year
                val month = binding.datePicker.month
                val day = binding.datePicker.dayOfMonth
                viewModel.changeDate(year, month, day)
                binding.invalidateAll()
                hideDatePicker()
                viewModel.checkAvailable()
            }*/
            val year = binding.datePicker.year
            val month = binding.datePicker.month + 1
            val day = binding.datePicker.dayOfMonth
            binding.tvDate.text = "$year.$month.$day"
            hideDatePicker()
        }

        binding.tvbtnDateNo.setOnClickListener {
            hideDatePicker()
        }
    }

    private fun setRecyclerView(){
        binding.rvPhoto.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPhoto.adapter = StoryPhotoAdapter(sender = this)
        binding.rvPhoto.addItemDecoration(StoryPhotoDecoration(this))

    }

    private fun setEdittext() {

    }

    private fun setDatePicker(){
        val datePickerListener =
            DatePicker.OnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
                //viewModel.checkDate(year, monthOfYear, dayOfMonth)
            }
        val current = Calendar.getInstance()
        binding.datePicker.init(current.get(Calendar.YEAR), current.get(Calendar.MONTH), current.get(
            Calendar.DAY_OF_MONTH), datePickerListener)
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
        imagePickerResult.launch(Intent(this, GalleryActivity::class.java))
    }

    override fun clickPhoto() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
        if (permission == PackageManager.PERMISSION_DENIED){
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
        } else {
            moveToImagePicker()
        }
    }

    private fun showDatePicker(){
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etContent.windowToken, 0)

        datePickerIsShow = true

        val backgroundAnim = AlphaAnimation(0f, 1f)
        backgroundAnim.duration = 350
        backgroundAnim.fillAfter = true
        binding.backgroundFilter.animation = backgroundAnim
        binding.backgroundFilter.visibility = View.VISIBLE

        val anim = TranslateAnimation(0f, 0f, binding.layoutDatePicker.height.toFloat(), 0f)
        anim.duration = 350
        anim.fillAfter = true
        binding.layoutDatePicker.animation = anim
        binding.layoutDatePicker.visibility = View.VISIBLE
    }

    private fun hideDatePicker(){
        datePickerIsShow = false

        val backgroundAnim = AlphaAnimation(1f, 0f)
        backgroundAnim.duration = 350
        backgroundAnim.fillAfter = false
        binding.backgroundFilter.animation = backgroundAnim
        binding.backgroundFilter.visibility = View.GONE

        val anim = TranslateAnimation(0f, 0f, 0f, binding.layoutDatePicker.height.toFloat())
        anim.duration = 350
        anim.fillAfter = false
        binding.layoutDatePicker.animation = anim
        binding.layoutDatePicker.visibility = View.GONE
    }

}