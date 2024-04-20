package com.raka.amazonia.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raka.amazonia.R
import com.raka.amazonia.databinding.FragmentHomeBinding
import com.raka.amazonia.model.ProductCompact
import com.raka.amazonia.ui.detail.DetailProductFragment
import com.raka.amazonia.utils.CallResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnReload.setOnClickListener { viewModel.getAllProducts() }
        setupObserver()
        setupAppBar()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {}

        return binding.root
    }

    private fun setupObserver() {
        viewModel.getAllProducts()

        viewModel.productsLiveData.observe(viewLifecycleOwner) { callResult ->
            when (callResult.status) {
                CallResult.Status.SUCCESS -> {
                    binding.loadingSpinner.visibility = View.GONE
                    hideEmptyView()
                    callResult.data?.let { listProduct -> setupHomeAdapter(listProduct) }
                }

                CallResult.Status.LOADING -> {
                    hideEmptyView()
                    binding.loadingSpinner.visibility = View.VISIBLE
                }

                CallResult.Status.ERROR -> {
                    showEmptyView()
                    binding.loadingSpinner.visibility = View.GONE
                }

                else -> {}
            }
        }
    }

    private fun setupHomeAdapter(listProduct: List<ProductCompact>) {
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = HomeAdapter(listProduct, viewModel::onFavoriteClicked) {
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.frameContainerView, DetailProductFragment.newInstance(it))
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }

    private fun setupAppBar() {
        binding.toolbarHome.title = resources.getString(R.string.app_name)
    }

    private fun showEmptyView() {
        binding.tvEmptyData.visibility = View.VISIBLE
        binding.btnReload.visibility = View.VISIBLE
        binding.ivEmptyData.visibility = View.VISIBLE
    }

    private fun hideEmptyView() {
        binding.tvEmptyData.visibility = View.GONE
        binding.btnReload.visibility = View.GONE
        binding.ivEmptyData.visibility = View.GONE
    }
    companion object {
        fun newInstance() = HomeFragment()
    }
}