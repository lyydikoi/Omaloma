package com.kasianov.sergei.absence.presentation.absences.presentation

import com.kasianov.sergei.absence.presentation.absences.data.AbsenceRepo
import com.kasianov.sergei.core.ui.BaseViewModel
import javax.inject.Inject

class AbsenceDetailsViewModel @Inject internal constructor(
    private val abcenseRepo: AbsenceRepo
): BaseViewModel() {

}