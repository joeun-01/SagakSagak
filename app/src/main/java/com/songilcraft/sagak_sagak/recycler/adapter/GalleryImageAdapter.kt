package com.songilcraft.sagak_sagak.recycler.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.songilcraft.sagak_sagak.data.GalleryImageData
import com.songilcraft.sagak_sagak.databinding.ViewGalleryPictureBinding
import com.songilcraft.sagak_sagak.recycler.sender.GalleryImageEventSender

class GalleryImageAdapter(private val sender : GalleryImageEventSender) : RecyclerView.Adapter<GalleryImageAdapter.GalleryImageViewHolder>() {

    private val imageList = ArrayList<GalleryImageData>()
    private val selectPosition = ArrayList<Int>()

    class GalleryImageViewHolder(binding : ViewGalleryPictureBinding) : RecyclerView.ViewHolder(binding.root){
        val image = binding.ivImage
        val isChecked = binding.ivCheck
        val layoutMain = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewGalleryPictureBinding.inflate(inflater, parent, false)
        return GalleryImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryImageViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(imageList[position].imageUri).into(holder.image)
        holder.isChecked.visibility = if (imageList[position].isChecked) View.VISIBLE else View.INVISIBLE
        holder.layoutMain.setOnClickListener {
            if (imageList[position].isChecked){
                imageList[position].isChecked = false
                notifyItemChanged(position)
                selectPosition.remove(position)
                sender.countCheck(selectPosition.size)
            } else if (selectPosition.size in 0 until 5){
                imageList[position].isChecked = true
                selectPosition.add(position)
                notifyItemChanged(position)
                sender.countCheck(selectPosition.size)
            }
        }
    }

    override fun getItemCount(): Int = imageList.size

    @SuppressLint("NotifyDataSetChanged")
    fun applyData(newImageList : ArrayList<GalleryImageData>){
        imageList.clear()
        imageList.addAll(newImageList)
        notifyDataSetChanged()
    }


    fun getSelectImageList() : ArrayList<String>{
        val selectImages = ArrayList<String>()
        for (position in selectPosition){
            selectImages.add(imageList[position].imageUri)
        }
        return selectImages
    }



}