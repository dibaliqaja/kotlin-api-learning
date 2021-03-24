package com.example.kotlinapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinapi.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
        getDataFromAPI()
    }

    private fun setupRecyclerView() {
        mainAdapter = MainAdapter(arrayListOf(), object : MainAdapter.OnAdapterListener {
            override fun onClick(result: MainModel.Result) {
//              Toast.makeText(applicationContext, result.title, Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(applicationContext, DetailActivity::class.java)
                        .putExtra("intent_title", result.title)
                        .putExtra("intent_image", result.image)
                )
            }

        })
        rv_home.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mainAdapter
        }
    }

    private fun getDataFromAPI() {
        progress_bar.visibility = View.VISIBLE
        ApiService.endpoint.getData()
            .enqueue(object: Callback<MainModel> {
                override fun onResponse(
                    call: Call<MainModel>,
                    response: Response<MainModel>
                ) {
                    if (response.isSuccessful) {
                        progress_bar.visibility = View.GONE
                        showData(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    progress_bar.visibility = View.GONE
                    printLog("onFailure: $t")
                }

            })
    }

    private fun printLog(message: String) {
        Log.d(TAG, message)
    }

    private fun showData(data: MainModel) {
        val results = data.result
        mainAdapter.setData(results)
//        for (result in results) {
//            printLog("ID: ${result.id}")
//            printLog("Title: ${result.title}")
//            printLog("Image: ${result.image}")
//        }
    }
}