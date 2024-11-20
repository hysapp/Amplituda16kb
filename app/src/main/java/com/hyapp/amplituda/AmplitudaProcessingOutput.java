package com.hyapp.amplituda;

import java.util.LinkedHashSet;
import java.util.List;

import com.hyapp.amplituda.callback.AmplitudaErrorListener;
import com.hyapp.amplituda.callback.AmplitudaSuccessListener;
import com.hyapp.amplituda.exceptions.AmplitudaException;
import com.hyapp.amplituda.exceptions.processing.InvalidParameterFlagException;
import com.hyapp.amplituda.exceptions.processing.SampleOutOfBoundsException;

public final class AmplitudaProcessingOutput<T> {

    private final com.hyapp.amplituda.AmplitudaResult<T> amplitudaResult;
    private LinkedHashSet<AmplitudaException> processingErrors = new LinkedHashSet<>();

    AmplitudaProcessingOutput(
            final String amplitudes,
            final com.hyapp.amplituda.InputAudio<T> inputAudio
    ) {
        amplitudaResult = new com.hyapp.amplituda.AmplitudaResult<>(amplitudes, inputAudio);
    }

    AmplitudaProcessingOutput(
            final com.hyapp.amplituda.AmplitudaResultJNI processingData,
            final com.hyapp.amplituda.InputAudio<T> inputAudio
    ) {
        this(
            processingData.getAmplitudes(),
            inputAudio
        );
        this.processingErrors.addAll(processingData.getErrors());
    }

    AmplitudaProcessingOutput(final AmplitudaException exception, final com.hyapp.amplituda.InputAudio<T> inputAudio) {
        this("", inputAudio);
        this.processingErrors.add(exception);
    }

    /**
     * Get Amplituda processing result. This function returns result in callback
     * @param successListener - success processing operation callback
     * @param errorListener - processing error callback
     */
    public void get(
            final AmplitudaSuccessListener<T> successListener,
            final AmplitudaErrorListener errorListener
    ) {
        handleAmplitudaProcessingErrors(errorListener);
        successListener.onSuccess(amplitudaResult);
    }

    /**
     * Get Amplituda processing result. This function returns result in callback
     * @param successListener - success processing operation callback
     */
    public void get(final AmplitudaSuccessListener<T> successListener) {
        get(successListener, null);
    }

    /**
     * Get Amplituda processing result
     * @param errorListener - processing error callback
     * @return AmplitudaResult object
     */
    public com.hyapp.amplituda.AmplitudaResult<T> get(final AmplitudaErrorListener errorListener) {
        handleAmplitudaProcessingErrors(errorListener);
        return amplitudaResult;
    }

    /**
     * Get Amplituda processing result
     * @return AmplitudaResult object
     */
    public com.hyapp.amplituda.AmplitudaResult<T> get() {
        return amplitudaResult;
    }

    private void handleAmplitudaProcessingErrors(final AmplitudaErrorListener errorListener) {
        if(processingErrors.isEmpty()){
            processingErrors = null;
            return;
        }

        for(final AmplitudaException exception : processingErrors) {
            throwException(exception, errorListener);
        }

        processingErrors.clear();
        processingErrors = null;
    }

    private void throwException(
            final AmplitudaException exception,
            final AmplitudaErrorListener errorListener
    ) {
        if(errorListener == null) {
            processingErrors.add(exception);
            return;
        }
        errorListener.onError(exception);
    }

}
