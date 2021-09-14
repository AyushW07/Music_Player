package com.example.music_player

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val player = MediaPlayer.create(this, R.raw.songa)

        val playbtn: ImageView = findViewById(R.id.playbtn)

        val prevbtn: ImageView = findViewById(R.id.prevbtn)

        val nextbtn: ImageView = findViewById(R.id.nextbtn)

        val seekBar: SeekBar = findViewById(R.id.seekBar)

        var isPlaying: Boolean = false

        var songNumber: Int=1

        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object: Runnable{
            override fun run() {
                val total_duration=player.duration/1000
                val remaining_duration = (player.duration - player.currentPosition)/1000
                val passed_durationn = total_duration - remaining_duration

                seekBar.progress = passed_durationn

                mainHandler.postDelayed(this,1000)
            }
        })

        playbtn.setOnClickListener{


            if (player.isPlaying)
            {
                player.pause()
                isPlaying=false
                playbtn.setImageResource(R.drawable.play)
            }
            else{
                player.start()
                isPlaying=true
                playbtn.setImageResource(R.drawable.stop)
            }

            seekBar.max = player.duration / 1000
        }

        prevbtn.setOnClickListener{
            if (songNumber>=1)
                songNumber--
            player.start()
            isPlaying=true
            playbtn.setImageResource(R.drawable.stop)

        }

        nextbtn.setOnClickListener{
            if (songNumber<=3)
                songNumber++
            player.start()
            isPlaying=true
            playbtn.setImageResource(R.drawable.stop)

        }

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
               if (player.isPlaying)
               {
                   player.seekTo(progress*1000)
               }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })


    }
}