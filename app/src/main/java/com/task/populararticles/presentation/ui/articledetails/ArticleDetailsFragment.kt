package com.task.populararticles.presentation.ui.articledetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.task.populararticles.R
import com.task.populararticles.databinding.FragmentArticleDetailsBinding

class ArticleDetailsFragment : Fragment() {
    private lateinit var binding: FragmentArticleDetailsBinding
    private val args: ArticleDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        initializeView()
        return binding.root
    }

    private fun initializeView() {
        (requireActivity()).title = getString(R.string.article_details)
        val articleData = args.article
        binding.model = articleData

    }

}