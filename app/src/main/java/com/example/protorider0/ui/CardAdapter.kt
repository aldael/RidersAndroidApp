package com.example.protorider0.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.protorider0.R
import com.example.protorider0.model.Rider
import jp.wasabeef.glide.transformations.CropTransformation
import com.example.protorider0.ui.extra.OnItemClickListener


class CardAdapter(private val listener: OnItemClickListener): RecyclerView.Adapter<CardViewHolder>(){

    var riders : MutableList<Rider> = ArrayList<Rider>()
    var anotherRiders: MutableList<Rider> = ArrayList<Rider>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.rider_card, parent, false)
        return CardViewHolder(layoutInflater)

    }

    override fun getItemCount(): Int = riders.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        val cropTransformation = CropTransformation(
            200,
            200,
            CropTransformation.CropType.TOP
        )
        val rider = riders[position]
        holder.rider.text = rider.rider

        Glide.with(holder.itemView.context)
            .load(riders[position].armor)
            .placeholder(R.drawable.rider_pose)
            .apply(RequestOptions.bitmapTransform(cropTransformation))
            .into(holder.armor)

        Glide.with(holder.itemView.context)
            .load(riders[position].serie)
            .placeholder(R.drawable._0th_kr_mylogo)
            .into(holder.serie)

        holder.itemView.setOnClickListener{
            listener.onItemClick(rider)
        }

        // holder.buttonfav.isChecked = rider.isFav
    }

    fun Update(lista : MutableList<Rider>){
        riders.clear()
        riders.addAll(lista)

        notifyDataSetChanged()
    }

    /* fun filtro(anothers: List<Rider>) {
        anotherRiders.clear()
        anotherRiders.addAll(anothers)
        notifyDataSetChanged()
    } */
}

