package com.bohdanserdyuk.KVPTPP.presenter.interactor

class StringToAnyMapper {
    fun toAnyArray(array: Array<String>): Array<Any> {
        return Array(array.size) { array[it] }
    }
}