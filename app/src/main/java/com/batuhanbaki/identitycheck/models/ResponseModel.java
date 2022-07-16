package com.batuhanbaki.identitycheck.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ResponseModel {
    private final boolean isSuccess;
    @NonNull
    private final String message;
    @Nullable
    private final Exception exception;

    public ResponseModel(boolean isSuccess, @NonNull String message, @Nullable Exception exception) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.exception = exception;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    @NonNull
    public String getMessage() {
        return message;
    }

    @Nullable
    public Exception getException() {
        return exception;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                ", exception=" + exception +
                '}';
    }
}

