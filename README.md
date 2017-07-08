# erl-utils
This library contains set of helper methods for Erlang (https://www.erlang.org/) jInterface (http://erlang.org/doc/apps/jinterface/jinterface_users_guide.html).

# Using in project
We use GitHub as MVN repo (Yes, I know that it's bad. But publishing to a public repo is more difficult.). 

1. Add link to repo to you `pom.xml`
```xml
    <repositories>
        <repository>
            <id>erl-utils-mvn-repo</id>
            <url>https://raw.github.com/wizardjedi/erl-utils/mvn-repo/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>
```
2. Add `erl-utils` to dependencies in pom.xml
```xml
    <repositories>
        <repository>
            <id>erl-utils-mvn-repo</id>
            <url>https://raw.github.com/wizardjedi/erl-utils/mvn-repo/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>
```
# Classes

Package `com.a1systems.utils.erl` contains core helper classes.

## Erl
Class `com.a1systems.utils.erl.Erl` contains helper methods for creating Erlang object.

Compare this
```java
OtpErlangAtom atom = new OtpErlangAtom(atomName);
```

with this

```java
Erl.atom("SomeAtom");
```

Static methods for constructing Erlang objects:
 * `OtpErlangAtom atom(String atomName)` - create Erlang atom with given name
 * `OtpErlangTuple tuple(OtpErlangObject... objects)` - create Erlang tuple (`{a,b,c}`) with given Erlang objects
 * `OtpErlangList string(String str)` - create erlang string (actually list of characters)
 * `OtpErlangBinary binstring(String str, Charset charset)` - create erlang binary string with charset
 * `OtpErlangBinary binstring(String str, String charset)` - create erlang binary string with charset name
 * `OtpErlangBinary binstring(String str)` - create erlang binary string with default charset
 * `OtpErlangList list(OtpErlangObject... objects)` - create erlang list of objects
 * `OtpErlangRef ref(String s, int a, int b)` - create erlang reference
 * `OtpErlangMap map(OtpErlangObject[] keys, OtpErlangObject... values)` - create erlang map with arrays of keys and values
 * `OtpErlangMap map(Map<String, OtpErlangObject> map)` - create erlang map from java map (shortcut)
 * `OtpErlangException exception(String msg)` - create erlang exception
 * `OtpErlangBoolean bool(boolean flag)` - create erlang boolean atom (true, false)
 * `OtpErlangBoolean boolTrue()` - create erlang true atom (shortcut)
 * `OtpErlangBoolean boolFalse()` - create erlang false atom (shortcut)
 * `OtpErlangLong longVal(long val)` - create erlang long value
 * `OtpErlangPid pid(String node, int id, int serial, int creation)` - create erlang pid

Static methods for constructing pattern matching helpers (see pattern matching section)
 * `OtpErlangAny _any()` - return reference to `Erl.ANY`. It's analogue for erlang's _ (underscore). It's object without capturing value.
 * `OtpErlangAnyObject _object()` - reference to any term with capture capabilities
 * `OtpErlangAnyAtom _atom()` - reference to any atom with capture capabilities
 * `OtpErlangAnyPid _pid()` - reference to any pid with capture capabilities
 * `OtpErlangAnyPid _map()` - reference to any map with capture capabilities
 * `OtpErlangAnyRef _ref()` - reference to any ref with capture capabilities
 * `OtpErlangAnyList _list()` - reference to any list with capture capabilities
 * `OtpErlangAnyTuple _tuple()` - reference to any tuple with capture capabilities
 * `OtpErlangAnyBinary _bin()` - reference to any binary with capture capabilities
 * `OtpErlangAnyLong _long()` - reference to any long with capture capabilities

