package com.holpackcand.holidayartcinoda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.holpackcand.holidayartcinoda.databinding.FragmentStartBinding
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class StartFragment : Fragment() {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(CountryHolidaysService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    val api: CountryHolidaysService by lazy {
        retrofit.create(CountryHolidaysService::class.java)
    }

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        try {
            binding.btnConfirm.setOnClickListener {
                try {
                    findNavController().navigate(R.id.action_startFragment_to_resultFragment)
                } catch (e: Exception) {
                    Snackbar.make(
                        binding.root,
                        "There is some error, try again",
                        Snackbar.LENGTH_LONG
                    )
                        .show()
                }
            }
            binding.btnSettings.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_settingsFragment)
            }

        } catch (e: Exception) {
            Snackbar.make(binding.root, "There is some error, try again", Snackbar.LENGTH_LONG)
                .show()
        }
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}