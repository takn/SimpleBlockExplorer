package com.studio1r.simpleblockexplorer;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EOSApiService {
    @GET("get_info")
    Observable<BlockInfo> getInfo();

    @POST("get_block")
    Observable<Block> getBlock(@Field("block_num_or_id") int blockId);
}
