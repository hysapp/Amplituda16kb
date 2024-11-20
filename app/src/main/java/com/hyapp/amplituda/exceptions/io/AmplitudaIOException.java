package com.hyapp.amplituda.exceptions.io;

import com.hyapp.amplituda.exceptions.AmplitudaException;

public class AmplitudaIOException extends AmplitudaException {
    public AmplitudaIOException(String message, final int code) {
        super(message, code);
    }
}
