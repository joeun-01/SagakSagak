package com.songilcraft.sagak_sagak.recycler.decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.songilcraft.sagak_sagak.utils.dpToPx

class TicketGridDecoration(context : Context) : RecyclerView.ItemDecoration() {
    private val size4 = dpToPx(context, 4)
    private val size8 = dpToPx(context, 8)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        if (position == 0 || position == 1) {
            outRect.top = size8
        }
        outRect.bottom = size8

        outRect.left = size4
        outRect.right = size4

    }
}