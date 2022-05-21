package com.songilcraft.sagak_sagak.screen.gallery

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.songilcraft.sagak_sagak.R
import com.songilcraft.sagak_sagak.config.BaseActivity
import com.songilcraft.sagak_sagak.data.GalleryImageData
import com.songilcraft.sagak_sagak.databinding.ActivityGalleryBinding
import com.songilcraft.sagak_sagak.recycler.adapter.GalleryImageAdapter
import com.songilcraft.sagak_sagak.recycler.sender.GalleryImageEventSender

class GalleryActivity : BaseActivity<ActivityGalleryBinding>(R.layout.activity_gallery), GalleryImageEventSender {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setButton()
        setRecyclerView()
        countCheck(0)

        getImageUri()
    }

    private fun setButton(){
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnConfirmation.setOnClickListener {
            val sendIntent = Intent(this, Activity::class.java)
            sendIntent.putExtra("imageList", (binding.rvImages.adapter as GalleryImageAdapter).getSelectImageList())
            setResult(RESULT_OK, sendIntent)
            finish()
        }
    }

    private fun setRecyclerView(){
        binding.rvImages.layoutManager = GridLayoutManager(this, 3)
        binding.rvImages.adapter = GalleryImageAdapter(this)
    }

    private fun getImageUri(){
        val imageList = ArrayList<GalleryImageData>()
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )
        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null)

        if (cursor != null){
            val columnIdx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

            while(cursor.moveToNext()){
                val id = cursor.getLong(columnIdx)
                val uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id.toString())
                imageList.add(GalleryImageData(uri.toString(), false))
            }
        }

        cursor?.close()
        (binding.rvImages.adapter as GalleryImageAdapter).applyData(imageList)
    }

    override fun countCheck(size: Int) {
        if (size !in 1..5){
            binding.btnConfirmation.setTextColor(ContextCompat.getColor(this, R.color.gray_50))
            binding.btnConfirmation.isClickable = false
        } else {
            binding.btnConfirmation.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.btnConfirmation.isClickable = true
        }
    }
}