package com.example.tvshowsapp.feature.home.view

import android.text.Html
import androidx.navigation.fragment.navArgs
import com.example.tvshowsapp.core.base.BaseFragment
import com.example.tvshowsapp.core.utils.setImageSrcFromUrl
import com.example.tvshowsapp.databinding.FragmentTvShowDetailBinding
import com.example.tvshowsapp.R
import kotlin.getValue

class TvShowDetailViewFragment: BaseFragment<FragmentTvShowDetailBinding>() {
    private val args: TvShowDetailViewFragmentArgs by navArgs()

    override fun init() {
        bindingView.imgShow.setImageSrcFromUrl(args.data.image.medium, R.mipmap.ic_launcher_round, requireContext())
        bindingView.tvTitle.text = args.data.name
        bindingView.tvDescription.text = Html.fromHtml(args.data.summary, Html.FROM_HTML_MODE_LEGACY)
    }

    override fun listenToObserver() {}

}
