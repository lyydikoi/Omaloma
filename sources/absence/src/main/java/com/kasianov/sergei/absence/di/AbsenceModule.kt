package com.kasianov.sergei.absence.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kasianov.sergei.absence.data.AbsenceRepo
import com.kasianov.sergei.absence.data.AbsenceRepoImpl
import com.kasianov.sergei.absence.presentation.absence_details.AbsenceDetailsViewModel
import com.kasianov.sergei.absence.presentation.AbsencesListViewModel
import com.kasianov.sergei.core_api.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class AbsenceModule {

    @Binds
    @Singleton
    abstract fun bindAbsenceRepo(repo: AbsenceRepoImpl): AbsenceRepo

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactoryProvider): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AbsenceDetailsViewModel::class)
    abstract fun bindAbsenceDetailsViewModel(viewModel: AbsenceDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AbsencesListViewModel::class)
    abstract fun bindAbsencesListViewModel(viewModel: AbsencesListViewModel): ViewModel
}
