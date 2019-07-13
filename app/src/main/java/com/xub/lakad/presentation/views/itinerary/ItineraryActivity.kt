package com.xub.lakad.presentation.views.itinerary

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.xub.lakad.R
import com.xub.lakad.presentation.base.BaseActivity
import com.xub.lakad.presentation.views.placeform.PlaceFormActivity
import kotlinx.android.synthetic.main.activity_itinerary.add_place
import kotlinx.android.synthetic.main.activity_itinerary.lin_places
import kotlinx.android.synthetic.main.activity_itinerary.toolbar
import kotlinx.android.synthetic.main.layout_itinerary.view.iv_category
import kotlinx.android.synthetic.main.layout_itinerary.view.tv_description
import kotlinx.android.synthetic.main.layout_itinerary.view.tv_name
import kotlinx.android.synthetic.main.layout_itinerary.view.tv_time
import kotlinx.android.synthetic.main.layout_itinerary.view.view
import org.koin.android.ext.android.inject

class ItineraryActivity : BaseActivity<ItineraryMvpView, ItineraryPresenter>(),
    ItineraryMvpView {

    companion object {
        private const val REQUEST_PLACE = 69
    }

    private val mPresenter by inject<ItineraryPresenter>()

    override val viewRes: Int = R.layout.activity_itinerary

    override fun getPresenter(): ItineraryPresenter = mPresenter

    override fun getView(): ItineraryMvpView = this

    override fun initViews(savedInstanceState: Bundle?) {
        add_place.setOnClickListener {
            startActivityForResult(
                Intent(this, PlaceFormActivity::class.java),
                REQUEST_PLACE
            )
        }

        /* init toolbar */
        setSupportActionBar(toolbar as Toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_PLACE &&
            resultCode == RESULT_OK) {
            // get intent
            val place = data!!.getStringExtra(PlaceFormActivity.PLACE)
            val description = data.getStringExtra(PlaceFormActivity.DESCRIPTION)
            val category = data.getStringExtra(PlaceFormActivity.CATEGORY)
            val time = data.getStringExtra(PlaceFormActivity.TIME)

            // presenter? nah just go directly
            val layoutPlace = layoutInflater.inflate(R.layout.layout_itinerary, lin_places, false)

            // set details
            layoutPlace.tv_name.text = place
            layoutPlace.tv_description.text = description
            layoutPlace.tv_time.text = time
            layoutPlace.view.visibility = View.VISIBLE

            val icon = when (category) {
                "BIKING" -> R.drawable.ld_biking
                "FOOD" -> R.drawable.ld_restaurant
                else -> R.drawable.ld_home_red
            }

            layoutPlace.iv_category.setImageResource(icon)

            lin_places.addView(layoutPlace, lin_places.childCount - 1)
        }
    }
}