package com.example.store

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.store.databinding.ActivityPostactivityBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class postactivity : AppCompatActivity() {
    lateinit var bitmap: Bitmap
    lateinit var binding: ActivityPostactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postactivity)

        val name = 111


        binding = DataBindingUtil.setContentView(this, R.layout.activity_postactivity)
        val category = binding.category.text.toString().trim()
        val description = binding.description.text.toString().trim()
        val id = binding.id.text.toString().trim()
        val price = binding.price.text.toString().trim()
        val title = binding.title.text.toString().trim()

        binding.postbutton.setOnClickListener {
            /*startActivity(Intent(this,MainActivity::class.java)
                .putExtra("category", category)
                .putExtra("id", id)
                .putExtra("price", price)
                .putExtra("title", title)
                .putExtra("description", description)
                .putExtra("image","varnit"))
            startActivity(intent)*/
            val localdataitem = dataitemItem("category","description",3,"image",1200.00,"title")

            dataobject.newobj.postdata(localdataitem).enqueue(object : Callback<ArrayList<dataitemItem>?> {
                override fun onResponse(
                    call: Call<ArrayList<dataitemItem>?>,
                    response: Response<ArrayList<dataitemItem>?>,
                ) {
                    Toast.makeText(this@postactivity, "${response.code()} is the posting code", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<ArrayList<dataitemItem>?>, t: Throwable) {
                    Toast.makeText(this@postactivity, "failed", Toast.LENGTH_SHORT).show()
                }
            })

        }

        binding.choosebutton.setOnClickListener {
            val intent  = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,11)
        }
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("SuspiciousIndentation")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==11&&resultCode== Activity.RESULT_OK&&data!=null){
         bitmap  = MediaStore.Images.Media.getBitmap(contentResolver,data.data)
            binding.image.setImageBitmap(bitmap)
        }
    }


}