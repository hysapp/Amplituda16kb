package com.hyapp.amplituda.exceptions.io;

import static com.hyapp.amplituda.ErrorCode.FILE_NOT_FOUND_IO_CODE;

public final class FileNotFoundException extends AmplitudaIOException {
    public FileNotFoundException() {
        super("Input file not found! Wrong file path or file does not exist!", FILE_NOT_FOUND_IO_CODE);
    }
}
