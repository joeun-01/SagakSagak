package com.songilcraft.sagak_sagak.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.songilcraft.sagak_sagak.data.Ticket
import com.songilcraft.sagak_sagak.databinding.ViewTicketBinding
import com.songilcraft.sagak_sagak.recycler.viewholder.TicketViewHolder

class TicketAdapter : RecyclerView.Adapter<TicketViewHolder>(){

    private val ticketList = arrayListOf<Ticket>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewTicketBinding.inflate(inflater, parent, false)
        return TicketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bind(ticketList[position])
    }

    override fun getItemCount(): Int = ticketList.size

    fun applyData(newTicketList : ArrayList<Ticket>){
        ticketList.clear()
        ticketList.addAll(newTicketList)
        notifyItemRangeInserted(0, newTicketList.size)
    }
}