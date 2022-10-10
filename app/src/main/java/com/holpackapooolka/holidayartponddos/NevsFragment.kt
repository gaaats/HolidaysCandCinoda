package com.holpackapooolka.holidayartponddos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.holpackapooolka.holidayartponddos.databinding.FragmentNevsBinding
import com.holpackapooolka.holidayartponddos.nevs.ArticleUIModel
import com.holpackapooolka.holidayartponddos.nevs.NevsListAdapter
import com.holpackapooolka.holidayartponddos.nevs.NewsAPIService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NevsFragment : Fragment() {

    private var list = mutableListOf<ArticleUIModel>()

    private val adapter by lazy {
        NevsListAdapter()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(NewsAPIService.NEVS_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: NewsAPIService by lazy {
        retrofit.create(NewsAPIService::class.java)
    }

    private var _binding: FragmentNevsBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNevsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addVertAndHorDividers()
        initProgBar()
        binding.btnImgExit.setOnClickListener {
            requireActivity().onBackPressed()
        }

        super.onViewCreated(view, savedInstanceState)
    }



    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun addVertAndHorDividers() {
        binding.recyclerViewNevs.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun initProgBar() {
        lifecycleScope.launch {
            binding.lottieAnimLoadNevs.visibility = View.VISIBLE
            binding.btnImgExit.visibility = View.GONE
            binding.tvTitleCountry.visibility = View.GONE
            binding.cardV.visibility = View.GONE
            binding.recyclerViewNevs.visibility = View.GONE
            loadList()
            delay(3200)
            binding.lottieAnimLoadNevs.visibility = View.GONE
            binding.btnImgExit.visibility = View.VISIBLE
            binding.tvTitleCountry.visibility = View.VISIBLE
            binding.cardV.visibility = View.VISIBLE
            binding.recyclerViewNevs.visibility = View.VISIBLE

        }
    }

    private fun loadList() {
        lifecycleScope.launch {
            try {
                val result = api.getTopHeadlines()
                if (result.isSuccessful) {
                    list = result.body()!!.articles!!.map {
                        it!!.convertFromApiModelToUi()
                    }.toMutableList()
                    adapter.submitList(list)
                    binding.recyclerViewNevs.adapter = adapter

                } else {
                    snackBarError()
                }
            } catch (e: Exception) {
                snackBarError()
            }
        }
    }

    private fun snackBarError() {
        Snackbar.make(
            binding.root,
            "There is some error, try again",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

}