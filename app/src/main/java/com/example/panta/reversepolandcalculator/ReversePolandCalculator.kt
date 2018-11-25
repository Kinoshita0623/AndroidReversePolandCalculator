package com.example.panta.reversepolandcalculator

import java.lang.ArithmeticException
import java.util.*

class ReversePolandCalculator{

    fun exeCalc(formula: String):Int{
        return execute(decode(formula))
    }

    private fun execute(que: Deque<String>):Int{
        val stack = Stack<String>()

        while(!que.isEmpty()){
            val code = que.poll()
            if(comparisonOp(code) == 0){
                stack.push(code)
            }else if(comparisonOp(code) != 0){
                stack.push(code)
                val ans = readOperator(stack)
                stack.push(ans.toString())
            }
        }
        return Integer.parseInt(stack.pop())
    }

    private fun readOperator(stack: Stack<String>):Int{
        val op = stack.pop()
        val num2 = Integer.parseInt(stack.pop())
        val num1 = Integer.parseInt(stack.pop())
        return operator(op,num1,num2)
    }

    fun decode(text: String): ArrayDeque<String> {
        //val formula = ArrayList<String>()
        val que = ArrayDeque<String>()
        //var number = ""
        var number = StringBuilder()
        for(c in text){
            if(c.isDigit() && c != ','){
                //数値の場合
                number.append(c)
            }/*else if(!Character.isWhitespace(c) && number.isNotBlank()){
                que.add(number.toString())
                que.add(c.toString())
                number = StringBuilder()
            }else if(!Character.isWhitespace(c)){
                que.add(c.toString())
            }*/else if(c == ',' &&!Character.isWhitespace(c) && number.isNotBlank()){
                que.add(number.toString())
                //que.add(c.toString())
                number = StringBuilder()
            }else if(c != ',' && !Character.isWhitespace(c)){
                que.add(c.toString())
            }
        }

        if(number.isNotBlank()){
            que.add(number.toString())
        }
        return que

    }

    private fun operator(op: String, num1: Int, num2: Int): Int {
        return when (op) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> num1 / num2
            else -> throw ArithmeticException("演算子異常")
        }

    }

    private fun comparisonOp(operator: String?): Int {
        return when (operator) {
            "*" -> 2
            //System.out.println("かける");
            //break;
            "/" -> 3
            //System.out.println("わる");
            //break;
            "+" -> 1
            //System.out.println("たす");
            //break;
            "-" -> 1
            //System.out.println("ひく");
            else -> 0
        }
    }
}

/*fun String.isDigt(s: String):Boolean{
    s.filter{it->


    }
}*/