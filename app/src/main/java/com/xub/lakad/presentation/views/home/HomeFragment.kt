package com.xub.lakad.presentation.views.home

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.itemanimators.SlideDownAlphaAnimator
import com.xub.lakad.R
import com.xub.lakad.presentation.base.BaseFragment
import com.xub.lakad.presentation.views.adapter.HomeDestinationAdapterItem
import com.xub.lakad.presentation.views.adapter.HomePostAdapterItem
<<<<<<< HEAD
import com.xub.lakad.presentation.views.home.details.ItineraryDetailsActivity
import kotlinx.android.synthetic.main.fragment_home.*
=======
import kotlinx.android.synthetic.main.fragment_home.rv_destination_post
import kotlinx.android.synthetic.main.fragment_home.rv_travel_post
>>>>>>> e8885ce57b206e01b434b3635747b8b35a657430
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<HomeMvpView, HomePresenter>(), HomeMvpView {

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private val mPresenter by inject<HomePresenter>()

    private lateinit var homeDestinationAdapterItem: FastItemAdapter<HomeDestinationAdapterItem>
    private lateinit var homePostAdapterItem: FastItemAdapter<HomePostAdapterItem>

    override val viewRes: Int
        get() = R.layout.fragment_home

    override fun getPresenter(): HomePresenter = mPresenter

    override fun getMvpView(): HomeMvpView = this

    override fun initViews(savedInstanceState: Bundle?) {

        homeDestinationAdapterItem = FastItemAdapter()
        homePostAdapterItem = FastItemAdapter()

        rv_destination_post.itemAnimator = SlideDownAlphaAnimator()
        rv_destination_post.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_destination_post.adapter = homeDestinationAdapterItem

        rv_travel_post.itemAnimator = SlideDownAlphaAnimator()
        rv_travel_post.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_travel_post.adapter = homePostAdapterItem

        homePostAdapterItem.withOnClickListener { v, adapter, item, position ->
            startActivity(Intent(context, ItineraryDetailsActivity::class.java))
            true
        }

        initDestinations()
        initTravelPost()
    }

    private fun initDestinations() {
        homeDestinationAdapterItem.clear()
        for (i in 0..5) {
            homeDestinationAdapterItem.add(HomeDestinationAdapterItem(""))
        }
        homeDestinationAdapterItem.notifyAdapterDataSetChanged()
    }

    private fun initTravelPost() {
        homePostAdapterItem.clear()
        for (i in 0..5) {
            homePostAdapterItem.add(HomePostAdapterItem(""))
        }
        homePostAdapterItem.notifyAdapterDataSetChanged()
    }

}