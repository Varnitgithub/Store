package com.example.store

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import android.widget.VideoView
import androidx.core.graphics.createBitmap
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("StaticFieldLeak")
lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity(), adapter.myinterface {
    lateinit var animation: Animation
    lateinit var mAdapter:adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
animation = AnimationUtils.loadAnimation(this,R.anim.scaleanimation)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        binding.imagePost.setOnClickListener {
            startActivity(Intent(this@MainActivity,transferactivity::class.java))
        }
         mAdapter = adapter(this, this)
        binding.recyclerview.adapter = mAdapter

        CoroutineScope(Dispatchers.IO).launch {
            dataobject.newobj.getdata().enqueue(object : Callback<ArrayList<dataitemItem>?> {
                override fun onResponse(
                    call: Call<ArrayList<dataitemItem>?>,
                    response: Response<ArrayList<dataitemItem>?>,
                ) {
                    mAdapter.updatelist(response.body()!!)

                }

                override fun onFailure(call: Call<ArrayList<dataitemItem>?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "failed", Toast.LENGTH_SHORT).show()
                }
            })
        }

        }

            override fun onclick(dataItem: dataitemItem) {
                Toast.makeText(this, "you clicked on Item", Toast.LENGTH_SHORT).show()
            }
        }

