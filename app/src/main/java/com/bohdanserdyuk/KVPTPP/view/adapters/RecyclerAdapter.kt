package com.bohdanserdyuk.KVPTPP.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract

class ServicesRecyclerAdapter(val view: BaseContract.MainView,
                              val items: Array<Any>,
                              private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<ServicesRecyclerAdapter.ServicesViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ServicesViewHolder {
        return ServicesViewHolder(layoutInflater.inflate(R.layout.service_item, p0, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(p0: ServicesViewHolder, p1: Int) {
        p0.bind(p1)
    }

    inner class ServicesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var index = 0
        private val title = itemView.findViewById<TextView>(R.id.service_title)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(index: Int) {
            this.index = index
            title.text = items[index].toString()
        }

        override fun onClick(v: View?) {
            view.itemClick(index)
        }
    }
}