package com.example.unimate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//profillerin gösterimi için home fragmentte kullanılan recycleview yapısının adapterı
class RecycleViewAdapter(private val usersArrayList: MutableList<User>) : RecyclerView.Adapter<RecycleViewAdapter.recycleViewHolder>(){
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
    interface OnItemClickListener {
        fun onItemClick(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recycleViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return recycleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: recycleViewHolder, position: Int) {
        val currentItem = usersArrayList[position]
        holder.ilanVerenIsim.text = currentItem.isim
        holder.ilanVerenSoyIsim.text = currentItem.soyisim
        holder.ilanTuru.text = currentItem.durum
        holder.sure.text = currentItem.sure
        holder.mesafe.text = currentItem.mesafe
        holder.kisiSayisi.text = currentItem.kisiSayisi

        holder.itemView.setOnClickListener {
            val user = usersArrayList[position]
            onItemClickListener?.onItemClick(user)
        }
    }

    override fun getItemCount(): Int {
        return usersArrayList.size
    }

    inner class recycleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val ilanVerenIsim : TextView = itemView.findViewById(R.id.verenismi)
        val ilanVerenSoyIsim : TextView = itemView.findViewById(R.id.verenSoyismi)
        val ilanTuru : TextView = itemView.findViewById(R.id.tur)
        val sure : TextView = itemView.findViewById(R.id.ilanSure)
        val mesafe : TextView = itemView.findViewById(R.id.ilanMesafe)
        val kisiSayisi : TextView = itemView.findViewById(R.id.ilanKisiSayisi)

        init {
            itemView.setOnClickListener{
                onClick(itemView)
            }
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = usersArrayList[position]
                    onItemClickListener?.onItemClick(clickedItem)
                }
            }
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val clickedItem = usersArrayList[position]
                onItemClickListener?.onItemClick(clickedItem)
            }
        }
    }
}