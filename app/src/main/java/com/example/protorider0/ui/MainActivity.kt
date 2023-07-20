package com.example.protorider0.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.protorider0.R
import com.example.protorider0.model.Rider
import com.google.firebase.auth.FirebaseAuth
import com.example.protorider0.ui.extra.OnItemClickListener

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var rvRiders: RecyclerView
    private lateinit var adapter: CardAdapter

    private lateinit var firebaseAuth: FirebaseAuth
    private val progressDialog by lazy { CustomProgressDialog(this) }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        // checkUser()
        bindView()
        bindViewModel()
        val filter = findViewById<EditText>(R.id.filter)
        filter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val userFilter = p0.toString().lowercase()
                val anothers = adapter.riders
                val riderFilter = anothers.filter { hero -> hero.rider.lowercase().contains(userFilter) }.toMutableList()
                adapter.Update(riderFilter)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        // val buttonfav = findViewById<ImageButton>(R.id.favlist)
    }
    override fun onStart() {
        super.onStart()
        progressDialog.start("Henshin...")
        viewModel.onStart(this)
    }

    override fun onItemClick(rider: Rider) {
        var anotherRiders = ArrayList<Rider>()
        anotherRiders = adapter.riders as ArrayList<Rider>
        val intent = Intent(this, CartaRider::class.java)
        intent.putExtra("id", rider.id)
        intent.putExtra("lista", anotherRiders)
        startActivity(intent)
        Log.d("DEMO", "listener on")
    }

    private fun bindView() {
        rvRiders = findViewById(R.id.rvRider)
        rvRiders.layoutManager = LinearLayoutManager(this)
        adapter = CardAdapter(this)
        rvRiders.adapter = adapter
    }

    private fun bindViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.riders.observe(this) {
            adapter.Update(it)
            progressDialog.stop()
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}