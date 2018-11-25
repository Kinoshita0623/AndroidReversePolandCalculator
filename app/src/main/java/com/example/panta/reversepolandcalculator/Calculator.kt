package com.example.panta.reversepolandcalculator

import java.lang.ArithmeticException
import java.math.BigDecimal

class Calculator{
    fun calc(num1: String, num2: String, op: String):BigDecimal{
        //val n1 = Integer.parseInt(num1)
        //val n2 = Integer.parseInt(num2)
        val n1 = BigDecimal(num1)
        val n2 = BigDecimal(num2)
        return when(op){
            "+" ->{
                //n1 + n2
                n1.add(n2)
            }
            "-" ->{
                //n1 - n2
                n1.subtract(n2)
            }
            "*" ->{
                //n1 * n2
                n1.multiply(n2)

            }
            "/" ->{
                n1.divide(n2)
            }
            else ->{
                throw ArithmeticException("演算子異常")
            }
        }
    }

    fun calcModule(op: String){

    }
}