package com.example.asystentnauczyciela.ui.clear_db_view

sealed class ClearDBEvent {
    object OnDeleteDBClick: ClearDBEvent()
    object OnConfirmDeleteDBClick: ClearDBEvent()
}
