package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.OtpErlangRef;
import com.google.common.base.MoreObjects;

public class OtpErlangAnyRef extends BaseCapture<OtpErlangRef> implements Capture<OtpErlangRef> {
    public OtpErlangAnyRef() {
        super(OtpErlangRef.class);
    }

    @Override
    public String toString() {
        return
            MoreObjects
                .toStringHelper("_ref")
                .add("capture", get())
                .omitNullValues()
                .toString();
    }
}
