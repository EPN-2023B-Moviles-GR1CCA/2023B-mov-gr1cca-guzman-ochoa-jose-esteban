package com.example.joseguzmanapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class BListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            R.id.mi_editar -> {
                Log.i("list-view", "Editar ${item?.groupId}")
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("list-view", "Eliminar ${item?.groupId}")
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}