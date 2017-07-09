package com.a1systems.utils.erl;

import com.ericsson.otp.erlang.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Utility class for construct Erlang objects
 */
public final class Erl {
    /**
     * Represent ANY erlang term. Analogue to _ (underscore) in Erlang
     */
    public static final OtpErlangAny ANY = new OtpErlangAny();

    private Erl() { }

    /**
     * Create erlang atom with given name
     * @param atomName
     * @return
     */
    public static OtpErlangAtom atom(String atomName) {
        return new OtpErlangAtom(atomName);
    }

    /**
     * Create erlang tuple with given objects
     * @param objects
     * @return
     */
    public static OtpErlangTuple tuple(OtpErlangObject... objects) {
        return new OtpErlangTuple(objects);
    }

    /**
     * Create erlang string (actualy list) with given string
     * @param str
     * @return
     */
    public static OtpErlangList string(String str) {
        return new OtpErlangList(str);
    }

    /**
     * Create erlang bit string from byte array
     * @param bitStringBuffer
     * @return
     */
    public static OtpErlangBitstr bitString(byte[] bitStringBuffer) {
        return new OtpErlangBitstr(bitStringBuffer);
    }

    /**
     * Create erlang binary from byte array
     * @param buffer
     * @return
     */
    public static OtpErlangBinary binstring(byte[] buffer) {
        return new OtpErlangBinary(buffer);
    }

    /**
     * Create erlang binary from string with given charset
     * @param str
     * @param charset
     * @return
     */
    public static OtpErlangBinary binstring(String str, Charset charset) {
        return new OtpErlangBinary(str.getBytes(charset));
    }

    /**
     * Create erlang binary from string with given charset name
     * @param str
     * @param charset
     * @return
     */
    public static OtpErlangBinary binstring(String str, String charset) {
        return binstring(str, Charset.forName(charset));
    }

    /**
     * Create erlang binary from string with UTF8 charset
     * @param str
     * @return
     */
    public static OtpErlangBinary binstring(String str) {
        return binstring(str, StandardCharsets.UTF_8);
    }

    /**
     * Create erlang list with given object
     * @param objects
     * @return
     */
    public static OtpErlangList list(OtpErlangObject... objects) {
        return new OtpErlangList(objects);
    }

    /**
     * Create erlang ref (reference)
     * @param s
     * @param a
     * @param b
     * @return
     */
    public static OtpErlangRef ref(String s, int a, int b) {
        return new OtpErlangRef(s, a, b);
    }

    /**
     * Create erlang map with given keys and values
     * @param keys
     * @param values
     * @return
     */
    public static OtpErlangMap map(OtpErlangObject[] keys, OtpErlangObject... values) {
        return new OtpErlangMap(keys, values);
    }

    /**
     * Create erlang map with given java map
     * @param map
     * @return
     */
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

    /**
     * Create erlang exception with given string
     * @param msg
     * @return
     */
    public static OtpErlangException exception(String msg) {
        return new OtpErlangException(msg);
    }

    /**
     * Create erlang boolean atom with given value
     * @param flag
     * @return
     */
    public static OtpErlangBoolean bool(boolean flag) {
        return new OtpErlangBoolean(flag);
    }

    /**
     * Create erlang boolean true
     * @return
     */
    public static OtpErlangBoolean boolTrue() {
        return bool(true);
    }

    /**
     * Create erlang boolan false
     * @return
     */
    public static OtpErlangBoolean boolFalse() {
        return bool(false);
    }

    /**
     * Create erlang integer value
     * @param val
     * @return
     */
    public static OtpErlangInt intVal(int val) {
        return new OtpErlangInt(val);
    }

    /**
     * Create erlang short value
     * @param val
     * @return
     */
    public static OtpErlangShort shortVal(short val) {
        return new OtpErlangShort(val);
    }

    /**
     * Create erlang float value
     * @param val
     * @return
     */
    public static OtpErlangFloat floatVal(float val) {
        return new OtpErlangFloat(val);
    }

    /**
     * Create erlang double value
     * @param val
     * @return
     */
    public static OtpErlangDouble doubleVal(double val) {
        return new OtpErlangDouble(val);
    }

    /**
     * Create erlang long value
     * @param val
     * @return
     */
    public static OtpErlangLong longVal(long val) {
        return new OtpErlangLong(val);
    }

    /**
     * Create erlang pid
     * @param node
     * @param id
     * @param serial
     * @param creation
     * @return
     */
    public static OtpErlangPid pid(String node, int id, int serial, int creation) {
        return new OtpErlangPid(node, id,  serial, creation);
    }

    /**
     * Return reference to ANY term
     * @return
     */
    public static OtpErlangAny _any() {
        return ANY;
    }

    /**
     * Pattern matching: any object with capture capabilities
     * @return
     */
    public static OtpErlangAnyObject _object() {
        return new OtpErlangAnyObject();
    }

    /**
     * Pattern matching: any atom with capture capabilities
     * @return
     */
    public static OtpErlangAnyAtom _atom() {
        return new OtpErlangAnyAtom();
    }

    /**
     * Pattern matching: any pid with capture capabilities
     * @return
     */
    public static OtpErlangAnyPid _pid() {
        return new OtpErlangAnyPid();
    }

    /**
     * Pattern matching: any map with capture capabilities
     * @return
     */
    public static OtpErlangAnyPid _map() {
        return new OtpErlangAnyPid();
    }

    /**
     * Pattern matching: any reference with capture capabilities
     * @return
     */
    public static OtpErlangAnyRef _ref() {
        return new OtpErlangAnyRef();
    }

    /**
     * Pattern matching: any list with capture capabilities
     * @return
     */
    public static OtpErlangAnyList _list() {
        return new OtpErlangAnyList();
    }

    /**
     * Pattern matching: any tuple with capture capabilities
     * @return
     */
    public static OtpErlangAnyTuple _tuple() {
        return new OtpErlangAnyTuple();
    }

    /**
     * Pattern matching: any binary with capture capabilities
     * @return
     */
    public static OtpErlangAnyBinary _bin() {
        return new OtpErlangAnyBinary();
    }

    /**
     * Pattern matching: any long with capture capabilities
     * @return
     */
    public static OtpErlangAnyLong _long() {
        return new OtpErlangAnyLong();
    }
}
