package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.OtpErlangObject;

/**
 * Interface represent capture capabilities. Use get() method after equals() to retrieve captured balue.
 * @param <T>
 */
public interface Capture<T extends OtpErlangObject> {
    T get();
}
