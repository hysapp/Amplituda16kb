package com.hyapp.amplituda;


import java.util.Arrays;
import java.util.LinkedHashSet;

import com.hyapp.amplituda.exceptions.AmplitudaException;
import com.hyapp.amplituda.exceptions.allocation.CodecContextAllocationException;
import com.hyapp.amplituda.exceptions.allocation.FrameAllocationException;
import com.hyapp.amplituda.exceptions.allocation.PacketAllocationException;
import com.hyapp.amplituda.exceptions.io.FileNotFoundException;
import com.hyapp.amplituda.exceptions.io.FileOpenException;
import com.hyapp.amplituda.exceptions.io.InvalidRawResourceException;
import com.hyapp.amplituda.exceptions.io.NoInputFileException;
import com.hyapp.amplituda.exceptions.processing.CodecNotFoundException;
import com.hyapp.amplituda.exceptions.processing.CodecOpenException;
import com.hyapp.amplituda.exceptions.processing.CodecParametersException;
import com.hyapp.amplituda.exceptions.processing.DecodingException;
import com.hyapp.amplituda.exceptions.processing.InvalidParameterFlagException;
import com.hyapp.amplituda.exceptions.processing.PacketSubmittingException;
import com.hyapp.amplituda.exceptions.processing.SampleOutOfBoundsException;
import com.hyapp.amplituda.exceptions.processing.SecondOutOfBoundsException;
import com.hyapp.amplituda.exceptions.processing.StreamInformationNotFoundException;
import com.hyapp.amplituda.exceptions.processing.StreamNotFoundException;
import com.hyapp.amplituda.exceptions.processing.UnsupportedSampleFormatException;

import static com.hyapp.amplituda.ErrorCode.CODEC_CONTEXT_ALLOC_CODE;
import static com.hyapp.amplituda.ErrorCode.CODEC_NOT_FOUND_PROC_CODE;
import static com.hyapp.amplituda.ErrorCode.CODEC_OPEN_PROC_CODE;
import static com.hyapp.amplituda.ErrorCode.CODEC_PARAMETERS_COPY_PROC_CODE;
import static com.hyapp.amplituda.ErrorCode.DECODING_PROC_CODE;
import static com.hyapp.amplituda.ErrorCode.FILE_NOT_FOUND_IO_CODE;
import static com.hyapp.amplituda.ErrorCode.FILE_OPEN_IO_CODE;
import static com.hyapp.amplituda.ErrorCode.FRAME_ALLOC_CODE;
import static com.hyapp.amplituda.ErrorCode.INVALID_PARAMETER_FLAG_PROC_CODE;
import static com.hyapp.amplituda.ErrorCode.INVALID_RAW_RESOURCE_IO_CODE;
import static com.hyapp.amplituda.ErrorCode.NO_INPUT_FILE_IO_CODE;
import static com.hyapp.amplituda.ErrorCode.PACKET_ALLOC_CODE;
import static com.hyapp.amplituda.ErrorCode.PACKET_SUBMITTING_PROC_CODE;
import static com.hyapp.amplituda.ErrorCode.SAMPLE_OUT_OF_BOUNDS_PROC_CODE;
import static com.hyapp.amplituda.ErrorCode.SECOND_OUT_OF_BOUNDS_PROC_CODE;
import static com.hyapp.amplituda.ErrorCode.STREAM_INFO_NOT_FOUND_PROC_CODE;
import static com.hyapp.amplituda.ErrorCode.STREAM_NOT_FOUND_PROC_CODE;
import static com.hyapp.amplituda.ErrorCode.UNSUPPORTED_SAMPLE_FMT_PROC_CODE;

final class AmplitudaResultJNI {

    private double duration;
    private String amplitudes;
    private String errors;

    /**
     * Get audio duration
     */
    long getDurationMillis() {
        return (long) (duration * 1000);
    }

    /**
     * Get ndk processing result
     */
    String getAmplitudes() {
        return amplitudes;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setAmplitudes(String amplitudes) {
        this.amplitudes = amplitudes;
    }

    /**
     * Get ndk exceptions according to codes
     */
    LinkedHashSet<AmplitudaException> getErrors() {
        if(errors == null || errors.isEmpty()) {
            return new LinkedHashSet<>();
        }
        LinkedHashSet<AmplitudaException> errors = new LinkedHashSet<>();
        for(String error : this.errors.split(" ")) {
            if(error.isEmpty())
                continue;
            errors.add(getExceptionFromCode(Integer.parseInt(error)));
        }
        return errors;
    }

    /**
     * Get exception according to code
     * @param code - exception code. All codes => ErrorCode.java
     * @return exception from code. Return global AmplitudaException when code is unknown
     */
    private AmplitudaException getExceptionFromCode(final int code) {
        switch (code) {
            case FRAME_ALLOC_CODE:                 return new FrameAllocationException();
            case PACKET_ALLOC_CODE:                return new PacketAllocationException();
            case CODEC_CONTEXT_ALLOC_CODE:         return new CodecContextAllocationException();
            case FILE_OPEN_IO_CODE:                return new FileOpenException();
            case CODEC_NOT_FOUND_PROC_CODE:        return new CodecNotFoundException();
            case STREAM_NOT_FOUND_PROC_CODE:       return new StreamNotFoundException();
            case STREAM_INFO_NOT_FOUND_PROC_CODE:  return new StreamInformationNotFoundException();
            case CODEC_PARAMETERS_COPY_PROC_CODE:  return new CodecParametersException();
            case PACKET_SUBMITTING_PROC_CODE:      return new PacketSubmittingException();
            case CODEC_OPEN_PROC_CODE:             return new CodecOpenException();
            case UNSUPPORTED_SAMPLE_FMT_PROC_CODE: return new UnsupportedSampleFormatException();
            case DECODING_PROC_CODE:               return new DecodingException();
            case SAMPLE_OUT_OF_BOUNDS_PROC_CODE:   return new SampleOutOfBoundsException();
            default:                               return new AmplitudaException();
        }
    }

}
