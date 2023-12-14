package com.devtech.retrofithiltapplication.di;

import android.content.Context;

import com.devtech.retrofithiltapplication.api.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    public String provideBaseUrl() {
        return BuildConfig.BASE_URL;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, String baseUrl) {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl).client(okHttpClient).build();
    }

    @Provides
    @Singleton
    public ApiService provideApiServices(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@ApplicationContext Context context) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build();
        } else return new OkHttpClient.Builder().build();
    }
}
