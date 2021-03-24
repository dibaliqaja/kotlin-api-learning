package com.example.kotlinapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar!!.title = intent.getStringExtra("intent_title")

        Glide.with(this)
            .load(intent.getStringExtra("intent_image"))
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(iv_detail)
    }
}