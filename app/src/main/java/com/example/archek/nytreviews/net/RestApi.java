package com.example.archek.nytreviews.net;

import com.example.archek.nytreviews.BuildConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

final public class RestApi {
    private static final String BASE_URL = "https://api.nytimes.com/svc/movies/v2/";// api link
    private static final long CONNECT_TIMEOUT = 60;//defaults: delay 60 msec
    private static final long READ_TIMEOUT =60;

    private static final Retrofit retrofit = new Retrofit.Builder() //instal retrofit builder
            .baseUrl( BASE_URL )
            .validateEagerly(true)
            .client( buildOkHttpClient() )
            .addConverterFactory( createConverterFactory() )
            .build();

    public static <S> S createService(Class<S> klass) {//metod for load data
        return retrofit.create( klass );
    }

    private static Converter.Factory createConverterFactory() {//setup Gson converter
        return GsonConverterFactory.create(
                new GsonBuilder()
                        .setFieldNamingPolicy( FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES )
                        .create()
        );
    }

    private static OkHttpClient buildOkHttpClient(){ //setup OkHttpClient
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout( READ_TIMEOUT,TimeUnit.SECONDS );
        if(BuildConfig.DEBUG){
            final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor( interceptor );
        }
        return builder.build();
    }
}
