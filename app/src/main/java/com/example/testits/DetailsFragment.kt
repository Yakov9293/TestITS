package com.example.testits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.testits.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    val args: DetailsFragmentArgs by navArgs()
    private val detailUserViewModel: DetailUserViewModel by viewModels {
        DetailUserViewModelFactory(args.userLogin)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailsBinding>(
            inflater, R.layout.fragment_details, container, false
        ).apply {
            viewModel = detailUserViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        detailUserViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        })

        return binding.root
    }

    companion object {
        private val TAG = DetailsFragment::class.java.simpleName
    }
}
