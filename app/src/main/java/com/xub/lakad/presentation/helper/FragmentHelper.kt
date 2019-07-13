package com.xub.lakad.presentation.helper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.xub.lakad.presentation.base.BaseFragment

/**
 *
 * Created by Exequiel Egbert V. Ponce on 11/16/2018.
 */
object FragmentHelper {


    fun setupFragment(fragmentManager: FragmentManager, baseFragment: BaseFragment<*, *>, frameLayoutId: Int) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(frameLayoutId, baseFragment)
        fragmentTransaction.commitNow()
    }

    fun setupFragment(fragmentManager: FragmentManager, baseFragment: Fragment, frameLayoutId: Int) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(frameLayoutId, baseFragment)
        fragmentTransaction.commitNow()
    }

}
