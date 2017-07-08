package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpOutputStream;

public class OtpErlangAny extends OtpErlangObject {
    @Override
    public String toString() {
        return "_";
    }

    @Override
    public void encode(OtpOutputStream otpOutputStream) {
        throw new IllegalArgumentException("OtpErlanAny object used only for pattern matching");
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
