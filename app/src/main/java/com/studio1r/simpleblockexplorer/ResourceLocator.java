package com.studio1r.simpleblockexplorer;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Simplified DI without adding any frameworks
 */
public class ResourceLocator {
    protected static final String EOS_BASE_URL = "https://api.eosnewyork.io/v1/chain/";
    private BlockRepositoryImpl blockRepository;
    private Retrofit retrofit;
    private EOSApiService service;
    private BlockListViewModelFactory factory;
    private BlockInfoRequestFactory requestFactory;

    public BlockRepository getBlockRepository() {
        if (blockRepository == null) {
            blockRepository = new BlockRepositoryImpl(getEosApiService(), getRequestFactory());
        }
        return blockRepository;
    }

    private BlockInfoRequestFactory getRequestFactory() {

        if (requestFactory == null) {
            requestFactory = new BlockInfoRequestFactory();
        }
        return requestFactory;
    }

    private EOSApiService getEosApiService() {
        if (service == null) {
            service = getRetrofit().create(EOSApiService.class);
        }
        return service;
    }

    private Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit.Builder builder = new Retrofit.Builder();
            builder
                    .baseUrl(EOS_BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());
            retrofit = builder.build();
        }
        return retrofit;
    }

    public BlockListViewModelFactory getFactory() {
        if (factory == null) {
            factory = new BlockListViewModelFactory(getBlockRepository(),
                    getExecutionScheduler(), getObservationScheduler());
        }
        return factory;
    }

    private Scheduler getObservationScheduler() {
        return AndroidSchedulers.mainThread();
    }

    private Scheduler getExecutionScheduler() {
        return Schedulers.io();
    }

}
