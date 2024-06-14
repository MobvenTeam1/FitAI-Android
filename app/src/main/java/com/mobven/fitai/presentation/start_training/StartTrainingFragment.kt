package com.mobven.fitai.presentation.start_training

import android.media.MediaPlayer
import android.os.CountDownTimer
import android.view.View
import com.mobven.fitai.R
import com.mobven.fitai.databinding.FragmentStartTrainingBinding
import com.mobven.fitai.presentation.base.BaseFragment
import java.util.concurrent.TimeUnit


class StartTrainingFragment : BaseFragment<FragmentStartTrainingBinding>(FragmentStartTrainingBinding::inflate) {

    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false
    private var timerPaused = false
    private var isPlaying = false
    private var timeLeftInMillis: Long = 61000
    override fun observeUi() {

        with(binding){
            val mediaPlayer = MediaPlayer.create(requireContext(),R.raw.lose_yourself)

            btnStartTraining.setOnClickListener {
                if (!isTimerRunning){
                    TrainingName.visibility = View.GONE
                    relativeDetails.visibility = View.GONE
                    tvReadyCounter.visibility = View.VISIBLE
                    gradientEffect.visibility - View.VISIBLE
                    btnStartTraining.setImageResource(R.drawable.pause_button)
                    startPreparationCountdown()
                }
            }

            btnPlayMusic.setOnClickListener {
                if (isPlaying) {
                    mediaPlayer.pause()
                    isPlaying = false
                    binding.btnPlayMusic.setImageResource(R.drawable.ic_play_borderless)
                } else {
                    mediaPlayer.start()
                    isPlaying = true
                    binding.btnPlayMusic.setImageResource(R.drawable.ic_pause)
                }
            }
        }
    }

    fun startPreparationCountdown() {
        val preparationTimer = object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                binding.tvReadyCounter.text = (secondsRemaining).toString()
                binding.btnStartTraining.setOnClickListener(null)
            }

            override fun onFinish() {
                binding.tvReadyCounter.visibility = View.GONE
                binding.tvCounter.visibility = View.VISIBLE
                binding.btnStartTraining.setOnClickListener {
                    if (timerPaused){
                        resumeTimer()
                    }else{
                        pauseTimer()
                    }
                }
                startTimer()
            }
        }
        preparationTimer.start()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                isTimerRunning = false
                timeLeftInMillis = 61000
            }
        }.start()
        isTimerRunning = true
    }

    private fun updateTimerText() {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeLeftInMillis)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(timeLeftInMillis) - TimeUnit.MINUTES.toSeconds(minutes)
        val timeFormatted = String.format("%02d:%02d", minutes, seconds)
        binding.tvCounter.text = timeFormatted
    }

    private fun pauseTimer() {
        timer.cancel()
        binding.btnStartTraining.setImageResource(R.drawable.play_button)
        timerPaused = true
    }

    private fun resumeTimer(){
        startTimer()
        binding.btnStartTraining.setImageResource(R.drawable.pause_button)
        timerPaused = false
    }

}