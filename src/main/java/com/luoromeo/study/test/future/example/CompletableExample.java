package com.luoromeo.study.test.future.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月22日 15:31
 * @modified By
 */
public class CompletableExample {

    static Random random = new Random();

    /**
     * @description 一个完成的CompletableFuture
     *              最简单的例子就是使用一个预定义的结果创建一个完成的CompletableFuture,通常我们会在计算的开始阶段使用它。
     *              getNow(null)方法在future完成的情况下会返回结果，就比如上面这个例子，否则返回null (传入的参数)。
     * @author zhanghua.luo
     * @date 2018年05月22日 03:48:17
     * @param
     * @return
     */
    static void completedFutureExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        assertEquals("message", cf.getNow(null));
    }

    /**
     * @description 一个简单的异步例子
     *
     *              CompletableFuture的方法如果以Async结尾，它会异步的执行(没有指定executor的情况下)，
     *              异步执行通过ForkJoinPool实现， 它使用守护线程去执行任务。注意这是CompletableFuture的特性，
     *              其它CompletionStage可以override这个默认的行为。
     * @author zhanghua.luo
     * @date 2018年05月22日 03:35:34
     * @param
     * @return
     */
    static void runAsyncExample() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
        });
        assertFalse(cf.isDone());
        sleepEnough();
        assertTrue(cf.isDone());
    }

    /**
     * @description 下面这个例子使用前面 #1(实例1) 的完成的CompletableFuture，
     *              #1返回结果为字符串message,然后应用一个函数把它变成大写字母。
     *              then意味着这个阶段的动作发生当前的阶段正常完成之后。本例中，当前节点完成，返回字符串message。
     *              Apply意味着返回的阶段将会对结果前一阶段的结果应用一个函数。
     *              函数的执行会被阻塞，这意味着getNow()只有打斜操作被完成后才返回。
     * @author zhanghua.luo
     * @date 2018年05月22日 03:49:03
     * @return
     */
    static void thenApplyExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        assertEquals("MESSAGE", cf.getNow(null));
    }

    /**
     * @description 通过调用异步方法(方法后边加Async后缀)，串联起来的CompletableFuture可以异步地执行（使用ForkJoinPool.commonPool()
     * @author zhanghua.luo
     * @date 2018年05月22日 04:01:19
     * @param
     * @return
     */
    static void thenApplyAsyncExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        });
        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }

    static ExecutorService excutor = Executors.newFixedThreadPool(3, new ThreadFactory() {

        int count = 1;

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "custom-executor-" + count++);
        }
    });

    /**
     * @description 异步方法一个非常有用的特性就是能够提供一个Executor来异步地执行CompletableFuture。这个例子演示了如何使用一个固定大小的线程池来应用大写函数。
     * @author zhanghua.luo
     * @date 2018年05月22日 04:05:08
     * @param
     * @return
     */
    static void thenApplyAsyncWithExecutorExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
            assertFalse(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        }, excutor);
        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());

    }

    /**
     * @description 如果下一阶段接收了当前阶段的结果，但是在计算的时候不需要返回值(它的返回类型是void)，
     *              那么它可以不应用一个函数，而是一个消费者， 调用方法也变成了thenAccept:
     * @author zhanghua.luo
     * @date 2018年05月22日 04:13:17
     * @param
     * @return
     */
    static void thenAcceptExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("thenAccept message").thenAccept(result::append);
        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * @description 同样，可以使用thenAcceptAsync方法， 串联的CompletableFuture可以异步地执行。
     * @author zhanghua.luo
     * @date 2018年05月22日 04:16:11
     * @param
     * @return
     */
    static void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message").thenAcceptAsync(result::append);
        cf.join();
        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * @description 首先我们创建了一个CompletableFuture,
     *              完成后返回一个字符串message,接着我们调用thenApplyAsync方法，它返回一个CompletableFuture。这个方法在第一个函数完成后，异步地应用转大写字母函数。
     *              这个例子还演示了如何通过delayedExecutor(timeout, timeUnit)延迟执行一个异步任务。
     *              我们创建了一个分离的handler阶段： exceptionHandler， 它处理异常异常，在异常情况下返回message
     *              upon cancel。 下一步我们显式地用异常完成第二个阶段。
     *              在阶段上调用join方法，它会执行大写转换，然后抛出CompletionException（正常的join会等待1秒，然后得到大写的字符串。不过我们的例子还没等它执行就完成了异常），
     *              然后它触发了handler阶段。
     * @author zhanghua.luo
     * @date 2018年05月22日 04:36:07
     * @param
     * @return
     */
    static void completeExceptionallyExample() {
        // CompletableFuture cf =
        // CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
        // CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase);
        CompletableFuture exceptionHandler = cf.handle((s, th) -> (th != null) ? "message upon cancel" : "");
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
        try {
            cf.join();
            fail("Should have thrown an exception");
        } catch (CompletionException ex) { // just for testing
            assertEquals("completed exceptionally", ex.getCause().getMessage());
        }
        assertEquals("message upon cancel", exceptionHandler.join());
    }

    static void cancelExample() {
        // CompletableFuture cf =
        // CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
        // CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase);
        CompletableFuture cf2 = cf.exceptionally(throwable -> "canceled message");
        assertTrue("Was not canceled", cf.cancel(true));
        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
        assertEquals("canceled message", cf2.join());
    }

    /**
     * applyToEither(取2个future中最先返回的,有返回值)
     * @description 下面的例子创建了CompletableFuture, applyToEither处理两个阶段，
     *              在其中之一上应用函数(包保证哪一个被执行)。 本例中的两个阶段一个是应用大写转换在原始的字符串上， 另一个阶段是应用小些转换。
     * @author zhanghua.luo
     * @date 2018年05月22日 04:53:10
     * @param
     * @return
     */
    @SuppressWarnings("all")
    static void applyToEitherExample() {
        String original = "Message";
        CompletableFuture cf1 = CompletableFuture.completedFuture(original).thenAcceptAsync(CompletableExample::delayedUpperCase);
        CompletableFuture<String> cf2 = cf1.applyToEither(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                s -> s + " from applyToEither");
        assertTrue(cf2.join().endsWith(" from applyToEither"));
    }

    /**
     * acceptEither(取2个future中最先返回的,无返回值)
     * @description 和前一个例子很类似了，只不过我们调用的是消费者函数 (Function变成Consumer):
     * @author zhanghua.luo
     * @date 2018年05月22日 05:05:24
     * @param
     * @return
     */
    static void acceptEitherExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApplyAsync(CompletableExample::delayedUpperCase).acceptEither(
                CompletableFuture.completedFuture(original).thenApplyAsync(CompletableExample::delayedLowerCase),
                s -> result.append(s).append("acceptEither"));
        cf.join();
        assertTrue("Result was empty", result.toString().endsWith("acceptEither"));
    }

    /**
     * @description 这个例子演示了依赖的CompletableFuture如果等待两个阶段完成后执行了一个Runnable。
     *              注意下面所有的阶段都是同步执行的，第一个阶段执行大写转换，第二个阶段执行小写转换。
     * @author zhanghua.luo
     * @date 2018年05月22日 05:23:07
     * @param
     * @return
     */
    static void runAfterBothExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
                .runAfterBoth(CompletableFuture.completedFuture(original).thenApply(String::toLowerCase), () -> result.append("done"));
        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * @description 使用BiConsumer处理两个阶段的结果
     *
     * @author zhanghua.luo
     * @date 2018年05月22日 05:31:49
     * @param
     * @return
     */
    static void thenAcceptBothExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
                .thenAcceptBoth(CompletableFuture.completedFuture(original).thenApply(String::toLowerCase), (s1, s2) -> result.append(s1).append(s2));
        assertEquals("MESSAGEmessage", result.toString());
    }

    /**
     * @description 使用BiFunction处理两个阶段的结果 如果CompletableFuture依赖两个前面阶段的结果，
     *              它复合两个阶段的结果再返回一个结果，我们就可以使用thenCombine()函数。整个流水线是同步的，所以getNow()会得到最终的结果，它把大写和小写字符串连接起来。
     * @author zhanghua.luo
     * @date 2018年05月22日 05:36:44
     * @param
     * @return
     */
    static void thenCombineExample() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(CompletableExample::delayedUpperCase)
                .thenCombine(CompletableFuture.completedFuture(original).thenApply(CompletableExample::delayedLowerCase), (s1, s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf.getNow(null));
    }

    /**
     * @description 异步使用BiFunction处理两个阶段的结果
     *              类似上面的例子，但是有一点不同：依赖的前两个阶段异步地执行，所以thenCombine()也异步地执行，即时它没有Async后缀。
     * @author zhanghua.luo
     * @date 2018年05月22日 05:42:28
     * @param
     * @return
     */
    static void thenCombineAsyncExample() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApplyAsync(CompletableExample::delayedUpperCase)
                .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(CompletableExample::delayedLowerCase), (s1, s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf.join());
    }

    /**
     * @description 我们可以使用thenCompose()完成上面两个例子。这个方法等待第一个阶段的完成(大写转换)，
     *              它的结果传给一个指定的返回CompletableFuture函数，它的结果就是返回的CompletableFuture的结果。
     *
     *              有点拗口，但是我们看例子来理解。函数需要一个大写字符串做参数，然后返回一个CompletableFuture,
     *              这个CompletableFuture会转换字符串变成小写然后连接在大写字符串的后面。
     * @author zhanghua.luo
     * @date 2018年05月22日 05:55:37
     * @param
     * @return
     */
    static void thenComposeExample() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(CompletableExample::delayedUpperCase).thenCompose(
                upper -> CompletableFuture.completedFuture(original).thenApply(CompletableExample::delayedLowerCase).thenApply(s -> upper + s));
        assertEquals("MESSAGEmessage", cf.join());
    }

    /**
     * @description 当几个阶段中的一个完成，创建一个完成的阶段 下面的例子演示了当任意一个CompletableFuture完成后，
     *              创建一个完成的CompletableFuture. 待处理的阶段首先创建，
     *              每个阶段都是转换一个字符串为大写。因为本例中这些阶段都是同步地执行(thenApply),
     *              从anyOf中创建的CompletableFuture会立即完成，这样所有的阶段都已完成，我们使用whenComplete(BiConsumer<?super
     *              Object, ? super Throwable> action)处理完成的结果。
     * @author zhanghua.luo
     * @date 2018年05月22日 05:59:11
     * @param
     * @return
     */
    @SuppressWarnings("all")
    static void anyOfExample() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture<String>> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s))).collect(Collectors.toList());
        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th) -> {
            if (th == null) {
                assertTrue(isUpperCase((String) res));
                result.append(res);
            }
        });
        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * @description 当所有的阶段都完成后创建一个阶段 上一个例子是当任意一个阶段完成后接着处理，接下来的两个例子演示当所有的阶段完成后才继续处理,
     *              同步地方式和异步地方式两种。
     * @author zhanghua.luo
     * @date 2018年05月22日 06:08:22
     * @param
     * @return
     */
    @SuppressWarnings("all")
    static void allOfExample() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture<String>> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s))).collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v, th) -> {
            futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow(null))));
            result.append("done");
        });
        assertTrue("Result was empty", result.length() > 0);
    }

    @SuppressWarnings("all")
    static void allOfAsyncExample() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture<String>> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(s -> delayedUpperCase(s))).collect(Collectors.toList());
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v, th) -> {
            futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow(null))));
            result.append("done");
        });
        allOf.join();
        assertTrue("Result was empty", result.length() > 0);
    }

    public static void main(String[] args) {
        runAfterBothExample();
    }

    private static boolean isUpperCase(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static String delayedUpperCase(String s) {
        randomSleep();
        return s.toUpperCase();
    }

    private static String delayedLowerCase(String s) {
        randomSleep();
        return s.toLowerCase();
    }

    private static void randomSleep() {
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sleepEnough() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
