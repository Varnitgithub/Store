package com.example.store

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.VideoView
import androidx.core.graphics.createBitmap
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("StaticFieldLeak")
lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity(), adapter.myinterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        /*val category = intent.getStringExtra("category").toString()
       // val id = intent.getStringExtra("id").toString().toInt()
        val price = intent.getStringExtra("price").toString().toDouble()
        val title = intent.getStringExtra("title").toString()
        val description = intent.getStringExtra("description").toString()
        val image = intent.getStringExtra("image").toString()*/
        //val dataItem = dataitemItem(category, description, id, image, price, title)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        binding.imagePost.setOnClickListener {
            val intent = Intent(this, postactivity::class.java)
            startActivity(intent)
        }
        val mAdapter = adapter(this, this)
        binding.recyclerview.adapter = mAdapter

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
        /* dataobject.newobj.postdata(dataItem).enqueue(object : Callback<dataitem?> {
             override fun onResponse(call: Call<dataitem?>, response: Response<dataitem?>) {
                 mAdapter.updatelist(response.body()!!)
             }
             override fun onFailure(call: Call<dataitem?>, t: Throwable) {
                 Toast.makeText(this@MainActivity, "failed", Toast.LENGTH_SHORT).show()
            }
         })*/

        binding.deletebutton.setOnClickListener {
            dataobject.newobj.idgetdata(5).enqueue(object : Callback<ArrayList<dataitemItem>?> {
                override fun onResponse(
                    call: Call<ArrayList<dataitemItem>?>,
                    response: Response<ArrayList<dataitemItem>?>,
                ) {
                    Toast.makeText(this@MainActivity, "${response.code()}is my code", Toast.LENGTH_SHORT).show()
                   // mAdapter.updatelist(response.body()!!)
                }

                override fun onFailure(call: Call<ArrayList<dataitemItem>?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


            /*  binding.deletebutton.visibility = View.GONE
        if (binding.delId.text.isNotEmpty()){
            binding.deletebutton.visibility = View.VISIBLE*/

            /* binding.deletebutton.setOnClickListener {
            dataobject.newobj.deletedata(binding.delId.text.toString().toInt()).enqueue(object : Callback<Unit?> {
                override fun onResponse(call: Call<Unit?>, response: Response<Unit?>) {
                    Toast.makeText(this@MainActivity, "successfully deleted", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onFailure(call: Call<Unit?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "failed deleted", Toast.LENGTH_SHORT).show()
                }
            })
        }*/


            override fun onclick(dataItem: dataitemItem) {


                Toast.makeText(this, "you clicked on Item", Toast.LENGTH_SHORT).show()
                //Toast.makeText(this, "$category $id $price $title $description $image", Toast.LENGTH_SHORT).show()
            }
        }

