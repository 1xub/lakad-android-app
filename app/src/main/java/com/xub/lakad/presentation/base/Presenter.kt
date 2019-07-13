package com.xub.lakad.presentation.base

interface Presenter<V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()

}