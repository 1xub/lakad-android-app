package com.xub.lakad.di

import com.xub.lakad.presentation.views.profile.ProfilePresenter
import org.koin.dsl.module.module

val appModule = module {
    factory { ProfilePresenter() }
}
