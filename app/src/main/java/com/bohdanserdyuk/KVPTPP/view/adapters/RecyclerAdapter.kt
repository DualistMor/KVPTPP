package com.bohdanserdyuk.KVPTPP.view.adapters

import android.animation.ObjectAnimator
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.presenter.entity.Service
import kotlinx.android.synthetic.main.service_item.view.*


class ServicesRecyclerAdapter(val context: Context,
                              val view: BaseContract.ServicesView,
                              val items: List<Service>,
                              private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<ServicesRecyclerAdapter.ServicesViewHolder>() {

    var recyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    fun hideKeyboard(pos: Int) {
        val editText = recyclerView?.findViewHolderForAdapterPosition(pos)?.itemView?.findViewById<EditText>(R.id.sum)
        editText?.clearFocus()
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(editText?.windowToken, 0)
    }

    fun hideKeyboard() {
        for (i in 0 until itemCount) {
            hideKeyboard(i)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ServicesViewHolder {
        return ServicesViewHolder(layoutInflater.inflate(R.layout.service_item, p0, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(p0: ServicesViewHolder, p1: Int) = p0.bind(p1)

    inner class ServicesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, TextWatcher {
        private var index = 0
        private val title: TextView = itemView.service_title
        private val editText: EditText = itemView.sum

        init {
            itemView.setOnClickListener(this)
            itemView.expand_arrow.setOnClickListener(this)
            editText.addTextChangedListener(this)
            val preloadedContainer = itemView.preloaded_value_buttons
            for (i in 0 until preloadedContainer.childCount) {
                preloadedContainer.getChildAt(i).setOnClickListener(this)
            }
        }

        fun bind(index: Int) {
            this.index = index
            title.text = items[index].title
            editText.setText(items[index].money)
        }

        override fun onClick(v: View?) {
            when (v!!.id) {
                R.id.expand_arrow -> {
                    hideKeyboard(index)
                    animateViewHolder()
                }
                R.id.value_button_1,
                R.id.value_button_2,
                R.id.value_button_3,
                R.id.value_button_4 -> {
                    editText.setText((v as Button).text)
                }
                else -> view.itemClick(items[index])
            }
        }

        override fun afterTextChanged(s: Editable) {
        }

        override fun beforeTextChanged(s: CharSequence, start: Int,
                                       count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            items[index].money = s.toString()
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