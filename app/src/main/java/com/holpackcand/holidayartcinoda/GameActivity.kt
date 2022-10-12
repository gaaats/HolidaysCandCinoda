package com.holpackcand.holidayartcinoda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.holpackcand.holidayartcinoda.databinding.ActivityGameBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {

    private var backPressedTime: Long = 0
    private var _binding: ActivityGameBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")

    private val mapInterestingFacts = mapOf(
        "Canada is a monarchy" to "Yep, Canada has a Queen/King. It’s the same as the UK’s – Queen Elizabeth II (now King Charles III). That’s because Canada is a member of the British Commonwealth, having formerly been a colony of the British Empire.",
        "There are two official languages in Canada." to "Britain wasn’t the only power-hungry colonial power at the time of Canada’s pioneering days. There was also France, who once owned much of North America for a while.",
        "Canada is the world’s second largest country" to "This is one of those super important Canada facts to know! You may have been thinking of China, or maybe the USA, but no – it’s Canada. How big are we talking? 9.98 million square miles. Of course, just in case you were wondering, Russia is the biggest",
        "Canada has the longest coastline in the world." to "There are so many reasons to visit Canada, and this is one of them! Have you seen a map of Canada? All those islands and jagged, irregular lines that mark where the land ends? There’s a lot of that kind of thing going on. It all adds up to the longest coastline in the world at over 125,500 miles. This one is another one of my favorite Canada facts!",
        "Canada also has the longest international border." to "This giant country’s only land neighbor is the United States, and since they’re both pretty big, you can expect that border to be big, too. It’s the longest border between two countries (well, the southern border) at a very long 5,525 miles.",
        "The word Canada is derived from an indigenous word." to "And that word is kanata, which means “settlement” or “village” in the language of the St. Lawrence Iroquoians. Sadly, these indigenous people disappeared in the 16th century, during wars with the Mohawk who wanted a monopoly on trade with Europeans at the time.",
        "Canada’s national animal is actually a beaver." to "It played a pretty crucial role in the history of Canada, what with all the fur trappers, traders, and battles over who would be the fur king of the region. ",
    )

    val listImages = listOf(
        R.drawable.one,
        R.drawable.tvo,
        R.drawable.three,
        R.drawable.four,
        R.drawable.five,
        R.drawable.six,
        R.drawable.seven,
        R.drawable.eight,
        R.drawable.nine,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAndSetRandomFact()
        binding.btnGenerateNextFact.setOnClickListener {
            getAndSetRandomFact()
        }

        binding.btnImgExit.setOnClickListener {
            initAlertDialog()
        }
    }

    private fun getAndSetRandomFact() {
        lifecycleScope.launch {
            binding.lottieAnimVaiting.visibility = View.VISIBLE
            binding.tvPleaseWaitLoading.visibility = View.VISIBLE

            binding.tvFactTitle.visibility = View.INVISIBLE
            binding.tvFactAbout.visibility = View.INVISIBLE
            binding.imgFactLogo.visibility = View.INVISIBLE
            binding.btnImgExit.visibility = View.INVISIBLE
            binding.tvTitleCountry.visibility = View.INVISIBLE
            binding.btnGenerateNextFact.visibility = View.INVISIBLE

            delay(3300)

            val factsTitle = mapInterestingFacts.keys
            val currentFactName = factsTitle.random()
            val currentFactText = mapInterestingFacts[currentFactName]
            val currentImg = listImages.random()

            binding.imgFactLogo.setImageResource(currentImg)
            binding.tvFactTitle.text = currentFactName
            binding.tvFactAbout.text = currentFactText


            binding.lottieAnimVaiting.visibility = View.INVISIBLE
            binding.tvPleaseWaitLoading.visibility = View.INVISIBLE

            binding.tvFactTitle.visibility = View.VISIBLE
            binding.tvFactAbout.visibility = View.VISIBLE
            binding.imgFactLogo.visibility = View.VISIBLE
            binding.btnImgExit.visibility = View.VISIBLE
            binding.tvTitleCountry.visibility = View.VISIBLE
            binding.btnGenerateNextFact.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onBackPressed() {
        try {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Snackbar.make(binding.root, "Press back again to exit", Snackbar.LENGTH_SHORT)
                    .show()
            }
            backPressedTime = System.currentTimeMillis()
            return
        } catch (e: Throwable) {
            Toast.makeText(this, "There is some error, try again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("Exit")
            .setMessage("Are you definitely want to log out, the current data will not be saved?")
            .setPositiveButton("Yes, Exit") { _, _ ->
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .setNegativeButton("Deny") { _, _ ->
            }
            .setCancelable(true)
            .create()
            .show()
    }
}