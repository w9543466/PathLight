package uk.ac.tees.w9543466.pathlight.network;

import android.util.Base64;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import uk.ac.tees.w9543466.pathlight.auth.User;

public class AuthInterceptor implements Interceptor {
    private final User user;

    public AuthInterceptor(User user) {
        this.user = user;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (user == null || user.getEmail() == null || user.getPwd() == null) {
            return chain.proceed(chain.request());
        }

        String basic = Credentials.basic(user.getEmail(), user.getPwd());
        Request request = chain.request()
                .newBuilder()
                .addHeader("Authorization", basic)
                .build();
        return chain.proceed(request);
    }
}
