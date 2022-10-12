package com.holpackcand.holidayartcinoda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.holpackcand.holidayartcinoda.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val holiday = args.holiday
        val country = "${holiday.country}, ${holiday.iso}"
        binding.tvTitleCountry.text = country
        binding.tvHolidayName.text = holiday.name
        binding.tvHolidayDate.text = holiday.date
        binding.tvDay.text = holiday.day
        initBtns()
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initBtns() {
        binding.btnImgExit.setOnClickListener {
            goBack()
        }
        binding.btnOk.setOnClickListener {
            goBack()
        }
    }

    private fun goBack() {
        requireActivity().onBackPressed()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}