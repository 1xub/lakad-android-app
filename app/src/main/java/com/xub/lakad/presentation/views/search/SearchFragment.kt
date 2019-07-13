package com.xub.lakad.presentation.views.search

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.itemanimators.SlideDownAlphaAnimator
import com.xub.lakad.R
import com.xub.lakad.presentation.base.BaseFragment
import com.xub.lakad.presentation.views.adapter.SearchCategoryAdapterItem
import com.xub.lakad.presentation.views.adapter.SearchCategoryResultAdapterItem
import kotlinx.android.synthetic.main.fragment_search.rv_search_category
import kotlinx.android.synthetic.main.fragment_search.rv_search_results
import org.koin.android.ext.android.inject

class SearchFragment : BaseFragment<SearchMvpView, SearchPresenter>(), SearchMvpView {

    private val mPresenter by inject<SearchPresenter>()

    private lateinit var searchCategoryAdapterItem : FastItemAdapter<SearchCategoryAdapterItem>
    private lateinit var searchCategoryResultAdapterItem : FastItemAdapter<SearchCategoryResultAdapterItem>

    override val viewRes: Int
        get() = R.layout.fragment_search

    override fun getMvpView(): SearchMvpView = this

    override fun getPresenter(): SearchPresenter = mPresenter

    override fun initViews(savedInstanceState: Bundle?) {

        searchCategoryAdapterItem = FastItemAdapter()
        searchCategoryResultAdapterItem = FastItemAdapter()

        rv_search_category.itemAnimator = SlideDownAlphaAnimator()
        rv_search_category.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_search_category.adapter = searchCategoryAdapterItem

        rv_search_results.itemAnimator = SlideDownAlphaAnimator()
        rv_search_results.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_search_results.adapter = searchCategoryResultAdapterItem

        initSearchCategory()
        initSearchCategoryResults()
    }

    private fun initSearchCategory(){
        searchCategoryAdapterItem.clear()
        for(i in 0..5){
            searchCategoryAdapterItem.add(SearchCategoryAdapterItem(""))
        }
        searchCategoryAdapterItem.notifyAdapterDataSetChanged()
    }

    private fun initSearchCategoryResults(){
        searchCategoryResultAdapterItem.clear()
        for(i in 0..5){
            searchCategoryResultAdapterItem.add(SearchCategoryResultAdapterItem(""))
        }
        searchCategoryResultAdapterItem.notifyAdapterDataSetChanged()
    }

}