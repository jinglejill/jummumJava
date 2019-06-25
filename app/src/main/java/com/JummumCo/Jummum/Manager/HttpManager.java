package com.JummumCo.Jummum.Manager;


import com.JummumCo.Jummum.Manager.http.ApiService;
import com.JummumCo.Jummum.Manager.http.MemberService;
import com.JummumCo.Jummum.Util.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class HttpManager {

    private static HttpManager instance;
    private String baseUrl = "";

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    private HttpManager() {
        createApiService();
    }

    private Retrofit createRequest() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat(Constant.JSON_DATE_TIME_FORMAT)
                .create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.writeTimeout(60, TimeUnit.SECONDS);
        httpClient.retryOnConnectionFailure(false);
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();



//        if (Constant.payQrCode){
//            baseUrl = Constant.BASE_URL_QR;
//        }else{
//            baseUrl = Constant.BASE_URL;
//        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    private ApiService apiService;
    private MemberService memberService;

    private void createApiService() {
        apiService = this.createRequest().create(ApiService.class);
        memberService = this.createRequest().create(MemberService.class);

    }

    public MemberService getMemberService() {
        return memberService;
    }

    public ApiService getApiService() {
        return apiService;
    }

}