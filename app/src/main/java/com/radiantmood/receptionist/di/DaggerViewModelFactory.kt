package com.radiantmood.receptionist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class DaggerViewModelFactory
@Inject constructor(private val viewModelsMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return try {
            getProvider(modelClass).get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun <T : ViewModel> getProvider(modelClass: Class<T>) =
        viewModelsMap[modelClass]
            ?: viewModelsMap.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("unknown model class $modelClass")
}