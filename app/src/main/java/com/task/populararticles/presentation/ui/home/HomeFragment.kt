package com.task.populararticles.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.task.populararticles.R
import com.task.populararticles.databinding.FragmentHomeBinding
import com.task.populararticles.domain.model.ArticleData
import com.task.populararticles.utile.Utilities
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initializeView()
        setListener()
        return binding.root
    }

    private fun initializeView() {
        binding.viewModel?.oncreate()
        Utilities.setSwipeRefreshLayoutColor(
            requireActivity(),
            binding.swipeRefreshHomeFragment
        )
        (requireActivity()).title = getString(R.string.article)

    }

    private fun setListener() {
        binding.viewModel?.loading?.observe(viewLifecycleOwner, {
            if (it!!) binding.shimmerFrameLayout.startShimmerAnimation()
            else binding.shimmerFrameLayout.stopShimmerAnimation()
        })
        binding.viewModel?.articleDetails?.observe(viewLifecycleOwner, {
            it?.let { it1 -> openArticleDetails(it1) }
        })

    }

    private fun openArticleDetails(articleData: ArticleData) {
        val action = HomeFragmentDirections.actionHomeFragmentToArticleDetailsFragment(articleData)
        findNavController().navigate(action)

    }

}