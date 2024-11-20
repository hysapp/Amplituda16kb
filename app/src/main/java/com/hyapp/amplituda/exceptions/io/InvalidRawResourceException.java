package com.hyapp.amplituda.exceptions.io;

import static com.hyapp.amplituda.ErrorCode.INVALID_RAW_RESOURCE_IO_CODE;

public final class InvalidRawResourceException extends AmplitudaIOException {
    public InvalidRawResourceException() {
        super("Invalid raw resource. Could not use file as input audio!", INVALID_RAW_RESOURCE_IO_CODE);
    }
}
