package com.bohdanserdyuk.KVPTPP.view.adapters

import android.animation.ObjectAnimator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.entity.ServiceData
import com.bohdanserdyuk.KVPTPP.presenter.entity.Service
import kotlinx.android.synthetic.main.service_item.view.*


class ServicesRecyclerAdapter(val view: BaseContract.MainView,
                              val items: List<Service>,
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
        private val title: TextView = itemView.service_title
        private val editText: EditText = itemView.sum

        init {
            itemView.expand_arrow.setOnClickListener(this)
            itemView.setOnClickListener(this)
        }

        fun bind(index: Int) {
            this.index = index
            title.text = items[index].title
            editText.setText(items[index].money)
        }

        override fun onClick(v: View?) {
            when (v!!.id) {
                R.id.expand_arrow -> animateViewHolder()
                else -> view.itemClick(items[index])
            }
        }

        private fun animateViewHolder() {

            val imageViewObjectAnimator = ObjectAnimator.ofFloat(itemView.expand_arrow,
                "rotation", 0f, 180f)

            if (itemView.expand_item.visibility == View.VISIBLE) {
                itemView.expand_item.visibility = View.GONE
                imageViewObjectAnimator.reverse()
            } else {
                itemView.expand_item.visibility = View.VISIBLE
                imageViewObjectAnimator.start()
            }
        }
    }
}