package com.hyapp.amplituda.exceptions.processing;

import static com.hyapp.amplituda.ErrorCode.STREAM_INFO_NOT_FOUND_PROC_CODE;

public final class StreamInformationNotFoundException extends AmplitudaProcessingException {
    public StreamInformationNotFoundException() {
        super("Could not find stream information!", STREAM_INFO_NOT_FOUND_PROC_CODE);
    }
}
