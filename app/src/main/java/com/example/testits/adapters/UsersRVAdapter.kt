package com.example.testits.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testits.data.User
import com.example.testits.databinding.ItemUserBinding
import com.example.testits.fragments.ListUsersFragmentDirections

class UsersRVAdapter(listUsers: ArrayList<User>?) :
    RecyclerView.Adapter<UsersRVAdapter.ItemViewHolder>() {

    private val itemList: ArrayList<User>? = listUsers

    class ItemViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.user?.let { user ->
                    val direction =
                        ListUsersFragmentDirections.actionListUsersFragmentToDetailsFragment(
                            user.login
                        )
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
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

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList?.get(position))
    }

}