package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.OtpErlangBinary;
import com.google.common.base.MoreObjects;

public class OtpErlangAnyBinary extends BaseCapture<OtpErlangBinary> implements Capture<OtpErlangBinary> {
    public OtpErlangAnyBinary() {
        super(OtpErlangBinary.class);
    }

    @Override
    public String toString() {
        return
            MoreObjects
                .toStringHelper("_binary")
                .add("capture", get())
                .omitNullValues()
                .toString();
    }
}
