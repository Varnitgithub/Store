package com.example.store

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class delete_data : AppCompatActivity() {
    lateinit var animation: Animation

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animation = AnimationUtils.loadAnimation(this, R.anim.anim)
        setContentView(R.layout.activity_delete_data)
        val newdelete: Button = findViewById(R.id.newDelete)
        val idfordelete: EditText = findViewById(R.id.idForDelete)
        val textfordelete: TextView = findViewById(R.id.textfordelete)
        //val getidstring = idfordelete.text.toString().toInt()
        CoroutineScope(Dispatchers.Main).launch {
            newdelete.setOnClickListener {
                dataobject.newobj.deletedata(idfordelete.text.toString().toInt())
                    .enqueue(object : Callback<Unit?> {
                        override fun onResponse(call: Call<Unit?>, response: Response<Unit?>) {
                            textfordelete.text = "Deleted Successfully"
                            textfordelete.startAnimation(animation)
                        }

                        override fun onFailure(call: Call<Unit?>, t: Throwable) {
                            Toast.makeText(this@delete_data, "failed", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }

    }
}