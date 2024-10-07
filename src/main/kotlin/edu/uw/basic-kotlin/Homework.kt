package edu.uw.basickotlin
import java.lang.IllegalArgumentException

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(x: Any) = when(x){
    "Hello" -> "world"
    0 -> "zero"
    1 -> "one"
    is String -> "Say what?"
    in 2..10 -> "low number"
    is Int -> "a number"
    else -> "I don't understand"
}


// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(num1: Int, num2: Int): Int {
    return num1 + num2
}
// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(num1: Int, num2: Int): Int {
    return num1 - num2
}
// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(num1: Int, num2: Int, numFun: (Int, Int) -> Int) : Int {
    return numFun(num1, num2)
}
// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int){
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"

}
// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
class Money( amount: Int, currency: String){
    init {
        if (amount < 0) {
            throw java.lang.IllegalArgumentException("Amount cannot be less than zero!")
        }
        if (currency !in listOf("USD", "EUR", "GBP", "CAN")) {
            throw java.lang.IllegalArgumentException("Not a valid currency")
        }
    }
    
    var amount: Int = amount
        private set(value) {
        if(value < 0) {
            throw java.lang.IllegalArgumentException ("Amount cannot be less than zero!")
        }
        field = value
    }
    var currency: String = currency
        private set(value){
            if (value !in listOf("USD", "EUR", "GBP", "CAN")){
                throw java.lang.IllegalArgumentException ("Not a valid currency")
                    }
            field = value
        }

    fun convert(newCurrency: String) : Money {
        val currencyConversion = when (this.currency) {
            "USD" -> when (newCurrency) {
                "GBP" -> Math.round(this.amount * 0.5).toInt()
                "EUR" -> Math.round(this.amount * 1.5).toInt()
                "CAN" -> Math.round(this.amount * 1.25).toInt()
                else -> this.amount
            }
            "GBP" -> when (newCurrency) {
                "USD" -> this.amount * 2
                "EUR" -> this.amount * 3
                "CAN" -> Math.round(this.amount * 2.5).toInt()
                else -> this.amount
            }
            "EUR" -> when (newCurrency) {
                "USD" -> Math.round(this.amount * 0.6).toInt()
                "GBP" -> Math.round(this.amount * 0.3).toInt()
                "CAN" -> Math.round(this.amount * 0.83).toInt()
                else -> this.amount
            }
            "CAN" -> when (newCurrency) {
                "USD" -> Math.round(this.amount * 0.8).toInt()
                "GBP" -> Math.round(this.amount * 0.4).toInt()
                "EUR" -> Math.round(this.amount * 1.2).toInt()
                else -> this.amount
            }
            else -> throw java.lang.IllegalArgumentException("Not a valid currency!")
        }
        return Money(currencyConversion, newCurrency)
    }
    operator fun plus(addedMoney: Money) : Money{
        val sumMoney = addedMoney.convert(this.currency)
        return Money((this.amount + sumMoney.amount), this.currency)
    }
}
