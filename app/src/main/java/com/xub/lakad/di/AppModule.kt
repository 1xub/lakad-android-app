package com.xub.lakad.di

import com.xub.lakad.presentation.views.NavigationPresenter
import com.xub.lakad.presentation.views.home.HomePresenter
import com.xub.lakad.presentation.views.itinerary.ItineraryPresenter
import com.xub.lakad.presentation.views.placeform.PlaceFormPresenter
import com.xub.lakad.presentation.views.search.SearchPresenter
import com.xub.lakad.presentation.views.profile.ProfilePresenter
import org.koin.dsl.module.module

val appModule = module {
    factory { ProfilePresenter() }
    factory { NavigationPresenter() }
    factory { HomePresenter() }
    factory { SearchPresenter() }
    factory { ItineraryPresenter() }
    factory { PlaceFormPresenter() }
}
