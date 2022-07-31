package com.mustafaunlu.cryptocrazyagain.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mustafaunlu.cryptocrazyagain.model.Crypto
import com.mustafaunlu.cryptocrazyagain.model.CryptoListItem
import com.mustafaunlu.cryptocrazyagain.repo.CryptoRepository
import com.mustafaunlu.cryptocrazyagain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class CryptoDetailViewModel
@Inject constructor(
    private val repository: CryptoRepository
) : ViewModel()
{

    suspend fun getCrypto(id : String) : Resource<Crypto>{
        return repository.getCrypto()
    }




}