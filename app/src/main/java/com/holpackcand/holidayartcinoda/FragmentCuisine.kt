package com.holpackcand.holidayartcinoda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.holidayscountrypackseven.holidaycanadaart.recycler.RecipeListAdapter
import com.holpackcand.holidayartcinoda.cuisine.RecipeService
import com.holpackcand.holidayartcinoda.cuisine.RecipesListNetItem
import com.holpackcand.holidayartcinoda.databinding.FragmentCuisineBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FragmentCuisine : Fragment() {

    private var list = mutableListOf<RecipesListNetItem>()

    private val adapter by lazy {
        RecipeListAdapter()
    }


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(RecipeService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    val api: RecipeService by lazy {
        retrofit.create(RecipeService::class.java)
    }

    private var _binding: FragmentCuisineBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCuisineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            loadList()
            addVertAndHorDividers()
            initProgBar()
            initOnItemClickListener()
        } catch (e: Exception) {
            snackBarError()
        }

        binding.btnCopy.setOnClickListener {
            try {
                saveToClipBoard()
            } catch (e: Exception) {
                snackBarError()
            }
        }
        binding.btnImgExit.setOnClickListener {
            initAlertDialog()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


    private fun loadList() {
        lifecycleScope.launch {
            try {
                val result = api.getRecipes()
                if (result.isSuccessful) {
                    list = result.body()!!
                    adapter.submitList(list)
                    binding.recyclerView.adapter = adapter
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

    private fun initProgBar() {
        lifecycleScope.launch {
            binding.imgMain.visibility = View.GONE
            binding.btnCopy.visibility = View.GONE
            binding.tvHelperName.visibility = View.GONE
            binding.tvHelperIngidients.visibility = View.GONE
            binding.cardV.visibility = View.GONE
            binding.btnImgExit.visibility = View.GONE
            delay(3000)
            binding.lottieAnimVaiting.visibility = View.VISIBLE
            binding.tvPleaseVaitLoading.visibility = View.VISIBLE

            binding.imgMain.visibility = View.VISIBLE
            binding.cardV.visibility = View.VISIBLE
            binding.tvHelperName.visibility = View.VISIBLE
            binding.tvHelperIngidients.visibility = View.VISIBLE
            binding.btnCopy.visibility = View.VISIBLE
            binding.btnImgExit.visibility = View.VISIBLE
            binding.lottieAnimVaiting.visibility = View.GONE
            binding.tvPleaseVaitLoading.visibility = View.GONE
        }
    }

    private fun saveToClipBoard() {
        Snackbar.make(binding.root, "Saved!", Snackbar.LENGTH_LONG).show()
    }

    private fun addVertAndHorDividers() {
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun initAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit")
            .setMessage("Are you definitely want to log out, the current data will not be saved?")
            .setPositiveButton("Yes, Exit") { _, _ ->
                requireActivity().onBackPressed()
            }
            .setNegativeButton("Deny") { _, _ ->
            }
            .setCancelable(true)
            .create()
            .show()
    }

    private fun initOnItemClickListener() {
        adapter.setOnItemClickListener {
            FragmentCuisineDirections.actionFragmentCuisineToFragmentDetailRecipe(recipe = it).also {
                findNavController().navigate(it)
            }
        }
    }


}