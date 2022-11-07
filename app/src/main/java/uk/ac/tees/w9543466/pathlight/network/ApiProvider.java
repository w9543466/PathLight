package uk.ac.tees.w9543466.pathlight.network;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.ac.tees.w9543466.pathlight.BaseResponse;
import uk.ac.tees.w9543466.pathlight.BuildConfig;
import uk.ac.tees.w9543466.pathlight.ResponseCallback;
import uk.ac.tees.w9543466.pathlight.auth.LoginApi;

public class ApiProvider {

    private Retrofit retrofit;

    public LoginApi getLoginApi() {
        if (retrofit == null) init();
        return retrofit.create(LoginApi.class);
    }

    private void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T extends BaseResponse> void get(Call<T> call, Class<T> clazz, ResponseCallback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                T t = null;
                try {
                    if (response.isSuccessful()) {
                        t = response.body();
                        if (t == null) {
                            t = clazz.newInstance();
                            t.setSuccess(true);
                            t.setMessage(response.message());
                        }
                    } else {
                        String errorResponse = response.errorBody().string();
                        Gson gson = new Gson();
                        BaseResponse resp = gson.fromJson(errorResponse, BaseResponse.class);
                        t = clazz.newInstance();
                        t.setSuccess(false);
                        t.setMessage(resp.getMessage());
                    }
                } catch (IllegalAccessException | InstantiationException | IOException e) {
                    e.printStackTrace();
                }
                callback.onResponse(t);
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable throwable) {
                try {
                    T t = clazz.newInstance();
                    t.setSuccess(false);
                    t.setMessage(throwable.getMessage());
                    callback.onResponse(t);
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
