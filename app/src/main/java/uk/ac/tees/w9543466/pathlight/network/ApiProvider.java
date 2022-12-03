package uk.ac.tees.w9543466.pathlight.network;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
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
import uk.ac.tees.w9543466.pathlight.auth.User;
import uk.ac.tees.w9543466.pathlight.employer.EmployerApi;
import uk.ac.tees.w9543466.pathlight.utils.PrefUtil;

public class ApiProvider {

    private Retrofit retrofit;
    private User user;

    public ApiProvider(Context context) {
        user = new PrefUtil(context).getLoginInfo();
    }

    public ApiProvider() {
    }

    public LoginApi getLoginApi() {
        if (retrofit == null) init();
        return retrofit.create(LoginApi.class);
    }

    public EmployerApi getEmployerApi() {
        if (retrofit == null) init();
        return retrofit.create(EmployerApi.class);
    }

    private void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = builder.addInterceptor(new AuthInterceptor(user))
                .addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T extends BaseResponse> void format(Call<RawResponse<T>> call, Class<T> clazz, ResponseCallback<T> callback) {
        call(call, response -> {
            T data = response.getData();
            if (data == null) {
                try {
                    data = clazz.newInstance();
                    data.setSuccess(response.isSuccess());
                    data.setMessage(response.getMessage());
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            } else {
                data.setSuccess(response.isSuccess());
                data.setMessage(response.getMessage());
            }
            callback.onResponse(data);
        });
    }

    public <T> void call(Call<RawResponse<T>> call, ResponseCallback<RawResponse<T>> callback) {
        call.enqueue(new Callback<RawResponse<T>>() {
            @Override
            public void onResponse(@NonNull Call<RawResponse<T>> call, @NonNull Response<RawResponse<T>> response) {
                RawResponse<T> rawResponse = new RawResponse<>();
                try {
                    if (response.isSuccessful()) {
                        rawResponse = response.body();
                    } else {
                        ResponseBody responseBody = response.errorBody();
                        if (responseBody != null) {
                            String errorResponse = responseBody.string();
                            Gson gson = new Gson();
                            rawResponse = gson.fromJson(errorResponse, RawResponse.class);
                        } else {
                            rawResponse = new RawResponse<>();
                            rawResponse.setSuccess(false);
                            rawResponse.setMessage("No error received from server");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    rawResponse.setSuccess(false);
                    rawResponse.setMessage(e.getMessage());
                }
                callback.onResponse(rawResponse);
            }

            @Override
            public void onFailure(@NonNull Call<RawResponse<T>> call, @NonNull Throwable throwable) {
                throwable.printStackTrace();
                RawResponse<T> rawResponse = new RawResponse<>();
                rawResponse.setSuccess(false);
                rawResponse.setMessage(throwable.getMessage());
                callback.onResponse(rawResponse);
            }
        });
    }
}
