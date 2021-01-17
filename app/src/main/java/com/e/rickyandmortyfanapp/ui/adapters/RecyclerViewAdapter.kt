package com.e.rickyandmortyfanapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.rickyandmortyfanapp.R
import com.e.rickyandmortyfanapp.extensions.ctx
import com.e.rickyandmortyfanapp.model.Characters
import com.e.rickyandmortyfanapp.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

class RecyclerViewAdapter(private val characterList: Characters) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindCharacter(characterList.results[position])
    }

    override fun getItemCount(): Int = characterList.results.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bindCharacter (character: Result) {
            with(character){
                Picasso.get().load(image).into(itemView.image)
                itemView.name.text = name.orEmpty()
                itemView.origin.text = origin.name.orEmpty()
                itemView.species.text = species.orEmpty()
                itemView.status.text = status.orEmpty()
                itemView.type.text = type.orEmpty()
                itemView.location.text = location.name.orEmpty()
            }
        }
    }
}