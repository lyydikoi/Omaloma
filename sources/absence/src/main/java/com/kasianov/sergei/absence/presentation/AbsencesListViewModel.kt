package com.kasianov.sergei.absence.presentation

import com.kasianov.sergei.absence.data.AbsenceRepo
import com.kasianov.sergei.core.ui.BaseViewModel
import javax.inject.Inject

class AbsencesListViewModel @Inject internal constructor(
    private val abcenseRepo: AbsenceRepo
): BaseViewModel() {

}