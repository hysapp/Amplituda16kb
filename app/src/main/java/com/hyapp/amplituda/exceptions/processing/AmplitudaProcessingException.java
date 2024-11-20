package com.hyapp.amplituda.exceptions.processing;

import com.hyapp.amplituda.exceptions.AmplitudaException;

public class AmplitudaProcessingException extends AmplitudaException {
    public AmplitudaProcessingException(String message, final int code) {
        super(message, code);
    }
}
