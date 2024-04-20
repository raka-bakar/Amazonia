package com.raka.amazonia.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.raka.amazonia.R
import com.raka.amazonia.databinding.FragmentDetailProductBinding
import com.raka.amazonia.model.ProductCompact
import com.raka.amazonia.utils.CallResult

class DetailProductFragment : Fragment() {
    private lateinit var binding: FragmentDetailProductBinding
    private val viewModel: DetailProductViewModel by activityViewModels()

    private val idProduct: Int
        get() = requireArguments().getInt(ARG_ID_PRODUCT)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getRatingProduct(idProduct)
        binding = FragmentDetailProductBinding.inflate(inflater, container, false)
        binding.btnReload.setOnClickListener { viewModel.getRatingProduct(idProduct) }
        setupObserver()
        setOnBackPressed()
        return binding.root
    }

    private fun setupObserver() {
        viewModel.productLiveData.observe(viewLifecycleOwner) { callResult ->
            when (callResult.status) {
                CallResult.Status.SUCCESS -> {
                    binding.loadingSpinnerDetail.visibility = View.GONE
                    callResult.data?.let { productCompact -> setView(productCompact) }
                }

                CallResult.Status.LOADING -> {
                    hideEmptyView()
                    binding.loadingSpinnerDetail.visibility = View.VISIBLE
                }

                CallResult.Status.ERROR -> {
                    showEmptyView()
                    binding.loadingSpinnerDetail.visibility = View.GONE
                }
            }
        }
    }

    private fun setView(product: ProductCompact) {
        binding.apply {
            tvDetailTitle.text = product.title
            tvDetailDescription.text = product.description
            tvDetailPrice.text = resources.getString(
                R.string.price_currency,
                product.price.toString()
            )
            tvDetailRating.text = resources.getString(
                R.string.product_rank,
                product.rank,
                product.totalProduct,
                product.category
            )
            ivDetailFavorite.setOnClickListener {
                viewModel.onFavoriteClicked(
                    id = product.id,
                    isFavorite = product.isFavorite
                )
            }
        }
        setFavoriteImage(product.isFavorite)
    }

    private fun setFavoriteImage(status: Boolean) {
        if (status) {
            binding.ivDetailFavorite.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            binding.ivDetailFavorite.setImageResource(R.drawable.ic_favorite_unfilled)
        }
    }

    private fun setOnBackPressed() {
        binding.toolbarDetail.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun showEmptyView() {
        binding.apply {
            tvEmptyData.visibility = View.VISIBLE
            btnReload.visibility = View.VISIBLE
            ivEmptyData.visibility = View.VISIBLE
        }
    }

    private fun hideEmptyView() {
        binding.apply {
            tvEmptyData.visibility = View.GONE
            btnReload.visibility = View.GONE
            ivEmptyData.visibility = View.GONE
        }
    }

    companion object {
        private const val ARG_ID_PRODUCT = "argIdProduct"
        fun newInstance(id: Int) =
            DetailProductFragment().apply {
                arguments = bundleOf(ARG_ID_PRODUCT to id)
            }
    }
}