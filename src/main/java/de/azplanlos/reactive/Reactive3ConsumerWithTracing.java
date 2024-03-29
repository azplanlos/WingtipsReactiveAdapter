/*
 * Copyright (c) 2022. ZDF Geschäftsbereich Außenstudios
 * Autor: Andreas Zöllner
 */

package de.azplanlos.reactive;

import com.nike.internal.util.Pair;
import com.nike.wingtips.Span;
import com.nike.wingtips.Tracer;
import com.nike.wingtips.util.asynchelperwrapper.ConsumerWithTracing;
import io.reactivex.rxjava3.functions.Consumer;
import org.slf4j.MDC;

import java.util.Deque;
import java.util.Map;

public class Reactive3ConsumerWithTracing<T> extends ConsumerWithTracing<T> implements Consumer<T> {
    /**
     * Constructor that extracts the current tracing and MDC information from the current thread using {@link
     * Tracer#getCurrentSpanStackCopy()} and {@link MDC#getCopyOfContextMap()}, and forwards the information to
     * the ConsumerWithTracing(Consumer, Deque, Map)
     * constructor. That tracing and MDC information will be associated with the thread when the given operation is
     * executed.
     *
     * <p>The operation you pass in cannot be null (an {@link IllegalArgumentException} will be thrown if you pass in
     * null for the operation).
     *
     * @param origConsumer
     */
    public Reactive3ConsumerWithTracing(java.util.function.Consumer<T> origConsumer) {
        super(origConsumer);
    }

    /**
     * Constructor that uses the given trace and MDC information, which will be associated with the thread when the
     * given operation is executed.
     *
     * <p>The operation you pass in cannot be null (an {@link IllegalArgumentException} will be thrown if you pass in
     * null for the operation).
     *
     * <p>The {@link Pair} can be null, or you can pass null for the left and/or right side of the pair, and no error
     * will be thrown. Any trace or MDC info that is null means the corresponding info will not be available to the
     * thread when the operation is executed however.
     *
     * <p>You can pass in a TracingState for clearer less verbose code since it extends
     * {@code Pair<Deque<Span>, Map<String, String>>}.
     *
     * @param origConsumer
     * @param originalThreadInfo
     */
    public Reactive3ConsumerWithTracing(java.util.function.Consumer<T> origConsumer, Pair<Deque<Span>, Map<String, String>> originalThreadInfo) {
        super(origConsumer, originalThreadInfo);
    }

    /**
     * Constructor that uses the given trace and MDC information, which will be associated with the thread when the
     * given operation is executed.
     *
     * <p>The operation you pass in cannot be null (an {@link IllegalArgumentException} will be thrown if you pass in
     * null for the operation).
     *
     * <p>The trace and/or MDC info can be null and no error will be thrown, however any trace or MDC info that is null
     * means the corresponding info will not be available to the thread when the operation is executed.
     *
     * @param origConsumer
     * @param spanStackForExecution
     * @param mdcContextMapForExecution
     */
    public Reactive3ConsumerWithTracing(java.util.function.Consumer<T> origConsumer, Deque<Span> spanStackForExecution, Map<String, String> mdcContextMapForExecution) {
        super(origConsumer, spanStackForExecution, mdcContextMapForExecution);
    }


    /**
     * Equivalent to calling {@code new ConsumerWithTracing(origConsumer)} - this allows you to do a static method
     * import for cleaner looking code in some cases. This method ultimately extracts the current tracing and MDC
     * information from the current thread using {@link Tracer#getCurrentSpanStackCopy()} and {@link
     * MDC#getCopyOfContextMap()}. That tracing and MDC information will be associated with the thread when the given
     * operation is executed.
     *
     * <p>The operation you pass in cannot be null (an {@link IllegalArgumentException} will be thrown if you pass in
     * null for the operation).
     *
     * @return {@code new ConsumerWithTracing(origConsumer)}.
     * @see ConsumerWithTracing#ConsumerWithTracing(java.util.function.Consumer)
     * @see ConsumerWithTracing
     */
    public static <T> Reactive3ConsumerWithTracing<T> withTracing(java.util.function.Consumer<T> origConsumer) {
        return new Reactive3ConsumerWithTracing<>(origConsumer);
    }
}
