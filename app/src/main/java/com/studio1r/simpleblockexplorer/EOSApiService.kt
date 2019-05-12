package com.studio1r.simpleblockexplorer


import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EOSApiService {
    @get:GET("get_info")
    val info: Observable<BlockInfo>

    @POST("get_block")
    fun getBlock(@Body() blockId: BlockInfoRequest): Observable<BlockEntity>
}
