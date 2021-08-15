package com.day_a_log.src.login.login

import com.day_a_log.src.login.login.models.KakaoResponse
import retrofit2.Call
import retrofit2.http.*

interface KakaoInterface {
    @GET("oauth/authorize")
    fun getKakaoLogin(@Query("client_id") client_id: String,
                      @Query("redirect_uri") redirect_uri: String,
                      @Query("response_type") response_type: String,
                      @Query("state") state: String?,
                      @Query("prompt") prompt: String?)
    : Call<KakaoResponse>
}
