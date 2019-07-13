package com.xub.lakad.presentation.views.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.ModelAbstractItem
import com.xub.lakad.R
import com.xub.lakad.presentation.common.utils.NumberUtils
import com.xub.lakad.presentation.views.profile.ProfileItinerary
import kotlinx.android.synthetic.main.layout_trending_itinerary.view.iv_image1
import kotlinx.android.synthetic.main.layout_trending_itinerary.view.iv_image2
import kotlinx.android.synthetic.main.layout_trending_itinerary.view.iv_image3
import kotlinx.android.synthetic.main.layout_trending_itinerary.view.tv_heart_count
import kotlinx.android.synthetic.main.layout_trending_itinerary.view.tv_more
import kotlinx.android.synthetic.main.layout_trending_itinerary.view.tv_name
import kotlin.random.Random

class ProfileItineraryAdapterItem(model: ProfileItinerary) :
    ModelAbstractItem<ProfileItinerary, ProfileItineraryAdapterItem, ProfileItineraryAdapterItem.ViewHolder>(
        model
    ) {

    override fun getType(): Int = R.id.profile_itinerary_item

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun getLayoutRes(): Int = R.layout.layout_trending_itinerary

    @SuppressLint("SetTextI18n")
    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)

        holder.view.iv_image1.setBackgroundResource(model.image1)
        holder.view.iv_image2.setBackgroundResource(model.image2)
        holder.view.iv_image3.setBackgroundResource(model.image3)
        holder.view.tv_more.text = "${Random.nextInt(3, 7)}+"
        holder.view.tv_heart_count.text = "${NumberUtils.formatDecimal(model.heartCount, 1)}k"
        holder.view.tv_name.text = model.name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view = itemView as CardView
    }
}