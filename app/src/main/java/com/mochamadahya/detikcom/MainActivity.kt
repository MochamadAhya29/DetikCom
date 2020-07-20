package com.mochamadahya.detikcom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mochamadahya.detikcom.adapter.RvNewsAdapter
import com.mochamadahya.detikcom.databinding.ActivityMainBinding
import retrofit2.Call
import com.mochamadahya.detikcom.model.ResponseNews
import com.mochamadahya.detikcom.service.RetrofitBuilder
import retrofit2.Callback
import timber.log.Timber

import retrofit2.Response

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val rvAdapter = RvNewsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding){
            setContentView(root)
//            setSupportActionBar(toolbar)
            mainRv.adapter = rvAdapter
            mainRv.layoutManager = LinearLayoutManager(baseContext)
            mainRv.setHasFixedSize(true)
        }

        val call = RetrofitBuilder.getService().feachHeadLines()
        call.enqueue(object : Callback<ResponseNews> {
            override fun onFailure(call: Call<ResponseNews>, t: Throwable){
                Timber.e(t.localizedMessage)
            }
            override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>){
                Timber.d(response.body()?.totalResults.toString())
                val listArticlesItem = response.body()?.articles
                listArticlesItem.let {
                    it?.let { it1 -> rvAdapter.addData(it1) }
                }
            }
        })
    }
}