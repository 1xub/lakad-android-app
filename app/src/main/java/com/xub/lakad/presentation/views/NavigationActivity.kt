package com.xub.lakad.presentation.views

import android.os.Bundle
import com.xub.lakad.R
import com.xub.lakad.presentation.base.BaseActivity
import com.xub.lakad.presentation.helper.FragmentHelper
import com.xub.lakad.presentation.views.home.HomeFragment
import org.koin.android.ext.android.inject

class NavigationActivity : BaseActivity<NavigationMvpView, NavigationPresenter>(),
    NavigationMvpView
{

    private val mPresenter by inject<NavigationPresenter>()

    override val viewRes: Int
        get() = R.layout.activity_navigation

    override fun getPresenter(): NavigationPresenter = mPresenter

    override fun getView(): NavigationMvpView = this

    override fun initViews(savedInstanceState: Bundle?) {
        FragmentHelper.setupFragment(supportFragmentManager, HomeFragment.newInstance(),R.id.frame_container)
    }

}