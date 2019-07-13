package com.xub.lakad.presentation.views.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.xub.lakad.R
import com.xub.lakad.presentation.base.FastAdapterItem

class HomePostAdapterItem(destination: String) : FastAdapterItem<String, HomePostAdapterItem, HomePostAdapterItem.ViewHolder>(destination) {

    private var empId: Int? = null

    fun setEmpId(id: Int) {
        empId = id
    }

    fun getEmpId(): Int {
        return empId!!
    }

    override fun getType(): Int {
        return R.id.destination_item
    }

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v);
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_home_destination
    }

    @SuppressLint("PrivateResource")
    override fun bindView(holder: HomePostAdapterItem.ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}