/*
 * Copyright (c) 2022. ZDF Geschäftsbereich Außenstudios
 * Autor: Andreas Zöllner
 */

package de.azplanlos.reactive;

import com.nike.internal.util.Pair;
import com.nike.wingtips.Span;
import com.nike.wingtips.Tracer;
import com.nike.wingtips.util.asynchelperwrapper.FunctionWithTracing;
import io.reactivex.functions.Function;
import org.slf4j.MDC;

import java.util.Deque;
import java.util.Map;

public class ReactiveFunctionWithTracing<T, R> extends FunctionWithTracing<T, R> implements Function<T, R> {
    /**
     * Constructor that extracts the current tracing and MDC information from the current thread using {@link
     * Tracer#getCurrentSpanStackCopy()} and {@link MDC#getCopyOfContextMap()}, and forwards the information to
     * the FunctionWithTracing(Function, Deque, Map)
     * constructor. That tracing and MDC information will be associated with the thread when the given operation is
     * executed.
     *
     * <p>The operation you pass in cannot be null (an {@link IllegalArgumentException} will be thrown if you pass in
     * null for the operation).
     *
     * @param origFunction
     */
    public ReactiveFunctionWithTracing(java.util.function.Function<T, R> origFunction) {
        super(origFunction);
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
     * @param origFunction
     * @param originalThreadInfo
     */
    public ReactiveFunctionWithTracing(java.util.function.Function<T, R> origFunction, Pair<Deque<Span>, Map<String,
            String>> originalThreadInfo) {
        super(origFunction, originalThreadInfo);
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
     * @param origFunction
     * @param spanStackForExecution
     * @param mdcContextMapForExecution
     */
    public ReactiveFunctionWithTracing(java.util.function.Function<T, R> origFunction, Deque<Span> spanStackForExecution,
                                       Map<String, String> mdcContextMapForExecution) {
        super(origFunction, spanStackForExecution, mdcContextMapForExecution);
    }


    /**
     * Equivalent to calling {@code new FunctionWithTracing(origFunction)} - this allows you to do a static method
     * import for cleaner looking code in some cases. This method ultimately extracts the current tracing and MDC
     * information from the current thread using {@link Tracer#getCurrentSpanStackCopy()} and {@link
     * MDC#getCopyOfContextMap()}. That tracing and MDC information will be associated with the thread when the given
     * operation is executed.
     *
     * <p>The operation you pass in cannot be null (an {@link IllegalArgumentException} will be thrown if you pass in
     * null for the operation).
     *
     * @return {@code new FunctionWithTracing(origFunction)}.
     * @see FunctionWithTracing#FunctionWithTracing(java.util.function.Function)
     * @see FunctionWithTracing
     */
    public static <T, U> ReactiveFunctionWithTracing<T, U> withTracing(java.util.function.Function<T, U> origFunction) {
        return new ReactiveFunctionWithTracing<>(origFunction);
    }
}
