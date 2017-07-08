package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Utility class for construct Erlang objects
 */
public final class Erl {
    public static final OtpErlangAny ANY = new OtpErlangAny();

    private Erl() { }

    public static OtpErlangAtom atom(String atomName) {
        return new OtpErlangAtom(atomName);
    }

    public static OtpErlangTuple tuple(OtpErlangObject... objects) {
        return new OtpErlangTuple(objects);
    }

    public static OtpErlangList string(String str) {
        return new OtpErlangList(str);
    }

    public static OtpErlangBinary binstring(String str, Charset charset) {
        return new OtpErlangBinary(str.getBytes(charset));
    }

    public static OtpErlangBinary binstring(String str, String charset) {
        return binstring(str, Charset.forName(charset));
    }

    public static OtpErlangBinary binstring(String str) {
        return binstring(str, StandardCharsets.UTF_8);
    }

    public static OtpErlangList list(OtpErlangObject... objects) {
        return new OtpErlangList(objects);
    }

    public static OtpErlangRef ref(String s, int a, int b) {
        return new OtpErlangRef(s, a, b);
    }

    public static OtpErlangMap map(OtpErlangObject[] keys, OtpErlangObject... values) {
        return new OtpErlangMap(keys, values);
    }

    public static OtpErlangMap map(Map<String, OtpErlangObject> map) {
        final int elementsCount = map.size();

        final OtpErlangObject[] keys = new OtpErlangObject[elementsCount];
        final OtpErlangObject[] values = new OtpErlangObject[elementsCount];

        int i = 0;
        for (final Map.Entry<String, OtpErlangObject> entry: map.entrySet()) {
            keys[i] = atom(entry.getKey());
            values[i] = entry.getValue();

            i++;
        }

        return map(keys, values);
    }

    public static OtpErlangException exception(String msg) {
        return new OtpErlangException(msg);
    }

    public static OtpErlangBoolean bool(boolean flag) {
        return new OtpErlangBoolean(flag);
    }

    public static OtpErlangBoolean boolTrue() {
        return bool(true);
    }

    public static OtpErlangBoolean boolFalse() {
        return bool(false);
    }

    public static OtpErlangLong longVal(long val) {
        return new OtpErlangLong(val);
    }

    public static OtpErlangPid pid(String node, int id, int serial, int creation) {
        return new OtpErlangPid(node, id,  serial, creation);
    }

    public static OtpErlangAny _any() {
        return ANY;
    }

    public static OtpErlangAnyObject _object() {
        return new OtpErlangAnyObject();
    }

    public static OtpErlangAnyAtom _atom() {
        return new OtpErlangAnyAtom();
    }

    public static OtpErlangAnyPid _pid() {
        return new OtpErlangAnyPid();
    }

    public static OtpErlangAnyPid _map() {
        return new OtpErlangAnyPid();
    }

    public static OtpErlangAnyRef _ref() {
        return new OtpErlangAnyRef();
    }

    public static OtpErlangAnyList _list() {
        return new OtpErlangAnyList();
    }

    public static OtpErlangAnyTuple _tuple() {
        return new OtpErlangAnyTuple();
    }

    public static OtpErlangAnyBinary _bin() {
        return new OtpErlangAnyBinary();
    }

    public static OtpErlangAnyLong _long() {
        return new OtpErlangAnyLong();
    }
}
