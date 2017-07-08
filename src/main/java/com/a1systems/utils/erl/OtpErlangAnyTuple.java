package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.OtpErlangTuple;
import com.google.common.base.MoreObjects;

public class OtpErlangAnyTuple extends BaseCapture<OtpErlangTuple> implements Capture<OtpErlangTuple> {
    public OtpErlangAnyTuple() {
        super(OtpErlangTuple.class);
    }

    @Override
    public String toString() {
        return
            MoreObjects
                .toStringHelper("_tuple")
                .add("capture", get())
                .omitNullValues()
                .toString();
    }
}
