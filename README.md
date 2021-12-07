# This project became private in case GitHub has no public access for maven repository. Maybe this project became public after github will provide NORMAL BASE SUPPORT for Maven repositories.

# erl-utils [![Build Status](https://travis-ci.org/wizardjedi/erl-utils.svg?branch=master)](https://travis-ci.org/wizardjedi/erl-utils) [![codecov](https://codecov.io/gh/wizardjedi/erl-utils/branch/master/graph/badge.svg)](https://codecov.io/gh/wizardjedi/erl-utils)
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
    <dependencies>
        <dependency>
            <groupId>com.a1systems.utils</groupId>
            <artifactId>erl-utils</artifactId>
            <version>1.1</version>
        </dependency>
    </dependencies>
```
# Classes

Package `com.a1systems.utils.erl` contains core helper classes.

## Erl shortcut helper
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

### Motivation example of pattern matching

In erlang we can use pattern matching for processing values. For example:

```erlang
Eshell V7.0  (abort with ^G)

1> ExampleList = [{prop1, "Value1"}, {prop2, make_ref()}].
[{prop1,"Value1"},{prop2,#Ref<0.0.2.29>}]

2> [{prop1, Value1}, {prop2, Value2}] = ExampleList.
[{prop1,"Value1"},{prop2,#Ref<0.0.2.29>}]

3> Value1.
"Value1"

4> Value2.
#Ref<0.0.2.29>
```

We want to have such(nearly) mechanism in Java.
So we created classes inherited from jInterface erlang objects and implemented `equals()` methods for such objects.
So you can write above code in Java like that.

```java
final OtpErlangList list =
        Erl.list(
                Erl.tuple(
                        Erl.atom("prop1"),
                        Erl.string("Value1")
                ),
                Erl.tuple(
                        Erl.atom("prop2"),
                        Erl.ref("ref", 1, 7)
                )
        );

final OtpErlangAnyList captureValue1 = Erl._list();
final OtpErlangAnyRef captureValue2 = Erl._ref();

final OtpErlangList pattern =
        Erl.list(
                Erl.tuple(
                        Erl.atom("prop1"),
                        captureValue1
                ),
                Erl.tuple(
                        Erl.atom("prop2"),
                        captureValue2
                )
        );

if (pattern.equals(list)) {
    System.out.println("Equal");
} else {
    System.out.println("Not equal");
}

System.out.println("Initial object: "+list);
System.out.println("Value1: "+captureValue1.get());
System.out.println("Value2: "+captureValue2.get());
```
Result:
```
Equal
Initial object: [{prop1,[86,97,108,117,101,49]},{prop2,#Ref<ref.1>}]
Value1: [86,97,108,117,101,49]
Value2: #Ref<ref.1>
```

### Capturing

Classes `com.a1systems.utils.erl.OtpErlangAny*` inherited from `com.a1systems.utils.erl.BaseCapture` and implement method `get()` (from `com.a1systems.utils.erl.Capture` interface) for retrieving values after using in `equals()`.

## Erlang process and gen_server

* `com.a1systems.utils.otp.ErlangProcess` - base class for represent erlang process. Process has name and reference to mail box for messages.
** Method `processMessage(OtpErlangObject msg)` - it's analogue to erlangs `receive` statement.
* `com.a1systems.utils.otp.GenServer` - base class for constructing gen_server like handlers in java.
** Method `OtpErlangObject handleCall(UUID messageUid, OtpErlangPid from, OtpErlangObject request)` is using for implement gen_servers logic

### Using `com.a1systems.utils.otp.GenServer`

!!!Now only `handle_call` is supported.

You have to implement your own handler and override `handler_call(...)` method. `com.a1systems.utils.otp.GenServer` get message, check for gen_server's call pattern, generate uniq id (UUID) for message and send result back after processing. 

Let's try realworld example

```java
public class Main {
    public static void main(String[] args) throws IOException, OtpErlangExit, OtpErlangDecodeException {

        OtpNode node = new OtpNode("gurka","test");

        final GenServer1 genServer = new GenServer1("gen_server1", node);

        OtpMbox mbox = genServer.getMbox();

        while (true) {
            OtpErlangObject msg = mbox.receive(1000);

            System.out.println("Got msg:" + msg);

            genServer.processMessage(msg);
        }
    }

    public static class GenServer1 extends GenServer {

        public GenServer1(String name, OtpNode node) {
            super(name, node);
        }

        @Override
        public OtpErlangObject handleCall(UUID messageUid, OtpErlangPid from, OtpErlangObject request) {
            return
                    Erl.tuple(
                            Erl.string("Request processed"),
                            Erl.tuple(
                                    Erl.string(messageUid.toString()),
                                    from,
                                    request
                            )
                    );
        }
    }
}
```

We created gen_srver that receive message and send it back with string "Request processed".
Run epmd in background mode, run application and run erlang node like this.

You have to set up cookie for remote node. Use host name from erl cli prompt.
```
$ erl -sname node1 -cookie test
Erlang/OTP 18 [erts-7.0] [source] [64-bit] [smp:8:8] [async-threads:10] [kernel-poll:false]

Eshell V7.0  (abort with ^G)
...
(node1@wiznote)1> erlang:set_cookie('gurka@wiznote', test).
(node1@wiznote)2> net_adm:ping('gurka@wiznote').
pong

(node1@wiznote)3> gen_server:call({gen_server1,'gurka@wiznote'}, {some, request, make_ref(),"List"}).               
{"Request processed",
 {"8d8ad962-64ca-4a42-81ef-ba2dd2463821",<0.59.0>,
  {some,request,#Ref<0.0.3.111>,"List"}}}
```

Console output

```
Got msg:{'$gen_call',{#Pid<node1@wiznote.59.0>,#Ref<node1@wiznote.112.3.0>},{some,request,#Ref<node1@wiznote.111.3.0>,"List"}}
```
