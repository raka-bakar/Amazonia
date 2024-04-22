package com.raka.amazonia.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.raka.amazonia.R
import com.raka.amazonia.databinding.ItemProductBinding
import com.raka.amazonia.model.ProductCompact

class HomeAdapter(
    data: List<ProductCompact>,
    private val onFavoriteClick: (ProductCompact) -> Unit,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    private var listProduct: MutableList<ProductCompact> = data as MutableList<ProductCompact>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeHolder(view)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.bind(listProduct[position], position)
    }

    private fun onClickItem(item: ProductCompact, position: Int) {
        onFavoriteClick(item)
        notifyItemChanged(position)
        listProduct[position].isFavorite = !item.isFavorite
    }

    inner class HomeHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: ProductCompact,
            position: Int
        ) {
            binding.apply {
                ivProduct.load(item.thumbnail) {
                    transformations(RoundedCornersTransformation(CORNER_RADIUS))
                }
                tvTitle.text = item.title
                tvPrice.text =
                    binding.root.resources.getString(R.string.price_currency, item.price.toString())
                tvDescription.text = item.description
                itemProduct.setOnClickListener {
                    onItemClick(item.id)
                }
                ivFavorite.setOnClickListener { onClickItem(item, position) }
            }
            setFavoriteImage(item.isFavorite)
        }

        private fun setFavoriteImage(status: Boolean) {
            if (status) {
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite_filled)
            } else {
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite_unfilled)
            }
        }
    }

    companion object {
        const val CORNER_RADIUS = 25F
    }
}