package com.kundak.kennyyakalamaoyunu

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.kundak.kennyyakalamaoyunu.databinding.ActivityMainBinding
import com.kundak.kennyyakalamaoyunu.databinding.ActivityOyunEkraniBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random.Default.nextInt

class GameScreen : AppCompatActivity() {
    private lateinit var binding: ActivityOyunEkraniBinding
    private var runnable : Runnable = Runnable { }
    var runnable1 : Runnable = Runnable { }
    var handler = Handler()

    private lateinit var sharedPreferences: SharedPreferences
    private var highScore : Int ? =null

    var number = 11
    var scoreNumber = 0
    var imageArray = ArrayList<ImageView>()
    var random = Random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOyunEkraniBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sharedPreferences = this.getSharedPreferences("com.kundak.kennyyakalamaoyunu", MODE_PRIVATE)
        highScore = sharedPreferences.getInt("score",0)
        if (highScore==0)
        {
         binding.highTextView.text ="High Score : 0"
        }
        else {
            binding.highTextView.text ="High Score : $highScore"
        }
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        imageArray.add(binding.imageView10)
        imageArray.add(binding.imageView11)
        imageArray.add(binding.imageView12)
        imageArray.add(binding.imageView13)
        imageArray.add(binding.imageView14)
        imageArray.add(binding.imageView15)
        imageArray.add(binding.imageView16)
        imageArray.add(binding.imageView17)
        imageArray.add(binding.imageView18)
        imageArray.add(binding.imageView19)
        imageHide()

    }

    override fun onStart() {
        super.onStart()
        Start()
    }

    fun imageHide() {
        runnable1 = object : Runnable {
            override fun run() {
                handler.postDelayed(this, 500)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                if (number != 0) {
                    var randomIndex = random.nextInt(16)
                    imageArray[randomIndex].visibility = View.VISIBLE
                }
            }
        }
        handler.post(runnable1)
    }

    fun Game(view: View) {
            scoreNumber++
            binding.scoreTextView.text = "Score : $scoreNumber"
    }


    fun Start() {
        binding.scoreTextView.text = "Score : 0"
        runnable = object : Runnable {
            override fun run() {
                handler.postDelayed(this, 1000)
                number--
                binding.timeTextView.text = "Time : $number"

                val alert = AlertDialog.Builder(this@GameScreen)

                if (number == 0) {
                    if(highScore!! < scoreNumber)
                    {
                        highScore = scoreNumber
                        binding.highTextView.text = "High Score : "+ highScore.toString()
                        sharedPreferences.edit().putInt("score",highScore!!).apply()

                    }
                    else
                    {
                        binding.highTextView.text ="High Score : $highScore"

                    }

                    handler.removeCallbacks(runnable)

                    alert.setTitle("Restart")
                    alert.setMessage("Skorunuz:${scoreNumber}\nTekrar oynamak ister misiniz ?")
                    alert.setPositiveButton("Yes") { dialog, w ->
                        number = 11
                        scoreNumber = 0

                        Start()

                    }
                    alert.setNegativeButton("No") { dialog, w ->
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    }
                    alert.show()
                }
            }
        }
        handler.post(runnable)
    }

}


