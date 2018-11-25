package com.example.panta.reversepolandcalculator

import java.lang.ArithmeticException

class Calculator{
    fun calc(num1: String, num2: String, op: String):Int{
        val n1 = Integer.parseInt(num1)
        val n2 = Integer.parseInt(num2)
        return when(op){
            "+" ->{
                n1 + n2
            }
            "-" ->{
                n1 - n2
            }
            "*" ->{
                n1 * n2

            }
            "/" ->{
                n1 / n2
            }
            else ->{
                throw ArithmeticException("演算子異常")
            }
        }
    }

    fun calcModule(op: String){

    }
}