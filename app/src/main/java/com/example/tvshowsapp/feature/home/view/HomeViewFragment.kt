package com.example.tvshowsapp.feature.home.view

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.tvshowsapp.core.base.BaseFragment
import com.example.tvshowsapp.core.utils.observe
import com.example.tvshowsapp.databinding.FragmentHomeBinding
import com.example.tvshowsapp.feature.home.datasource.model.TvShowResponse
import com.example.tvshowsapp.feature.home.view.cell.TvShowAdapter
import com.example.tvshowsapp.feature.home.viewmodel.TvShowsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class HomeViewFragment: BaseFragment<FragmentHomeBinding>() {
    private val viewModel: TvShowsViewModel by viewModel()

    override fun init() {
        viewModel.getTvShows()
    }

    override fun listenToObserver() {
        observe(viewModel.onLoading, this::onLoading)
        observe(viewModel.onGetTvShows, this::onGetTvShows)
        observe(viewModel.onNetworkError, this::onNetWorkError)
    }

    private fun setOnChangeListeners(){
        bindingView.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().uppercase()
                (bindingView.rvTvShowsList.adapter as TvShowAdapter).setDataList(viewModel.dataList.filter {
                    it.name?.uppercase()!!.contains(query)
                })
            }
        })
    }

    private fun onGetTvShows(data: List<TvShowResponse>) {
        setOnChangeListeners()
        bindingView.rvTvShowsList.adapter = TvShowAdapter(data, object : TvShowAdapter.OnClick {
            override fun onClick(data: TvShowResponse) {
                val action = HomeViewFragmentDirections.actionHomeViewFragmentToTvShowDetailViewFragment(data)
                findNavController().navigate(action)
            }
        })
    }

    private fun onLoading(loading: Boolean) {
        bindingView.loading.visibility = if (loading) View.VISIBLE else View.GONE
    }

}
