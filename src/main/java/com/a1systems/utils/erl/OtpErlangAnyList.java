package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.OtpErlangList;
import com.google.common.base.MoreObjects;

public class OtpErlangAnyList extends BaseCapture<OtpErlangList> implements Capture<OtpErlangList> {
    public OtpErlangAnyList() {
        super(OtpErlangList.class);
    }

    @Override
    public String toString() {
        return
            MoreObjects
                .toStringHelper("_list")
                .add("capture", get())
                .omitNullValues()
                .toString();
    }
}
