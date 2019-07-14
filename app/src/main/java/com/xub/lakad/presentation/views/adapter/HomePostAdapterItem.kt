package com.xub.lakad.presentation.views.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.xub.lakad.R
import com.xub.lakad.presentation.base.FastAdapterItem
import com.xub.lakad.presentation.common.utils.NumberUtils
import kotlinx.android.synthetic.main.item_home_card.view.iv_home_heart
import kotlinx.android.synthetic.main.item_home_card.view.tv_foot_count
import kotlinx.android.synthetic.main.item_home_card.view.tv_fork_count
import kotlin.random.Random

class HomePostAdapterItem(destination: String) :
    FastAdapterItem<String, HomePostAdapterItem, HomePostAdapterItem.ViewHolder>(destination) {

    private var empId: Int? = null

    private var isHeart = true

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
        return R.layout.item_home_card
    }

    @SuppressLint("PrivateResource")
    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)

        holder.view.iv_home_heart.setOnClickListener {
            if (isHeart) {
                holder.view.iv_home_heart.setImageResource(R.drawable.foot)
            } else {
                holder.view.iv_home_heart.setImageResource(R.drawable.footed)
            }

            isHeart = !isHeart
        }

        holder.view.tv_fork_count.text = "${Random.nextInt(0, 50)}"
        holder.view.tv_foot_count.text =
            "${NumberUtils.formatDecimal(Random.nextDouble(1.0, 9.9), 1)}k"

        holder.view.tv_fork_count.setOnClickListener {

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view = itemView as CardView
    }
}