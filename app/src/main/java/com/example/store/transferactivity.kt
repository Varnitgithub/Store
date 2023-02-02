package com.example.store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class transferactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transferactivity)
    }

    fun updatemydata(view: View) {
        startActivity(Intent(this@transferactivity,postactivity::class.java))
    }

    fun getmydatabyid(view: View) {
        startActivity(Intent(this@transferactivity,idactivity::class.java))
    }
    fun deletemydata(view: View) {
        startActivity(Intent(this@transferactivity,delete_data::class.java))
    }
}