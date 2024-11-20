package com.hyapp.amplituda.callback;

import com.hyapp.amplituda.AmplitudaResult;

/**
 * Callback interface for success processing event
 */
public interface AmplitudaSuccessListener<T> {
    void onSuccess(final AmplitudaResult<T> result);
}
