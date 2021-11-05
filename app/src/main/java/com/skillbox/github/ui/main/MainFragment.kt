package com.skillbox.github.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.currentUserInfoButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToCurrentUserFragment())
        }

        binding.repositoryListButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToRepositoryListFragment())
        }

        binding.starredRepositoryListButton.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToStarredListFragment())
        }
        return view

    }


}