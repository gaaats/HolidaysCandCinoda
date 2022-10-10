package com.holpackapooolka.holidayartponddos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.holpackapooolka.holidayartponddos.databinding.ActivityGameBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {

    private var backPressedTime: Long = 0
    private var _binding: ActivityGameBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")

    private val mapInterestingFacts = mapOf(
        "Marie Curie was Polish" to "Marie Curie was a physicist and chemist who was the pioneer of the use of radioactivity in medicine. Marie was born and raised in Warsaw but couldn’t attend university there because she was a woman.",
        "Poland has lots of different festivals and celebrations" to "From Saint Dominic’s Fair which is a traditional fair and market that dates back to 1260 AD to the Wianki festival. The Wianki festival dates all the way back to the ancient period, back in the ancient period this festival marked the beginning of the summer solstice.",
        "The Polish alphabet has 32 letters" to "Unlike the English alphabet that is made up of only 26 letters, the Polish alphabet has 32! The alphabet is very similar to the English one as it based off of the Latin alphabet, however, there are a few extra letters that have diacritics which appear differently and require a different pronunciation. ",
        "Kraków gets its name from legend about dragon" to "One night a group of boys entered a cave and awoke an evil dragon that had terrorised the village years before. The dragon was angry and returned to the village, he ate some cattle owned by the villagers and returned to its cave. Each day after this, the dragon would return to the village and seize another victim, the dragon soon turned to taking boys and men. They named the dragon ‘Smok’.",
        "Poland has diverse land and climate" to "There are many types of landscapes in Poland. Ranging from beaches, hills and even mountain ranges. There are three major mountain ranges and these are largely located in the southern region of Poland. ",
        "Poland is bordered by 7 countries" to "Poland is central to Europe, and it is bordered by 7 other countries. These countries are Germany, the Czech Republic, Ukraine, Slovakia, Lithuania, Belarus and Russia. Where the southern border is mountainous, the northern border is surrounded by the Baltic Sea coast. Poland was actually only established as its own independent country on November third, 1918. ",
        "Poland has a very famous salt mine" to "Poland is also home to a huge tourist attraction called the “Wieliczka” Salt Mine. The mines are the largest underground attraction in Europe. While the mines are a tourist attraction, they were actually used to excavate table salt continuously from the 13th century to 2007.",
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

            delay(1000)

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