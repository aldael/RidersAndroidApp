package com.example.protorider0.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.protorider0.R
import com.example.protorider0.model.Rider

class CartaRider: AppCompatActivity() {
    private lateinit var textRider: TextView
    private lateinit var textIdentidad: TextView
    private lateinit var imageRider: ImageView
    private lateinit var backbutton: ImageButton
    // private lateinit var favbutton: CheckBox
    private var riderList: List<Rider> = emptyList()

    // private val favRiders = mutableListOf<Rider>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.raidaa_card)

        riderList = intent.getSerializableExtra("lista") as? List<Rider> ?: emptyList()
        textRider = findViewById(R.id.textView)
        textIdentidad = findViewById(R.id.identidad)
        imageRider = findViewById(R.id.rider_image)
        backbutton = findViewById(R.id.BackButton)
        backbutton.setOnClickListener{
            onBackPressed()
        }
        val riderId = intent.getIntExtra("id", 74)
        Log.d("DEMO", "$riderId")
        val rider = riderList.find { it.id == riderId }

        if (rider != null) {
            textRider.text = rider.rider
            textIdentidad.text = rider.identidad
            Glide.with(this).load(rider.armor).into(imageRider)


        }
        /*
        val button = findViewById<CheckBox>(R.id.buttonfav)
        button.setOnCheckedChangeListener { _, isChecked ->
            rider?.isFav = isChecked
            if(isChecked) {
                favRiders.add(rider!!)
            } else {
                favRiders.remove(rider)
            }
        }
         val intent = Intent(this, RidersFav::class.java)
        intent.putExtra("favs", ArrayList(favRiders))
        startActivity(intent)
        Log.d("DEMO", "funciono") */


    }

}