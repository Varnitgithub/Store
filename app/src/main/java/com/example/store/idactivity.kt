package com.example.store

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.store.databinding.ActivityIdactivityBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class idactivity : AppCompatActivity() {
    lateinit var animation: Animation
    lateinit var binding: ActivityIdactivityBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_idactivity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_idactivity)
animation = AnimationUtils.loadAnimation(this,R.anim.scaleanimation)




        binding.newUpdate.setOnClickListener {
            dataobject.newobj.idgetdata(binding.idForUpdate.text.toString().toInt() ).enqueue(object : Callback<dataitemItem?> {
                override fun onResponse(
                    call: Call<dataitemItem?>,
                    response: Response<dataitemItem?>,
                ) {
                    binding.getcategory.text = response.body()?.category
                    binding.getid.text = response.body()?.id.toString()
                    binding.gettitle.text = response.body()?.title
                    binding.getdescription.text = response.body()?.description
                    binding.getprice.text = response.body()?.price.toString()
              Glide.with(this@idactivity).load(response.body()?.image).into(binding.getnewimageofget)
                    binding.linearlayout.startAnimation(animation)
               }
                override fun onFailure(call: Call<dataitemItem?>, t: Throwable) {
                    Toast.makeText(this@idactivity, "failed", Toast.LENGTH_SHORT).show()
                }
            })
        }


    }
}