package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.OtpErlangObject;

public interface Capture<T extends OtpErlangObject> {
    T get();
}
