package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.OtpErlangAtom;
import com.google.common.base.MoreObjects;

public class OtpErlangAnyAtom extends BaseCapture<OtpErlangAtom> implements Capture<OtpErlangAtom> {
    public OtpErlangAnyAtom() {
        super(OtpErlangAtom.class);
    }

    @Override
    public String toString() {
        return
            MoreObjects
                .toStringHelper("_atom")
                .add("capture", get())
                .omitNullValues()
                .toString();
    }
}
