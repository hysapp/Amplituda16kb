package com.hyapp.amplituda.exceptions.processing;

import static com.hyapp.amplituda.ErrorCode.STREAM_NOT_FOUND_PROC_CODE;

public final class StreamNotFoundException extends AmplitudaProcessingException {
    public StreamNotFoundException() {
        super("Could not find stream in the input file!", STREAM_NOT_FOUND_PROC_CODE);
    }
}
