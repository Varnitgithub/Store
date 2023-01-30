package com.example.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity(), adapter.myinterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter(this, this)

        dataobject.newobj.getdata().enqueue(object : Callback<dataitem?> {
            override fun onResponse(call: Call<dataitem?>, response: Response<dataitem?>) {
               response.body()
            }
            override fun onFailure(call: Call<dataitem?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onclick(dataItem: dataitemItem) {
        Toast.makeText(this, "you clicked on Item", Toast.LENGTH_SHORT).show()
    }
}
