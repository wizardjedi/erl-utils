package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.OtpErlangMap;
import com.google.common.base.MoreObjects;

public class OtpErlangAnyMap extends BaseCapture<OtpErlangMap> implements Capture<OtpErlangMap> {
    public OtpErlangAnyMap() {
        super(OtpErlangMap.class);
    }

    @Override
    public String toString() {
        return
            MoreObjects
                .toStringHelper("_map")
                .add("capture", get())
                .omitNullValues()
                .toString();
    }
}
