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
     * @param atomName for atom
     * @return erlang atom
     */
    public static OtpErlangAtom atom(String atomName) {
        return new OtpErlangAtom(atomName);
    }

    /**
     * Create erlang tuple with given objects
     * @param objects to store in tuple
     * @return erlang tuple
     */
    public static OtpErlangTuple tuple(OtpErlangObject... objects) {
        return new OtpErlangTuple(objects);
    }

    /**
     * Create erlang string (actualy list) with given string
     * @param str to convert to list
     * @return erlang list as string
     */
    public static OtpErlangList string(String str) {
        return new OtpErlangList(str);
    }

    /**
     * Create erlang bit string from byte array
     * @param bitStringBuffer to convert to bit string
     * @return erlang bitstring
     */
    public static OtpErlangBitstr bitString(byte[] bitStringBuffer) {
        return new OtpErlangBitstr(bitStringBuffer);
    }

    /**
     * Create erlang binary from byte array
     * @param buffer to convert to binary
     * @return erlang binary
     */
    public static OtpErlangBinary binstring(byte[] buffer) {
        return new OtpErlangBinary(buffer);
    }

    /**
     * Create erlang binary from string with given charset
     * @param str represent string
     * @param charset object
     * @return erlang binary
     */
    public static OtpErlangBinary binstring(String str, Charset charset) {
        return new OtpErlangBinary(str.getBytes(charset));
    }

    /**
     * Create erlang binary from string with given charset name
     * @param str value
     * @param charset name
     * @return erlang binary
     */
    public static OtpErlangBinary binstring(String str, String charset) {
        return binstring(str, Charset.forName(charset));
    }

    /**
     * Create erlang binary from string with UTF8 charset
     * @param str value
     * @return erlang binary
     */
    public static OtpErlangBinary binstring(String str) {
        return binstring(str, StandardCharsets.UTF_8);
    }

    /**
     * Create erlang list with given object
     * @param objects to store in list
     * @return erlang list
     */
    public static OtpErlangList list(OtpErlangObject... objects) {
        return new OtpErlangList(objects);
    }

    /**
     * Create erlang ref (reference)
     * @param s node name
     * @param a id
     * @param b creation
     * @return erlang ref
     */
    public static OtpErlangRef ref(String s, int a, int b) {
        return new OtpErlangRef(s, a, b);
    }

    /**
     * Create erlang map with given keys and values
     * @param keys for map
     * @param values for keys
     * @return erlang map
     */
    public static OtpErlangMap map(OtpErlangObject[] keys, OtpErlangObject... values) {
        return new OtpErlangMap(keys, values);
    }

    /**
     * Create erlang map with given java map
     * @param map to convert to erlang map
     * @return erlang map
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
     * @param msg for exception
     * @return erlang exception
     */
    public static OtpErlangException exception(String msg) {
        return new OtpErlangException(msg);
    }

    /**
     * Create erlang boolean atom with given value
     * @param flag value
     * @return erlang boolean
     */
    public static OtpErlangBoolean bool(boolean flag) {
        return new OtpErlangBoolean(flag);
    }

    /**
     * Create erlang boolean true
     * @return boolean true
     */
    public static OtpErlangBoolean boolTrue() {
        return bool(true);
    }

    /**
     * Create erlang boolan false
     * @return boolean false
     */
    public static OtpErlangBoolean boolFalse() {
        return bool(false);
    }

    /**
     * Create erlang integer value
     * @param val value
     * @return int
     */
    public static OtpErlangInt intVal(int val) {
        return new OtpErlangInt(val);
    }

    /**
     * Create erlang short value
     * @param val value
     * @return short
     */
    public static OtpErlangShort shortVal(short val) {
        return new OtpErlangShort(val);
    }

    /**
     * Create erlang float value
     * @param val value
     * @return float
     */
    public static OtpErlangFloat floatVal(float val) {
        return new OtpErlangFloat(val);
    }

    /**
     * Create erlang double value
     * @param val value
     * @return double
     */
    public static OtpErlangDouble doubleVal(double val) {
        return new OtpErlangDouble(val);
    }

    /**
     * Create erlang long value
     * @param val value
     * @return long
     */
    public static OtpErlangLong longVal(long val) {
        return new OtpErlangLong(val);
    }

    /**
     * Create erlang pid
     * @param node name
     * @param id of pid
     * @param serial value
     * @param creation value
     * @return pid
     */
    public static OtpErlangPid pid(String node, int id, int serial, int creation) {
        return new OtpErlangPid(node, id,  serial, creation);
    }

    /**
     * Return reference to ANY term
     * @return erlang any pattern matching helper
     */
    public static OtpErlangAny _any() {
        return ANY;
    }

    /**
     * Pattern matching: any object with capture capabilities
     * @return erlang any object pattern matching helper
     */
    public static OtpErlangAnyObject _object() {
        return new OtpErlangAnyObject();
    }

    /**
     * Pattern matching: any atom with capture capabilities
     * @return erlang atom pattern matching helper
     */
    public static OtpErlangAnyAtom _atom() {
        return new OtpErlangAnyAtom();
    }

    /**
     * Pattern matching: any pid with capture capabilities
     * @return erlang pid pattern matching helper
     */
    public static OtpErlangAnyPid _pid() {
        return new OtpErlangAnyPid();
    }

    /**
     * Pattern matching: any map with capture capabilities
     * @return erlang map pattern matching helper
     */
    public static OtpErlangAnyPid _map() {
        return new OtpErlangAnyPid();
    }

    /**
     * Pattern matching: any reference with capture capabilities
     * @return erlang ref pattern matching helper
     */
    public static OtpErlangAnyRef _ref() {
        return new OtpErlangAnyRef();
    }

    /**
     * Pattern matching: any list with capture capabilities
     * @return erlang list pattern matching helper
     */
    public static OtpErlangAnyList _list() {
        return new OtpErlangAnyList();
    }

    /**
     * Pattern matching: any tuple with capture capabilities
     * @return erlang tuple pattern matching helper
     */
    public static OtpErlangAnyTuple _tuple() {
        return new OtpErlangAnyTuple();
    }

    /**
     * Pattern matching: any binary with capture capabilities
     * @return erlang binary pattern matching helper
     */
    public static OtpErlangAnyBinary _bin() {
        return new OtpErlangAnyBinary();
    }

    /**
     * Pattern matching: any long with capture capabilities
     * @return erlang long pattern matching helper
     */
    public static OtpErlangAnyLong _long() {
        return new OtpErlangAnyLong();
    }
}
