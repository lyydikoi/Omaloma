package com.kasianov.sergei.omaloma.core.di.publicholidays

import com.kasianov.sergei.omaloma.core.di.FragmentScope
import com.kasianov.sergei.omaloma.core.di.AppComponent
import com.kasianov.sergei.omaloma.presentation.publicholidays.PubHolDetailsFragment
import com.kasianov.sergei.omaloma.presentation.publicholidays.PubHolListFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class])
interface PublicHolidaysComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): PublicHolidaysComponent
    }

    fun inject(fragment: PubHolDetailsFragment)
    fun inject(fragment: PubHolListFragment)

}