package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.*;
import org.junit.Assert;
import org.junit.Test;

public class ErlTest {
    @Test
    public void testEquals() {
        final OtpErlangTuple pattern =
             Erl.tuple(
                 Erl.atom("$gen_call"),
                 Erl.tuple(
                     Erl._pid(),
                     Erl._ref()
                 ),
                 Erl._any()
             );


         final OtpErlangTuple source =
             Erl.tuple(
                 Erl.atom("$gen_call"),
                 Erl.tuple(
                     Erl.pid("a",0,0,0),
                     Erl.ref("s", 0,0)
                 ),
                 Erl.tuple(
                     Erl.atom("Hello")
                 )
             );

        Assert.assertTrue(pattern.equals(source));
    }

    @Test
    public void testAny() {
        final OtpErlangAny any = new OtpErlangAny();

        final OtpErlangAtom anyAtom = new OtpErlangAtom("anyAtom");

        Assert.assertTrue(any.equals(anyAtom));

        Assert.assertTrue(any.equals(new OtpErlangRef("anyAtom", 0, 0)));

        Assert.assertTrue(any.equals(new OtpErlangPid("anyAtom", 0, 0,0)));

        Assert.assertTrue(any.equals(new OtpErlangList("anyAtom")));
        Assert.assertTrue(any.equals(new OtpErlangList(new OtpErlangObject[]{})));
        Assert.assertTrue(any.equals(new OtpErlangList(new OtpErlangObject[]{anyAtom})));

        Assert.assertTrue(any.equals(new OtpErlangTuple(anyAtom)));
        Assert.assertTrue(any.equals(new OtpErlangTuple(new OtpErlangObject[]{})));
        Assert.assertTrue(any.equals(new OtpErlangTuple(new OtpErlangObject[]{anyAtom})));
    }

    @Test
    public void testLong() {
        final OtpErlangAnyLong any = new OtpErlangAnyLong();

        final OtpErlangAtom anyAtom = new OtpErlangAtom("anyAtom");

        Assert.assertTrue(any.equals(new OtpErlangLong(102)));

        Assert.assertFalse(any.equals(anyAtom));

        Assert.assertFalse(any.equals(new OtpErlangRef("anyAtom", 0, 0)));

        Assert.assertFalse(any.equals(new OtpErlangPid("anyAtom", 0, 0,0)));

        Assert.assertFalse(any.equals(new OtpErlangList("anyAtom")));
        Assert.assertFalse(any.equals(new OtpErlangList(new OtpErlangObject[]{})));
        Assert.assertFalse(any.equals(new OtpErlangList(new OtpErlangObject[]{anyAtom})));

        Assert.assertFalse(any.equals(new OtpErlangTuple(anyAtom)));
        Assert.assertFalse(any.equals(new OtpErlangTuple(new OtpErlangObject[]{})));
        Assert.assertFalse(any.equals(new OtpErlangTuple(new OtpErlangObject[]{anyAtom})));
    }

    @Test
    public void testObject() {
        final OtpErlangAnyObject any = new OtpErlangAnyObject();

        final OtpErlangAtom anyAtom = new OtpErlangAtom("anyAtom");

        Assert.assertTrue(any.equals(new OtpErlangLong(102)));

        Assert.assertTrue(any.equals(anyAtom));

        Assert.assertTrue(any.equals(new OtpErlangRef("anyAtom", 0, 0)));

        Assert.assertTrue(any.equals(new OtpErlangPid("anyAtom", 0, 0,0)));

        Assert.assertTrue(any.equals(new OtpErlangList("anyAtom")));
        Assert.assertTrue(any.equals(new OtpErlangList(new OtpErlangObject[]{})));
        Assert.assertTrue(any.equals(new OtpErlangList(new OtpErlangObject[]{anyAtom})));

        Assert.assertTrue(any.equals(new OtpErlangTuple(anyAtom)));
        Assert.assertTrue(any.equals(new OtpErlangTuple(new OtpErlangObject[]{})));
        Assert.assertTrue(any.equals(new OtpErlangTuple(new OtpErlangObject[]{anyAtom})));
    }
}
