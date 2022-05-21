package com.songilcraft.sagak_sagak.recycler.viewholder

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.songilcraft.sagak_sagak.R
import com.songilcraft.sagak_sagak.data.Ticket
import com.songilcraft.sagak_sagak.databinding.ViewTicketBinding

class TicketViewHolder(private val binding : ViewTicketBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
        setTopBackground()
        setBottomBackground()
    }

    private fun setTopBackground(){
        val shapeAppearanceModel = ShapeAppearanceModel().toBuilder()
            .setTopLeftCorner(CornerFamily.ROUNDED, 24f).setTopRightCorner(CornerFamily.ROUNDED, 24f)
            .setBottomLeftCorner(CornerFamily.CUT, 24f).setBottomRightCorner(CornerFamily.CUT, 24f).build()

        ViewCompat.setBackground(binding.layoutTop, MaterialShapeDrawable(shapeAppearanceModel).apply {
            fillColor = ContextCompat.getColorStateList(binding.root.context, R.color.white)
        })
    }

    private fun setBottomBackground(){
        val shapeAppearanceModel = ShapeAppearanceModel().toBuilder()
            .setBottomLeftCorner(CornerFamily.ROUNDED, 24f).setBottomRightCorner(CornerFamily.ROUNDED, 24f)
            .setTopLeftCorner(CornerFamily.CUT, 24f).setTopRightCorner(CornerFamily.CUT, 24f).build()

        ViewCompat.setBackground(binding.layoutBottom, MaterialShapeDrawable(shapeAppearanceModel).apply {
            fillColor = ContextCompat.getColorStateList(binding.root.context, R.color.white)
        })
    }


    fun bind(ticket : ArrayList<Ticket>) {
        binding.ticket = ticket[0]
        if (ticket.size > 1){
            binding.tvCount.visibility = View.VISIBLE
            binding.tvCount.text = ticket.size.toString()
        } else {
            binding.tvCount.visibility = View.GONE
        }
    }
}