package com.bohdanserdyuk.KVPTPP.model.interactor

class JsFormatterBuilder {
    private val stringBuilder = StringBuilder()

    private var counter = 0

    /**
     * If vararg set as an argument of other vararg it should be marked with *(spread operator)[values],
     * in other way, method toString() will be applied to vararg and only one string will be passed to fun arguments
     */
    fun addFunction(jsPattern: String, usersPattern: String, vararg values: String): JsFormatterBuilder {
        if (!values.contains("")) {
            if (stringBuilder.isEmpty()) stringBuilder.append("javascript:(function() { ")
            stringBuilder.append(String.format(jsPattern, counter.toString(), counter.toString(), String.format(usersPattern, *values)
                .replace("`", "\\'")
                .replace("\'", "\\'")))
            counter++
        }
        return this
    }

    fun build(): String {
        stringBuilder.append("})()")
        return stringBuilder.toString()
    }
}