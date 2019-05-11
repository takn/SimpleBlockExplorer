package com.studio1r.simpleblockexplorer


import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface EOSApiService {
    @get:GET("get_info")
    val info: Observable<BlockInfo>

    @FormUrlEncoded
    @POST("get_block")
    fun getBlock(@Field("block_num_or_id") blockId: Int): Observable<Block>
}
