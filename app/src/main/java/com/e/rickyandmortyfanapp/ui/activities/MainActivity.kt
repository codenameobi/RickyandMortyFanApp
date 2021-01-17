package com.e.rickyandmortyfanapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.rickyandmortyfanapp.R
import com.e.rickyandmortyfanapp.ui.adapters.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = RecyclerViewAdapter()
    }
}