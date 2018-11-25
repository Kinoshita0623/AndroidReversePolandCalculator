package com.example.panta.reversepolandcalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val numberButtonList = ArrayList<Button>()
        numberButtonList.apply{
            add(findViewById(R.id.zeroButton))
            add(findViewById(R.id.oneButton))
            add(findViewById(R.id.twoButton))
            add(findViewById(R.id.threeButton))
            add(findViewById(R.id.fourButton))
            add(findViewById(R.id.fiveButton))
            add(findViewById(R.id.sixButton))
            add(findViewById(R.id.sevenButton))
            add(findViewById(R.id.eightButton))
            add(findViewById(R.id.nineButton))

        }

        val calc = Calculator()

        val listView: ListView = findViewById(R.id.listView)

        val numberList = ArrayList<String>()



        fun displayNumberList(){
            val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numberList)
            listView.adapter = arrayAdapter
        }

        var numbers = StringBuilder()

        for(n in 0.until(10)){
            val button:Button = numberButtonList[n]
            button.setOnClickListener{
                applyNumber(n.toString(), numbers, numberList)
                displayNumberList()
            }

        }
        //小数点
        val smallNumberPoint:Button = findViewById(R.id.smallNumberPoint)
        smallNumberPoint.setOnClickListener {
            applyNumber(".", numbers, numberList)
        }

        //Enter
        val enterButton: Button = findViewById(R.id.enterButton)
        enterButton.setOnClickListener {
            displayNumberList()
            numbers = StringBuilder()   //数値を初期化
        }

        //backSpace
        val backSpaceButton:Button = findViewById(R.id.backSpaceButton)
        backSpaceButton.setOnClickListener {

            //val data = StringBuilder(numberList.last())
            val data = if(numberList.isNotEmpty()) StringBuilder(numberList.last()) else return@setOnClickListener
            if(data.isBlank()){
                numberList.remove(numberList.last())
                //numbers = StringBuilder()
            }else{
                val tx = data.deleteCharAt(data.length - 1)
                numberList.remove(numberList.last())
                if(!tx.isBlank()){
                    numberList.add(tx.toString())
                }

                numbers = StringBuilder()
            }
            displayNumberList()
        }

        fun opList(op: String){
            if(numberList.size >= 2){
                val num1 = numberList[numberList.lastIndex -1]  //前
                val num2 = numberList[numberList.lastIndex] //後
                numberList.remove(numberList.last())
                numberList[numberList.lastIndex] =  calc.calc(num1 = num1, num2 = num2, op = op).toString()

                displayNumberList()
            }

        }

        //足し算
        val plusButton: Button = findViewById(R.id.plusButton)
        plusButton.setOnClickListener {
            opList("+")
        }

        //引き算
        val minusButton: Button = findViewById(R.id.minusButton)
        minusButton.setOnClickListener {
            opList("-")
        }

        //掛け算
        val addButton: Button = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            opList("*")
        }

        //割り算
        val divisionButton:Button = findViewById(R.id.divisionButton)
        divisionButton.setOnClickListener{
            opList("/")
        }


    }

    private fun applyNumber(s: String, numbers: StringBuilder, numberList: ArrayList<String>){
        if(numbers.isBlank()){
            numbers.append(s)
            numberList.add(numbers.toString())
        }else{
            numbers.append(s)
            numberList[numberList.lastIndex] = numbers.toString()
        }
        //displayNumberList()
    }
}
