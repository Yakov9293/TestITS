package com.example.testits

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testits.databinding.FragmentListUsersBinding
import com.example.testits.databinding.ItemUserBinding

class MyAdapter(listUsers: ArrayList<User>?) :
    RecyclerView.Adapter<MyAdapter.ItemViewHolder>() {

    private val itemList: ArrayList<User>? = listUsers

    class ItemViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.user?.let { user ->
                    val direction =
                        ListUsersFragmentDirections.actionListUsersFragmentToDetailsFragment(user.login)
                    it.findNavController().navigate(direction)
                }
            }
        }

        fun bind(item: User?) {
            binding.apply {
                user = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ItemViewHolder {
        return ItemViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return itemList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyAdapter.ItemViewHolder, position: Int) {
        holder.bind(itemList?.get(position))
    }

}