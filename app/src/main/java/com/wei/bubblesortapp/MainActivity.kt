package com.wei.bubblesortapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wei.bubblesortapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //Declare a top-level variable in the class for the binding object
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initialize the binding object, which you'll use to access Views in the activity_main.xml layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Set the content view of the activity. The following line of code specifies the root of the view hierarchy in the app
        setContentView(binding.root)
        binding.sortButton.setOnClickListener {
            val userInput = binding.numbersInput.text.toString()
            if (!userInput.matches("[0-9]+".toRegex())) {
                binding.numbersInput.error = "Only digits 0-9 are allowed."
                return@setOnClickListener
            }
            if (userInput.length < 3 || userInput.length > 8) {
                binding.numbersInput.error = "Enter 3-8 numbers."
                return@setOnClickListener
            }
            val numbersArray = userInput.split("\\s+".toRegex()) // Split by whitespace
                .mapNotNull { it.toIntOrNull() }
                .toMutableList()
            bubbleSort(numbersArray)
        }
    }

//    private fun validateInput() {
//        val userInput = binding.numbersInput.text.toString()
//        if (userInput.length < 3 || userInput.length > 8) {
//            binding.numbersInput.error = "Enter 3-8 numbers."
//            return
//        }
//        if (!userInput.matches("[0-9]+".toRegex())) {
//            binding.numbersInput.error = "Only digits 0-9 are allowed."
//            return
//        }
//        Toast.makeText(this, "Input is valid: $userInput ", Toast.LENGTH_LONG).show()
//    }

    private fun bubbleSort(arr:MutableList<Int>) {
        val n = arr.size
        for(i in 0 until n) {
            binding.textViewOutput.append("Iteration ${i + 1} \n")
            for(j in n - 1  downTo  i + 1) {
                // Print the array after each loop iteration
                printIteration(arr, i)
                if(arr[j] < arr[j - 1]) {
                    // Swap the elements
                    val temp = arr[j]
                    arr[j] = arr[j - 1]
                    arr[j - 1] = temp
                }
                if(j == i + 1) {
                    printIteration(arr, i)
                }
            }
        }
    }

    private fun printIteration(arr: List<Int>, iteration: Int) {
        val output = "${arr.joinToString(" ")}\n"
        // Append the output to the TextView
        binding.textViewOutput.append(output)
    }
}