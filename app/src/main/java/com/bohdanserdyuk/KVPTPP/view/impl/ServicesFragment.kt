package com.bohdanserdyuk.KVPTPP.view.impl

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bohdanserdyuk.KVPTPP.KVPTPPAplication
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.presenter.entity.Service
import com.bohdanserdyuk.KVPTPP.view.BaseFragment
import com.bohdanserdyuk.KVPTPP.view.adapters.ServicesRecyclerAdapter
import com.bohdanserdyuk.KVPTPP.viewModel.BasePresenterViewModel
import kotlinx.android.synthetic.main.fragment_services.view.*


class ServicesFragment : BaseFragment<BaseContract.ServicesView, BaseContract.ServicesPresenter>(), BaseContract.ServicesView {

    lateinit var v: View

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity!!.application as KVPTPPAplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_services, container, false)
        v.servicesRecycler.setItemViewCacheSize(15)
        v.servicesRecycler.layoutManager = LinearLayoutManager(activity)
        v.servicesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) presenter.scrolledDown() else if (dy < 0) presenter.scrolledUp()
            }
        })
        presenter.onCreateView()
        return v
    }

    override fun hideKeyboard() = (v.servicesRecycler.adapter as ServicesRecyclerAdapter).hideKeyboard()

    override fun sendMessage(m: Any) {
        ViewModelProviders.of(activity!!).get(BasePresenterViewModel::class.java).sendMessage(m)
    }

    override fun setAdapter(list: List<Service>) {
        v.servicesRecycler.adapter = ServicesRecyclerAdapter(context!!, this, list, layoutInflater)
    }

    override fun <T : Activity> startActivity(c: Class<T>) = startActivity(Intent(activity, c))

    override fun itemClick(s: Service) = presenter.itemClick(s)
}
