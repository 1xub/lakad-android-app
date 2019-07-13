package com.xub.lakad.presentation.views.search

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.itemanimators.SlideDownAlphaAnimator
import com.xub.lakad.R
import com.xub.lakad.presentation.base.BaseFragment
import com.xub.lakad.presentation.views.adapter.SearchCategoryAdapterItem
import kotlinx.android.synthetic.main.fragment_search.rv_search_category
import org.koin.android.ext.android.inject

class SearchFragment : BaseFragment<SearchMvpView, SearchPresenter>(), SearchMvpView {

    private val mPresenter by inject<SearchPresenter>()

    private lateinit var searchCategoryAdapterItem : FastItemAdapter<SearchCategoryAdapterItem>

    override val viewRes: Int
        get() = R.layout.fragment_search

    override fun getMvpView(): SearchMvpView = this

    override fun getPresenter(): SearchPresenter = mPresenter

    override fun initViews(savedInstanceState: Bundle?) {

        searchCategoryAdapterItem = FastItemAdapter()

        rv_search_category.itemAnimator = SlideDownAlphaAnimator()
        rv_search_category.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_search_category.adapter = searchCategoryAdapterItem

        initSearchCategory()
    }

    private fun initSearchCategory(){
        searchCategoryAdapterItem.clear()
        for(i in 0..5){
            searchCategoryAdapterItem.add(SearchCategoryAdapterItem(""))
        }
        searchCategoryAdapterItem.notifyAdapterDataSetChanged()
    }

}