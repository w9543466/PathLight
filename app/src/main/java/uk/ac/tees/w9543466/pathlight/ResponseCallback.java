package uk.ac.tees.w9543466.pathlight;

public interface ResponseCallback<T extends BaseResponse> {
    void onResponse(T response);
}
