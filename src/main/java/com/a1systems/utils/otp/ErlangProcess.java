package com.a1systems.utils.otp;

import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpMbox;
import com.ericsson.otp.erlang.OtpNode;
import com.google.common.base.MoreObjects;

/**
 * Abstract class for represent erlang process in Java node
 */
public abstract class ErlangProcess {
    /**
     * Mail box of process
     */
    protected OtpMbox mbox;

    /**
     * OTP Node of process
     */
    protected OtpNode node;

    /**
     * Name of process
     */
    protected String name;

    /**
     * Construct process with no values
     */
    public ErlangProcess() { /* */ }

    /**
     * Construct process and initialize fields
     * @param name
     * @param node
     */
    public ErlangProcess(String name, OtpNode node) {
        this.name = name;
        this.node = node;

        this.mbox = node.createMbox(name);
    }

    /**
     * Implement this method to process messages. It's analogue to receive statement in erlang.
     * @param msg
     */
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
