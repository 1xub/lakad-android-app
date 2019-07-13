package com.xub.lakad.di

import com.xub.lakad.presentation.profile.ProfilePresenter
import com.xub.lakad.presentation.views.NavigationPresenter
import com.xub.lakad.presentation.views.home.HomePresenter
import com.xub.lakad.presentation.views.search.SearchPresenter
import org.koin.dsl.module.module

val appModule = module {
    factory { ProfilePresenter() }
    factory { NavigationPresenter() }
    factory { HomePresenter() }
    factory { SearchPresenter() }
}
