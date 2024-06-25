package com.example.retrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val call = RetrofitInstance.apiInterface.getUser()
        call.enqueue(object : Callback<List<ModelReqItem>> {
            override fun onResponse(call: Call<List<ModelReqItem>>, response: Response<List<ModelReqItem>>) {
                if (response.isSuccessful) {
                    response.body()?.let { userList ->
                        for (user in userList) {
                          val text =   Log.d("MainActivity", "User: ${user.title}")
                            binding.tv.text = text.toString()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<ModelReqItem>>, t: Throwable) {
                Log.e("MainActivity", "Error fetching users", t)
            }
        })
    }
}
