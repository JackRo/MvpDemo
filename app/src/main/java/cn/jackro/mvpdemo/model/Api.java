package cn.jackro.mvpdemo.model;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.jackro.mvpdemo.BuildConfig;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>
 * Api类，用来配置Retrofit，配置OkHttpClient，获取ApiService
 * </p>
 * <p>
 * Tip: 使用这个类，你必须调用{@link #setBaseUrl(String)}设置它的BASE_URL
 * </p>
 */
public class Api {

    private static String BASE_URL;

    /**
     * 默认超时时长
     */
    private static final int DEFAULT_TIMEOUT = 10;

    private OkHttpClient mOkHttpClient;

    private Api() {
        //region OkHttp config
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Headers.Builder headersBuilder = new Headers.Builder()
                        .add("Accept", "application/json")
                        .add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                Request request = chain.request();
                Headers headers = headersBuilder.build();
                return chain.proceed(request.newBuilder().headers(headers).build());
            }
        };


        builder.addInterceptor(interceptor);

        if (BuildConfig.IS_DEBUG) {
            // Log info Interceptor
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //set Debug Log pattern
            builder.addInterceptor(loggingInterceptor);
        }
        //endregion

        mOkHttpClient = builder.build();
    }

    private static class Singleton {
        private static final Api INSTANCE = new Api();
    }

    static Api getInstance() {
        return Singleton.INSTANCE;
    }

    private Retrofit getRetrofit() {

        //region 测试单例模式的mOkHttpClient是否是同一个对象
        System.out.println(BASE_URL);
        System.out.println(mOkHttpClient.toString());
        //endregion

        //Retrofit config
        return new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    ApiService getApiService() {
        return getRetrofit().create(ApiService.class);
    }

    /**
     * 设置Retrofit的baseUrl
     *
     * @param baseUrl Retrofit的baseUrl
     */
    static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }
}
