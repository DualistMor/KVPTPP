package com.bohdanserdyuk.KVPTPP.presenter.interactor.use_case

class JsFormatterUseCase {
    fun formatJsWithUsersInfo(usersPattern: String, jsPattern: String, selectedService: String, pib: String): String {
        val usersPatternFormatted: String = String.format(usersPattern, selectedService, pib)
        return String.format(jsPattern, usersPatternFormatted.replace("`", "\\'").replace("\'", "\\'")
        )
    }
}