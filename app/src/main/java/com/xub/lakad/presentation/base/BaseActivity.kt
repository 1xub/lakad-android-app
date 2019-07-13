package com.xub.lakad.presentation.base

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity<V : MvpView, P : BasePresenter<V>> :
    AppCompatActivity(),
    MvpView {

    abstract val viewRes: Int

    abstract fun getPresenter(): P

    abstract fun getView(): V

    abstract fun initViews(savedInstanceState: Bundle?)

    private var loadingDialog: Dialog? = null
    private val dialogs = arrayListOf<Dialog>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewRes)
        if (!getPresenter().isViewAttached) {
            getPresenter().attachView(getView())
        }

        initViews(savedInstanceState)

    }


    override fun onPause() {
        super.onPause()
        if (!getPresenter().isViewAttached) {
            getPresenter().attachView(getView())
        }
    }

    override fun onDestroy() {
        if (getPresenter().isViewAttached) {
            getPresenter().detachView()
        }

        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        if (!getPresenter().isViewAttached) {
            getPresenter().attachView(getView())
        }
    }

    override fun onResume() {
        super.onResume()
        if (!getPresenter().isViewAttached) {
            getPresenter().attachView(getView())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        getPresenter().detachView()
        super.onSaveInstanceState(outState)
    }


}