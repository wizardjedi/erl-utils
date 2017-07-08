package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.OtpErlangObject;
import com.google.common.base.MoreObjects;

public class OtpErlangAnyObject extends BaseCapture<OtpErlangObject> implements Capture<OtpErlangObject> {
    public OtpErlangAnyObject() {
        super(OtpErlangObject.class);
    }

    @Override
    public String toString() {
        return
            MoreObjects
                .toStringHelper("_object")
                .add("capture", get())
                .omitNullValues()
                .toString();
    }
}
