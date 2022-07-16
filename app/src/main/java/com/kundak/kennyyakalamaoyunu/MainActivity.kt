package com.kundak.kennyyakalamaoyunu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.kundak.kennyyakalamaoyunu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Toast.makeText(applicationContext,"Welcome",Toast.LENGTH_LONG).show()

    }
    fun Next(view: View)
    {
         val intent = Intent(applicationContext,GameScreen::class.java)
        startActivity(intent)
    }
    fun Exit(view : View)
    {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Exit")
        alert.setMessage("Emin misiniz ? ")
        alert.setPositiveButton("Yes"){
                dialog, w-> finishAffinity()
                            System.exit(0)
        }
        alert.setNegativeButton("No"){dialog, w->
            dialog.cancel()
        }
        alert.show()
    }
}