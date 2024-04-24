package com.raka.amazonia.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raka.amazonia.R
import com.raka.amazonia.databinding.FragmentHomeBinding
import com.raka.amazonia.data.model.ProductCompact
import com.raka.amazonia.ui.detail.DetailProductFragment
import com.raka.amazonia.utils.ScreenState
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

        binding.btnReload.setOnClickListener { viewModel.getInitialData() }
        setupObserver()
        setupAppBar()
        disableBackPress()

        return binding.root
    }

    private fun setupObserver() {
        viewModel.getAllProducts()

        viewModel.productsLiveData.observe(viewLifecycleOwner) { state ->
            when (state.status) {
                ScreenState.Status.SUCCESS -> {
                    binding.loadingSpinner.visibility = View.GONE
                    hideEmptyView()
                    state.data?.let { listProduct ->
                        setupHomeAdapter(
                            listProduct = listProduct
                        )
                    }
                }

                ScreenState.Status.LOADING -> {
                    hideEmptyView()
                    binding.loadingSpinner.visibility = View.VISIBLE
                }

                ScreenState.Status.ERROR -> {
                    showEmptyView()
                    binding.loadingSpinner.visibility = View.GONE
                }
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
        binding.toolbarHome.logo = ResourcesCompat.getDrawable(
            resources,
            R.drawable.ic_logo,
            null
        )
    }

    private fun disableBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {}
    }

    private fun showEmptyView() {
        binding.apply {
            tvEmptyData.visibility = View.VISIBLE
            btnReload.visibility = View.VISIBLE
        }
    }

    private fun hideEmptyView() {
        binding.apply {
            tvEmptyData.visibility = View.GONE
            btnReload.visibility = View.GONE
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}