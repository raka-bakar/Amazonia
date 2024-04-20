package com.raka.amazonia.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.raka.amazonia.databinding.FragmentDetailProductBinding

class DetailProductFragment : Fragment() {
    private lateinit var binding: FragmentDetailProductBinding

    private val idProduct: Int
        get() = requireArguments().getInt(ARG_ID_PRODUCT)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private const val ARG_ID_PRODUCT = "argIdProduct"
        fun newInstance(id: Int) =
            DetailProductFragment().apply {
                arguments = bundleOf(ARG_ID_PRODUCT to id)
            }
    }
}