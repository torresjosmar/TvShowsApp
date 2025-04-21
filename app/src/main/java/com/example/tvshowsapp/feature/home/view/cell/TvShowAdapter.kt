package com.example.tvshowsapp.feature.home.view.cell

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowsapp.databinding.TvShowItemBinding
import com.example.tvshowsapp.feature.home.datasource.model.TvShowResponse

class TvShowAdapter (
    private var data: List<TvShowResponse>,
    private val onClick: OnClick
) : RecyclerView.Adapter<TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder(
            binding = TvShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun setDataList(data: List<TvShowResponse>) {
        this.data = data
        notifyDataSetChanged()
    }

    interface OnClick{
        fun onClick(data: TvShowResponse)
    }
}
