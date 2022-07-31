package com.mustafaunlu.cryptocrazyagain.service

import com.mustafaunlu.cryptocrazyagain.model.Crypto
import com.mustafaunlu.cryptocrazyagain.model.CryptoList
import retrofit2.http.GET

interface CryptoAPI {


    @GET("atilsamancioglu/IA32-CryptoComposeData/main/cryptolist.json")
    suspend fun getCryptoList() : CryptoList

    @GET("atilsamancioglu/IA32-CryptoComposeData/main/crypto.json")
    suspend fun getCrypto() : Crypto



}