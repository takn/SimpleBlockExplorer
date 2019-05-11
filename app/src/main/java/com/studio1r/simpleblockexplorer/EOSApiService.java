package com.studio1r.simpleblockexplorer;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EOSApiService {
    @GET("get_info")
    Call<Response> getInfo();

    @POST("get_block")
    Call<Block> getBlock(@Field("block_num_or_id") int blockId);
}
