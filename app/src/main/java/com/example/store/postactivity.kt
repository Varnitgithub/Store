package com.example.store

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.store.databinding.ActivityPostactivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class postactivity : AppCompatActivity() {
    lateinit var bitmap: Bitmap

    //  lateinit var binding: ActivityPostactivityBinding
    lateinit var binding: ActivityPostactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postactivity)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_postactivity)
        val localdataitem =
            dataitemItem(
                binding.category.toString(),
                binding.description.toString(),
                binding.id.toString().toInt(),
                bitmap.toString(), binding.price.toString().toDouble(),
                binding.title.toString()
            )

        binding.postbutton.setOnClickListener {
            dataobject.newobj.postdata(localdataitem)
                .enqueue(object : Callback<ArrayList<dataitemItem>?> {
                    override fun onResponse(
                        call: Call<ArrayList<dataitemItem>?>,
                        response: Response<ArrayList<dataitemItem>?>,
                    ) {
                        Toast.makeText(
                            this@postactivity,
                            "${response.code()} is the posting code",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onFailure(call: Call<ArrayList<dataitemItem>?>, t: Throwable) {
                        Toast.makeText(this@postactivity, "failed", Toast.LENGTH_SHORT).show()
                    }
                })
        }
        CoroutineScope(Dispatchers.Default).launch {
            binding.updatebutton.setOnClickListener {
                dataobject.newobj.putdata(5, localdataitem)
                    .enqueue(object : Callback<ArrayList<dataitemItem>?> {
                        override fun onResponse(
                            call: Call<ArrayList<dataitemItem>?>,
                            response: Response<ArrayList<dataitemItem>?>,
                        ) {
                            Toast.makeText(
                                this@postactivity,
                                "${response.code()} is the posting code",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onFailure(call: Call<ArrayList<dataitemItem>?>, t: Throwable) {
                            Toast.makeText(this@postactivity, "failed", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }

        binding.patchbutton.setOnClickListener {
            dataobject.newobj.patchdata(5, localdataitem)
                .enqueue(object : Callback<ArrayList<dataitemItem>?> {
                    override fun onResponse(
                        call: Call<ArrayList<dataitemItem>?>,
                        response: Response<ArrayList<dataitemItem>?>,
                    ) {
                        Toast.makeText(
                            this@postactivity,
                            "${response.code()} is the posting code",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onFailure(call: Call<ArrayList<dataitemItem>?>, t: Throwable) {
                        Toast.makeText(this@postactivity, "failed", Toast.LENGTH_SHORT).show()
                    }
                })
        }
        binding.choosebutton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 11)
        }
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("SuspiciousIndentation")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 11 && resultCode == Activity.RESULT_OK && data != null) {
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data.data)
            binding.image.setImageBitmap(bitmap)
        }
    }
}