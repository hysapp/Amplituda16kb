package com.hyapp.amplituda.exceptions.allocation;

import com.hyapp.amplituda.exceptions.AmplitudaException;

public class AmplitudaAllocationException extends AmplitudaException {
    public AmplitudaAllocationException(String message, final int code) {
        super(message, code);
    }
}
