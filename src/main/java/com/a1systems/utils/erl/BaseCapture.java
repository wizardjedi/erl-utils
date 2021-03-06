package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpOutputStream;

/**
 * Implementation of capturing mechanism.
 * @param <T> class of capturing
 */
public abstract class BaseCapture<T extends OtpErlangObject> extends OtpErlangObject implements Capture<T> {
    /**
     * Value be captured
     */
    protected T capture;

    /**
     * Class for equals method
     */
    protected Class<T> clazz;

    public BaseCapture(Class cls) {
        this.clazz = cls;
    }

    @Override
    public T get() {
        return capture;
    }

    @Override
    public abstract String toString();

    @Override
    public void encode(OtpOutputStream otpOutputStream) {
        throw new IllegalArgumentException("Object used only for pattern matching");
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && clazz.isAssignableFrom(o.getClass())) {
            capture = (T) o;

            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
