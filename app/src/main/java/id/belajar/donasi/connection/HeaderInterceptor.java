package id.belajar.donasi.connection;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
//    private final String token;
//    public HeaderInterceptor(String token){
//        this.token = token;
//    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("Content-Type", "application/json")
//                .addHeader("authorization",  token != null ? token : "")
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }

}
