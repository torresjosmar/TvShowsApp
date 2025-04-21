package com.example.tvshowsapp.feature.home.view.cell

import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowsapp.core.utils.setImageSrcFromUrl
import com.example.tvshowsapp.databinding.TvShowItemBinding
import com.example.tvshowsapp.feature.home.datasource.model.TvShowResponse
import com.example.tvshowsapp.R

class TvShowViewHolder(
    private val binding: TvShowItemBinding,
    private val onClick: TvShowAdapter.OnClick
) : RecyclerView.ViewHolder(binding.root){
    fun bind(data: TvShowResponse) {
        binding.tvTitle.text = data.name
        binding.tvType.text = data.type
        binding.tvlanguaje.text = data.language

        binding.imgTvshow.setImageSrcFromUrl(data.image.medium, R.mipmap.ic_launcher_round, binding.root.context)

        binding.container.setOnClickListener {
            onClick.onClick(data)
        }

    }
}