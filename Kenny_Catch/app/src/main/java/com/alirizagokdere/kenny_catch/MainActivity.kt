package com.alirizagokdere.kenny_catch

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.alirizagokdere.kenny_catch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var score = 0

    val imageArray = ArrayList<ImageView>()


    var runnable : Runnable = Runnable{}
    var handler : Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // ImageArray
        imageArray.add(binding.imageView01)
        imageArray.add(binding.imageView02)
        imageArray.add(binding.imageView03)
        imageArray.add(binding.imageView04)
        imageArray.add(binding.imageView05)
        imageArray.add(binding.imageView06)
        imageArray.add(binding.imageView07)
        imageArray.add(binding.imageView08)
        imageArray.add(binding.imageView09)
        imageArray.add(binding.imageView10)
        imageArray.add(binding.imageView11)
        imageArray.add(binding.imageView12)

        invisImages()



        object : CountDownTimer(10500,1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.textViewTime.text = "Time : ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                binding.textViewTime.text = "Time = 0"
                handler.removeCallbacks(runnable)

                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over!")
                alert.setMessage("Restart The Game ?")
                alert.setPositiveButton("Yes",DialogInterface.OnClickListener{
                    dialogInterface, i ->
                    // restart
                    val intentFromMain = intent
                    finish()
                    startActivity(intentFromMain)
                })
                alert.setNegativeButton("No", DialogInterface.OnClickListener {
                    dialogInterface, i ->
                    Toast.makeText(this@MainActivity,"Game Over!", Toast.LENGTH_LONG).show()
                })
                alert.show()
            }
        }.start()
    }

    fun invisImages() {

        runnable = object :Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                val random = java.util.Random()
                val randomIndex = random.nextInt(imageArray.size)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }

    fun scoreArttir(view: View){
        score = score +1
        binding.textViewScore.text = "Score : $score"

    }


}


