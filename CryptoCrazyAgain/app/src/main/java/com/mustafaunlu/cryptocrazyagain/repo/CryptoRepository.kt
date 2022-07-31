package com.mustafaunlu.cryptocrazyagain.repo

import com.mustafaunlu.cryptocrazyagain.model.Crypto
import com.mustafaunlu.cryptocrazyagain.model.CryptoList
import com.mustafaunlu.cryptocrazyagain.service.CryptoAPI
import com.mustafaunlu.cryptocrazyagain.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject


@ActivityScoped
class CryptoRepository
@Inject
constructor(private val api : CryptoAPI)
{

    suspend fun getCryptoList() : Resource<CryptoList>{
        val response=try{
            api.getCryptoList()
        }catch (e : Exception){
            return Resource.Error("CryptoList Error!")
        }
        return Resource.Success(response)
    }

    suspend fun getCrypto() : Resource<Crypto>{
        val response=try{
            api.getCrypto()
        }catch (e :Exception){
            return Resource.Error("Crypto Error!")
        }
        return Resource.Success(response)
    }


}