package com.itnation.writee_aicontentwritertool.AiModel;

public interface ResponseCallback {

    void onResponse(String response);
    void onError(Throwable throwable);
}
