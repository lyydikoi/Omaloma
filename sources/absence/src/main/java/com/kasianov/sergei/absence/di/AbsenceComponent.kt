package com.kasianov.sergei.absence.di

import com.kasianov.sergei.absence.presentation.absence_details.AbsenceDetailsFragment
import com.kasianov.sergei.absence.presentation.AbsencesListFragment
import com.kasianov.sergei.core_api.ProvidersFacade
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AbsenceModule::class],
    dependencies = [ProvidersFacade::class]
)
interface AbsenceComponent {

    companion object {
        fun create(providersFacade: ProvidersFacade): AbsenceComponent {
            return DaggerAbsenceComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(fragment: AbsenceDetailsFragment)
    fun inject(fragment: AbsencesListFragment)
}
