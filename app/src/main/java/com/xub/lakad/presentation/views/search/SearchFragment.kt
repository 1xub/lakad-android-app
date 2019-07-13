package com.xub.lakad.presentation.views.search

import android.os.Bundle
import com.xub.lakad.R
import com.xub.lakad.presentation.base.BaseFragment
import org.koin.android.ext.android.inject

class SearchFragment : BaseFragment<SearchMvpView, SearchPresenter>(), SearchMvpView {

    private val mPresenter by inject<SearchPresenter>()

    override val viewRes: Int
        get() = R.layout.fragment_search

    override fun getMvpView(): SearchMvpView = this

    override fun getPresenter(): SearchPresenter = mPresenter

    override fun initViews(savedInstanceState: Bundle?) {

    }

}