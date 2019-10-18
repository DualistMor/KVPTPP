package com.bohdanserdyuk.KVPTPP.model.interactor.use_case

import java.util.regex.Pattern

class CorrectFullNameUseCase {
    val nRex = "[А-ЯІЇЄҐ]{1}[а-яіїєґ'`]+"

    /**
     * matches example: Григорій Саввич Сковорода, Григорій Саввич-Квітка Сковорода
     */
    fun isFullNameCorrectUA(str: String): Boolean = Pattern.matches("(\\A$nRex\\b)(\\b[-]$nRex\\b)?( )(\\b$nRex\\b)( )(\\b$nRex\\b)", str)
}