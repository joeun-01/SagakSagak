package com.songilcraft.sagak_sagak.recycler.decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.songilcraft.sagak_sagak.utils.dpToPx

class StoryPhotoDecoration(context : Context) : RecyclerView.ItemDecoration() {
    private val size8 = dpToPx(context, 8)
    private val size16 = dpToPx(context, 16)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        if (position == 0) {
            outRect.left = size16
        } else {
            outRect.left = size8
        }

        if (position == state.itemCount - 1) {
            outRect.right = size16
        }
    }
}