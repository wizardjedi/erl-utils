package com.a1systems.utils.otp;

import com.a1systems.utils.erl.Erl;
import com.a1systems.utils.erl.OtpErlangAnyObject;
import com.a1systems.utils.erl.OtpErlangAnyPid;
import com.a1systems.utils.erl.OtpErlangAnyRef;
import com.ericsson.otp.erlang.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Abstract implementation of gen_server behaviour
 */
public abstract class GenServer extends ErlangProcess {
    /**
     * Label used in call() to gen_server
     */
    public static final String CALL_LABEL = "$gen_call";

    /**
     * Label used in case() to gen_server
     */
    public static final String CAST_LABEL = "$gen_cast";

    /**
     * Call label as atom
     */
    public static final OtpErlangAtom CALL_ATOM = Erl.atom(CALL_LABEL);

    /**
     * Cast label as atom
     */
    public static final OtpErlangAtom CAST_ATOM = Erl.atom(CAST_LABEL);

    private static final Logger logger = LoggerFactory.getLogger(GenServer.class);

    public GenServer() { /* */ }

    public GenServer(String name, OtpNode node) {
        super(name, node);
    }

    /**
     * Process message.
     *  * Generates uniq id of message
     *  * Check gen_server call convension
     *  * dispatch message to handleCall() method
     *  * return result to client
     *
     * For gen_server message looks like {'$gen_call',{From,Ref},Request}
     *
     * @see https://github.com/erlang/otp/blob/master/lib/stdlib/src/gen_server.erl
     * @see http://erlang.org/doc/design_principles/gen_server_concepts.html
     * @param msg
     */
    @Override
    public void processMessage(OtpErlangObject msg) {
        if (msg != null) {
            final UUID messageUid = UUID.randomUUID();

            final OtpErlangAnyRef anyRef = Erl._ref();
            final OtpErlangAnyPid anyPid = Erl._pid();

            final OtpErlangAnyObject anyTuple = Erl._object();

            final OtpErlangObject pattern =
                Erl
                    .tuple(
                        CALL_ATOM,
                        Erl.tuple(
                            anyPid,
                            anyRef
                        ),
                        anyTuple
                    );

            if (pattern.equals(msg)) {
                final OtpErlangPid from = anyPid.get();
                final OtpErlangRef ref = anyRef.get();
                final OtpErlangObject request = anyTuple.get();

                try {
                    logger
                        .trace(
                            "MSGUID:{} Call handleCall from:{}, request:{}",
                            messageUid,
                            from,
                            request
                        );

                    final long startProcessingMillis = System.currentTimeMillis();

                    final OtpErlangObject resp = handleCall(messageUid, from, request);

                    logger.trace("MSGUID:{} Got resp:{}", messageUid, resp);

                    sendAnswer(mbox, messageUid, from, ref, resp);

                    final long endProcessingMillis = System.currentTimeMillis();

                    logger
                        .info(
                            "MSGUID:{} Request processed in:{} ms",
                            messageUid,
                            endProcessingMillis - startProcessingMillis
                        );
                } catch (Exception e) {
                    logger
                        .error(
                            String
                                .format(
                                    "MSGUID:%s Error on process call",
                                    messageUid.toString()
                                ),
                            e
                        );
                }
            } else {
                logger.error("MSGUID:{} Message:{} is not a proper tuple", messageUid, msg);
            }
        } else {
            logger.trace("Message is null nothing to do.");
        }
    }

    /**
     * Process message and return result.
     *
     * @param messageUid
     * @param from
     * @param request
     * @return
     */
    public abstract OtpErlangObject handleCall(UUID messageUid, OtpErlangPid from, OtpErlangObject request);

    /**
     * Send message back to client.
     *
     * @param mbox
     * @param messageUid
     * @param from
     * @param ref
     * @param resp
     */
    public void sendAnswer(OtpMbox mbox, UUID messageUid, OtpErlangPid from, OtpErlangRef ref, OtpErlangObject resp) {
        final OtpErlangTuple t = Erl.tuple(ref, resp);

        logger.trace("MSGUID:{} Try to send resp:{}", messageUid, t);

        mbox.send(from, t);
    }
}
