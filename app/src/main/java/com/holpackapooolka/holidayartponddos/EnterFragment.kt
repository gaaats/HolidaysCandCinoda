package com.holpackapooolka.holidayartponddos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.holpackapooolka.holidayartponddos.databinding.FragmentEnterBinding


class EnterFragment : Fragment() {

    private var _binding: FragmentEnterBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnHolidays.setOnClickListener {
            findNavController().navigate(R.id.action_enterFragment_to_startFragment)
        }
        binding.btnNevs.setOnClickListener {
            findNavController().navigate(R.id.action_enterFragment_to_nevsFragment)
        }
        binding.btnInterestingFacts.setOnClickListener {
            findNavController().navigate(R.id.aboutFragment)
        }
        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }
        binding.btnPlayGame.setOnClickListener {
            startActivity(Intent(requireActivity(), GameActivity::class.java))
            requireActivity().finish()
        }
        binding.btnCuisine.setOnClickListener {
            findNavController().navigate(R.id.fragmentCuisine)
        }

        binding.btnHeart.setOnClickListener {
            Snackbar.make(binding.root, "I love you too ♥♥♥", Snackbar.LENGTH_LONG).show()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}