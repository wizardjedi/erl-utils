package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.OtpErlangPid;
import com.google.common.base.MoreObjects;

public class OtpErlangAnyPid extends BaseCapture<OtpErlangPid> implements Capture<OtpErlangPid> {
    public OtpErlangAnyPid() {
        super(OtpErlangPid.class);
    }

    @Override
    public String toString() {
        return
            MoreObjects
                .toStringHelper("_pid")
                .add("capture", get())
                .omitNullValues()
                .toString();
    }
}
