package com.dand0129.binariescalculator


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel: ViewModel() {

    private val _number1 = MutableLiveData("")
    val number1: LiveData<String> get() = _number1

    private val _result = MutableLiveData(0)
    val result: LiveData <Int> get() = _result

    private val _textToShow = MutableLiveData("")
    val textToShow: LiveData <String> get() = _textToShow

    fun binToDec(number: String) {
        if(isBinary(number)){
            _result.value = number.toInt(2)
            _textToShow.value = _result.value.toString()
        } else{
            _textToShow.value = "Please enter a binary number"
        }
    }

     private fun isBinary(number: String): Boolean {
        val pattern = Regex("^[01]+$")
        return pattern.matches(number)
    }

    private fun isDecimal(number: String): Boolean {
        val pattern = Regex("^[0123456789]+\$")
        return pattern.matches(number)
    }

    fun decToBin(number: String) {
        if (isDecimal(number)) {
            val currentDecimalNumber = Integer.toBinaryString(number.toInt()).toInt()
            _result.value = currentDecimalNumber
            _textToShow.value = _result.value.toString()
        } else {
            _textToShow.value = "Please enter a decimal number"
        }

    }

}

