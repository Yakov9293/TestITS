package com.example.testits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testits.adapters.UsersRVAdapter
import com.example.testits.databinding.FragmentListUsersBinding
import com.example.testits.util.EndlessRecyclerViewScrollListener
import com.example.testits.viewModel.ListUsersViewModel

class ListUsersFragment : Fragment() {

    val listUsersViewModel: ListUsersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentListUsersBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(activity)

        binding.rvUsers.apply {
            this.layoutManager = layoutManager
            this.adapter =
                UsersRVAdapter(listUsersViewModel.listUsers)
            listUsersViewModel.addUsers { adapter?.notifyDataSetChanged() }

            addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    listUsersViewModel.addUsers { adapter?.notifyDataSetChanged() }
                    adapter?.notifyDataSetChanged()
                }
            })
        }

        listUsersViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        })

        return binding.root
    }

    companion object {
        private val TAG = ListUsersFragment::class.java.simpleName
    }
}
