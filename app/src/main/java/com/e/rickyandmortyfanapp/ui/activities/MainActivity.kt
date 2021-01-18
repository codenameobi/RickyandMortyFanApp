package com.e.rickyandmortyfanapp.ui.activities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.rickyandmortyfanapp.R
import com.e.rickyandmortyfanapp.api.CharacterRetriever
import com.e.rickyandmortyfanapp.ui.adapters.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager = LinearLayoutManager(this)

        if(isNetworkConnected()){
            retrieveRepositories()
        }else{
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _-> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isNetworkConnected() : Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork

        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    fun retrieveRepositories() {
        val mainActivityJob = Job()

        val errorHandler = CoroutineExceptionHandler{ _, expection ->
            AlertDialog.Builder(this).setTitle("Error")
                .setMessage(expection.message)
                .setPositiveButton(android.R.string.ok) {_, _ ->}
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch (errorHandler){
            val resultList = CharacterRetriever().getCharacters()
            rv.adapter = RecyclerViewAdapter(resultList)
        }
    }
}