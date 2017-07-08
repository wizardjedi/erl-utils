package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.OtpErlangLong;
import com.google.common.base.MoreObjects;

public class OtpErlangAnyLong extends BaseCapture<OtpErlangLong> implements Capture<OtpErlangLong> {
    public OtpErlangAnyLong() {
        super(OtpErlangLong.class);
    }

    @Override
    public String toString() {
        return
            MoreObjects
                .toStringHelper("_long")
                .add("capture", get())
                .omitNullValues()
                .toString();
    }
}
