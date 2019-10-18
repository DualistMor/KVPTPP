package com.bohdanserdyuk.KVPTPP.model.interactor.use_case

class JsFormatterUseCase {
    private val stringBuilder = StringBuilder()

    private var counter = 0

    fun addFunction(jsPattern: String, usersPattern: String, vararg values: String): JsFormatterUseCase {
        if(stringBuilder.isEmpty()) stringBuilder.append("javascript:(function() { ")

        val a = String.format(usersPattern, *values)
            .replace("`", "\\'")
            .replace("\'", "\\'")
        val b = String.format(jsPattern, counter.toString(), counter.toString(),a )
        stringBuilder.append(b)
        counter++
        return this
    }

    fun build(): String {
        stringBuilder.append("})()")
        return stringBuilder.toString()
    }
}