package com.wadektech.maishamedschallenge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.wadektech.maishamedschallenge.R
import com.wadektech.maishamedschallenge.data.PostsRepository
import com.wadektech.maishamedschallenge.data.local.PostsRoomDatabase
import com.wadektech.maishamedschallenge.databinding.FragmentPostsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [PostsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentPostsBinding.inflate(inflater)

        val db = PostsRoomDatabase(requireContext())
        val repo = PostsRepository(db)
        val factory = PostsViewModelFactory(repo)
        val postsViewModel : PostsViewModel by lazy {
            ViewModelProvider(this,factory).get(PostsViewModel::class.java)
        }
        binding.lifecycleOwner = this
        binding.viewModel = postsViewModel
        binding.rvPosts.adapter = PostsAdapter()
        return binding.root
    }
}