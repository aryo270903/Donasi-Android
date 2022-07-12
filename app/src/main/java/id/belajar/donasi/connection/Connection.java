package id.belajar.donasi.connection;

import java.util.concurrent.TimeUnit;

import id.belajar.donasi.BuildConfig;
import id.belajar.donasi.utils.Shared;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection {
    private static Connection instance;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(getAuthenticatedOkHttp(Shared.getValue("access_token")))
            .build();
    private DonasiInterface donasiInterface;

    private static final ConnectionPool connectionPool = new ConnectionPool(5,3, TimeUnit.DAYS);
    private static final int REQUEST_TIMEOUT_IN_SECOND = 60;


    private Connection(){}

    public static Connection getInstance(){
        if(null == instance){
            instance = new Connection();
        }
        return instance;
    }

//    public void resetToken(){
//        retrofit = null;
//    }

    private Retrofit getRetrofit(){
        if(retrofit==null){
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(BuildConfig.BASE_URL_API);
            builder.addConverterFactory(GsonConverterFactory.create());
//            builder.client(getAuthenticatedOkHttp(Shared.getValue("access_token")));
            retrofit = builder
                    .build();
        }
        return retrofit;
    }

//    private OkHttpClient getAuthenticatedOkHttp(String accesToken) {
//        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
//                .connectionPool(connectionPool)
//                .connectTimeout(REQUEST_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
//                .readTimeout(REQUEST_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
//                .writeTimeout(REQUEST_TIMEOUT_IN_SECOND, TimeUnit.SECONDS);
//
////        httpClient.addInterceptor(new HeaderInterceptor(accesToken));
//        return httpClient.build();
//    }

    public DonasiInterface getServiceEndPoint(){
        if(donasiInterface == null){
            donasiInterface = getRetrofit().create(DonasiInterface.class);
        }
        return donasiInterface;
    }
}
