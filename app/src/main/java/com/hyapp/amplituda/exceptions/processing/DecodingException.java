package com.hyapp.amplituda.exceptions.processing;

import static com.hyapp.amplituda.ErrorCode.DECODING_PROC_CODE;

public final class DecodingException extends AmplitudaProcessingException {
    public DecodingException() {
        super("Error during decoding!", DECODING_PROC_CODE);
    }
}
