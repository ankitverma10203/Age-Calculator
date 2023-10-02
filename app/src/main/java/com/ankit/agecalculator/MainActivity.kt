package com.ankit.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.datePicker)

        button.setOnClickListener {
            onClickHandler()
        }
    }

    fun onClickHandler() {
        val myCalendar = Calendar.getInstance()
        var year = myCalendar.get(Calendar.YEAR)
        var month = myCalendar.get(Calendar.MONTH)
        var day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, selectedDay ->
            Toast.makeText(this,"Date Selected!", Toast.LENGTH_SHORT).show()

            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            val tvSelectedDate = findViewById<TextView>(R.id.selectedDate)
            tvSelectedDate.text= selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val date = sdf.parse(selectedDate)

            date?.let {
                val seconds = (Date().time - date.time)/1000;
                val minutes = seconds / 60
                val hours = minutes / 60
                val days = hours / 24

                val ageInDays = findViewById<TextView>(R.id.ageInDays)
                ageInDays.text="$days"

                val ageInHours = findViewById<TextView>(R.id.ageInHours)
                ageInHours.text="$hours"

                val ageInMinutes = findViewById<TextView>(R.id.ageInMinutes)
                ageInMinutes.text="$minutes"

                val ageInSeconds = findViewById<TextView>(R.id.ageInSeconds)
                ageInSeconds.text="$seconds"
            }

        }, year, month, day)

        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()

    }
}