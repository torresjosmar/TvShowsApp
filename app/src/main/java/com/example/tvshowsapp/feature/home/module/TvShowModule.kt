package com.example.tvshowsapp.feature.home.module

import com.example.tvshowsapp.feature.home.datasource.repository.TvShowRepository
import com.example.tvshowsapp.feature.home.datasource.service.TvShowService
import com.example.tvshowsapp.feature.home.usecase.TvShowUseCase
import com.example.tvshowsapp.feature.home.viewmodel.TvShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.core.qualifier.named
import kotlin.jvm.java


val tvShowModule: Module = module{
    viewModel { TvShowsViewModel(get()) }
    single<TvShowUseCase> { providerTvShowUseCase(get()) }
    single<TvShowRepository> { providerTvShowRepository(get()) }
    single<TvShowService> { providerTvShowService(get(named("public"))) }

}

fun providerTvShowService(retrofit: Retrofit): TvShowService {
    return retrofit.create(TvShowService::class.java)
}

fun providerTvShowRepository(tvShowService: TvShowService): TvShowRepository {
    return TvShowRepository(tvShowService)
}

fun providerTvShowUseCase(tvShowRepository: TvShowRepository): TvShowUseCase {
    return TvShowUseCase(tvShowRepository)
}
