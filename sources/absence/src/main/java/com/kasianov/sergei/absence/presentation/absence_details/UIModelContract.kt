package com.kasianov.sergei.absence.presentation.absence_details

import com.kasianov.sergei.core_api.model.dto.AbsenceDTO

interface UIModelContract {

    sealed class UIState {
        object Loading : UIState()
        data class Failure(val message: String) : UIState()
        data class Success(val absence: AbsenceDTO) : UIState()
        data class ChoosingDate(val startDate: String, val endDate: String?) : UIState()
        object NavigatingBack : UIState()
    }

    sealed class Action {
        data class GetAbsence(val millisCreated: String?) : Action()
        data class SetDate(val startDate: String, val endDate: String?) : Action()
        object SaveAbsence : Action()
        object CloseCalendar : Action()
        object ChangeDate : Action()
        object CloseDetailsView : Action()
    }
}
