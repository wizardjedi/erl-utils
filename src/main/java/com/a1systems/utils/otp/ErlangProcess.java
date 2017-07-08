package com.a1systems.utils.otp;

import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpMbox;
import com.ericsson.otp.erlang.OtpNode;
import com.google.common.base.MoreObjects;

public abstract class ErlangProcess {
    protected OtpMbox mbox;

    protected OtpNode node;

    protected String name;

    public ErlangProcess() { /* */ }

    public ErlangProcess(String name, OtpNode node) {
        this.name = name;
        this.node = node;

        this.mbox = node.createMbox(name);
    }

    public abstract void processMessage(OtpErlangObject msg);

    public OtpMbox getMbox() {
        return mbox;
    }

    public void setMbox(OtpMbox mbox) {
        this.mbox = mbox;
    }

    public OtpNode getNode() {
        return node;
    }

    public void setNode(OtpNode node) {
        this.node = node;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return
            MoreObjects
                .toStringHelper(this)
                .add("name", getName())
                .toString();
    }
}
