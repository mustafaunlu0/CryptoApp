package com.mustafaunlu.cryptocrazyagain.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.cryptocrazyagain.model.CryptoListItem
import com.mustafaunlu.cryptocrazyagain.repo.CryptoRepository
import com.mustafaunlu.cryptocrazyagain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class CryptoListViewModel
@Inject constructor(
    private val repository: CryptoRepository
) : ViewModel()
{
    val cryptoList = mutableStateOf<List<CryptoListItem>>(listOf())
    val errorMessage= mutableStateOf("")
    val isLoading= mutableStateOf(false)

    private var initialCryptoList= listOf<CryptoListItem>()
    private var isSearchStarting = true

    init{
        laodCryptos()
    }

    fun searchCryptoList(query : String){


        val listToSearch= if(isSearchStarting){
            cryptoList.value
        }else{
            initialCryptoList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                cryptoList.value=initialCryptoList
                isSearchStarting=true
                return@launch
            }
            val results=listToSearch.filter {
                it.currency.contains(query.trim(),true)
            }
            if(isSearchStarting){
                initialCryptoList=cryptoList.value
                isSearchStarting=false

            }

            cryptoList.value=results

        }

    }







    fun laodCryptos(){

        viewModelScope.launch {
            isLoading.value=true

            val result = repository.getCryptoList()

            when(result){


                is Resource.Success -> {

                    val cryptoItems = result.data!!.mapIndexed { index, cryptoListItem ->
                        CryptoListItem(cryptoListItem.currency,cryptoListItem.price)
                    }

                    errorMessage.value=""
                    isLoading.value=false
                    cryptoList.value +=cryptoItems

                }


                is Resource.Error -> {
                    errorMessage.value=result.message!!
                    isLoading.value=false

                }


            }

        }

    }





}