package com.songilcraft.sagak_sagak.recycler.viewholder

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.songilcraft.sagak_sagak.databinding.ViewStoryPhotoBinding
import com.songilcraft.sagak_sagak.recycler.sender.StoryPhotoEventSender
import kotlin.math.min

@SuppressLint("NotifyDataSetChanged")
class StoryPhotoAdapter(private val imageList : ArrayList<String> = arrayListOf(), private val sender : StoryPhotoEventSender) : RecyclerView.Adapter<StoryPhotoAdapter.StoryPhotoViewHolder>() {
    private val bitmapList = ArrayList<Bitmap>()

    class StoryPhotoViewHolder(binding : ViewStoryPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        val photo = binding.ivPicture
        val addBtn = binding.btnAdd
        val countText = binding.tvCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryPhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewStoryPhotoBinding.inflate(inflater, parent, false)
        return StoryPhotoViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StoryPhotoViewHolder, position: Int) {
        holder.addBtn.setOnClickListener{
            sender.clickPhoto()
        }
        when {
            position < imageList.size -> {
                holder.photo.visibility = View.VISIBLE
                holder.addBtn.visibility = View.GONE
                holder.countText.visibility = View.GONE
                Glide.with(holder.itemView.context).asBitmap().load(imageList[position]).into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        holder.photo.setImageBitmap(resource)
                        bitmapList.add(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })
            }
            position == imageList.size -> {
                holder.photo.visibility = View.INVISIBLE
                holder.addBtn.visibility = View.VISIBLE
                holder.countText.visibility = View.VISIBLE
                holder.countText.text = "${imageList.size}/5"
                holder.photo.setImageResource(0)
            }
            else -> {
                holder.addBtn.visibility = View.GONE
                holder.countText.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = min(6, imageList.size + 1)

    fun applyData(newImageList : ArrayList<String>) {
        imageList.clear()
        imageList.addAll(newImageList)
        bitmapList.clear()
        notifyDataSetChanged()
    }

    fun applyData() {
        bitmapList.clear()
        notifyDataSetChanged()
    }

    fun getBitmapList() = bitmapList
}