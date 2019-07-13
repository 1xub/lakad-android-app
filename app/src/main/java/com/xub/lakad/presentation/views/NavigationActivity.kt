package com.xub.lakad.presentation.views

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xub.lakad.R
import com.xub.lakad.presentation.base.BaseActivity
import com.xub.lakad.presentation.helper.FragmentHelper
import com.xub.lakad.presentation.views.home.HomeFragment
import com.xub.lakad.presentation.views.profile.ProfileActivity
import com.xub.lakad.presentation.views.search.SearchFragment
import kotlinx.android.synthetic.main.activity_navigation.navigation
import kotlinx.android.synthetic.main.layout_menu_toolbar.iv_bell
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

        navigation.itemIconTintList = null;
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        iv_bell.setOnClickListener{
            startActivity(Intent(this@NavigationActivity, ProfileActivity::class.java))
        }

        FragmentHelper.setupFragment(supportFragmentManager, HomeFragment.newInstance(),R.id.frame_container)
    }
    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {

            when(item.itemId){
                R.id.nav_home -> {
                    FragmentHelper.setupFragment(supportFragmentManager, HomeFragment.newInstance(),R.id.frame_container)
                    return true
                }
                R.id.nav_search -> {
                    FragmentHelper.setupFragment(supportFragmentManager, SearchFragment(),R.id.frame_container)
                    return true
                }
            }

            return false

        }
    }

}