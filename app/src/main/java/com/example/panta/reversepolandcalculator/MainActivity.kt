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
                //随時更新する

                if(numbers.isBlank()){
                    numbers.append(n.toString())
                    numberList.add(numbers.toString())
                }else{
                    numbers.append(n.toString())
                    numberList[numberList.lastIndex] = numbers.toString()
                }
                displayNumberList()


            }
        }

        val enterButton: Button = findViewById(R.id.enterButton)
        enterButton.setOnClickListener {
            displayNumberList()
            numbers = StringBuilder()   //数値を初期化
        }

        fun popList(op: String){
            if(numberList.size >= 2){
                val num1 = numberList[numberList.lastIndex -1]
                val num2 = numberList[numberList.lastIndex]
                numberList[numberList.lastIndex - 1] =  calc.calc(num1 = num1, num2 = num2, op = op).toString()
                numberList.remove(numberList.last())
                displayNumberList()
            }

        }

        //足し算
        val plusButton: Button = findViewById(R.id.plusButton)
        plusButton.setOnClickListener {
            popList("+")
        }

        //引き算
        val minusButton: Button = findViewById(R.id.minusButton)
        minusButton.setOnClickListener {
            popList("-")
        }

        //掛け算
        val addButton: Button = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            popList("*")
        }

        val divisionButton:Button = findViewById(R.id.divisionButton)
        divisionButton.setOnClickListener{
            popList("/")
        }

    }
}
