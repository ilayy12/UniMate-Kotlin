package com.example.unimate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RequestRecycleViewAdapter (private val requestsArrayList: MutableList<Request>) : RecyclerView.Adapter<RequestRecycleViewAdapter.RequestViewHolder>(){
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
    interface OnItemClickListener {
        fun onItemClick(request: Request)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.requestitem,parent,false)
        return RequestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val currentItem = requestsArrayList[position]
        holder.teklifciIsim.text = currentItem.isim
        holder.teklifciSoyIsim.text = currentItem.soyisim
        holder.teklifciMail.text = currentItem.email
        holder.teklifciBolum.text = currentItem.bolum
        holder.teklifciTel.text = currentItem.telefon

        holder.itemView.setOnClickListener {
            val request = requestsArrayList[position]
            onItemClickListener?.onItemClick(request)
        }
    }

    override fun getItemCount(): Int {
        return requestsArrayList.size
    }

    inner class RequestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val teklifciIsim : TextView = itemView.findViewById(R.id.requester)
        val teklifciSoyIsim : TextView = itemView.findViewById(R.id.requestersoyad)
        val teklifciBolum : TextView = itemView.findViewById(R.id.requestBolum)
        val teklifciTel : TextView = itemView.findViewById(R.id.requestTel)
        val teklifciMail : TextView = itemView.findViewById(R.id.requestMail)

        init {
            itemView.setOnClickListener{
                onClick(itemView)
            }
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = requestsArrayList[position]
                    onItemClickListener?.onItemClick(clickedItem)
                }
            }
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val clickedItem = requestsArrayList[position]
                onItemClickListener?.onItemClick(clickedItem)
            }
        }
    }

}