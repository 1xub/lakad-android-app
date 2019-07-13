package com.xub.lakad.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : MvpView, P : BasePresenter<V>> : Fragment() {

    abstract val viewRes: Int

    abstract fun getMvpView(): V

    abstract fun getPresenter(): P

    abstract fun initViews(savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(viewRes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPresenter().attachView(getMvpView())
        initViews(savedInstanceState)
    }

    override fun onDestroyView() {

        if (getPresenter().isViewAttached) {
            getPresenter().detachView()
        }

        super.onDestroyView()
    }

    override fun onResume() {

        if (getPresenter().isViewAttached) {
            if (!getPresenter().isViewAttached) {
                getPresenter().attachView(getMvpView())
            }
        }

        super.onResume()
    }

}