package com.hyapp.amplituda.callback;


import com.hyapp.amplituda.exceptions.AmplitudaException;

/**
 * Callback interface for error events
 */
public interface AmplitudaErrorListener {
    void onError(final AmplitudaException exception);
}
