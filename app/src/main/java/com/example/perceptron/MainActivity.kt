package com.example.perceptron

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSolveClick(view: View) {
        if (editRate.text.isEmpty() ||
            editIterations.text.isEmpty() ||
            editThreshold.text.isEmpty()) {
            return
        }

        val data = listOf(
            listOf(0.0, 0.0) to checkBoxA.isChecked(),
            listOf(1.0, 1.5) to checkBoxC.isChecked(),
            listOf(2.0, 3.0) to checkBoxB.isChecked(),
            listOf(5.0, 4.0) to checkBoxD.isChecked()
        )
        val rate = editRate.text.toString().toDouble()
        val threshold = editThreshold.text.toString().toDouble()
        val iterations = editIterations.text.toString().toInt()
        val (results, weights) = solve(data, rate, threshold, iterations)

        resultsView.text = "Results:\nClasses: " + results.joinToString(", ") + "\nWeights: " + weights.joinToString(", ")
    }
}