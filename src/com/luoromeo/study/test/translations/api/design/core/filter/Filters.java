package com.luoromeo.study.test.translations.api.design.core.filter;

import com.luoromeo.study.test.translations.api.design.core.Output;
import com.luoromeo.study.test.translations.api.design.core.Receiver;
import com.luoromeo.study.test.translations.api.design.core.Sender;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月24日 13:48
 * @modified By
 */
public class Filters {

    static class SpecificationOutputWrapper<T, ReceiverThrowableType extends Throwable> implements Output<T, ReceiverThrowableType> {

        final Output<T, ReceiverThrowableType> output;

        final Specification<T> specification;

        public SpecificationOutputWrapper(Output<T, ReceiverThrowableType> output, Specification<T> specification) {
            this.output = output;
            this.specification = specification;
        }

        @Override
        public <SendThrowableType extends Throwable> void receiveFrom(Sender<T, SendThrowableType> sender)
                throws ReceiverThrowableType, SendThrowableType {
            output.receiveFrom(new SpecificationSenderWrapper<>(sender, specification));
        }
    }

    static class SpecificationSenderWrapper<T, SenderThrowableType extends Throwable> implements Sender<T, SenderThrowableType> {

        final Sender<T, SenderThrowableType> sender;

        final Specification<T> specification;

        public SpecificationSenderWrapper(Sender<T, SenderThrowableType> sender, Specification<T> specification) {
            this.sender = sender;
            this.specification = specification;
        }

        public <ReceiverThrowableType extends Throwable> void sendTo(Receiver<T, ReceiverThrowableType> receiver)
                throws ReceiverThrowableType, SenderThrowableType {
            sender.sendTo(new SpecificationReceiverWrapper<T, ReceiverThrowableType>(receiver, specification));
        }
    }

    static class SpecificationReceiverWrapper<T, ReceiverThrowableType extends Throwable> implements Receiver<T, ReceiverThrowableType> {

        final Receiver<T, ReceiverThrowableType> receiver;

        final Specification<T> specification;

        public SpecificationReceiverWrapper(Receiver<T, ReceiverThrowableType> receiver, Specification<T> specification) {
            this.receiver = receiver;
            this.specification = specification;
        }

        public void receive(T item) throws ReceiverThrowableType {
            if (specification.test(item)) {
                receiver.receive(item);
            }
        }

        public void finished() throws ReceiverThrowableType {
            receiver.finished();
        }
    }

    public static <T, ReceiverThrowableType extends Throwable> Output<T, ReceiverThrowableType> filter(Specification<T> specification,
            final Output<T, ReceiverThrowableType> output) {
        return new SpecificationOutputWrapper<T, ReceiverThrowableType>(output, specification);
    }

    static class FunctionOutputWrapper<From, To, ReceiverThrowableType extends Throwable> implements Output<From, ReceiverThrowableType> {

        final Output<To, ReceiverThrowableType> output;

        final Function<From, To> function;

        public FunctionOutputWrapper(Output<To, ReceiverThrowableType> output, Function<From, To> function) {
            this.output = output;
            this.function = function;
        }

        @Override
        public <SendThrowableType extends Throwable> void receiveFrom(Sender<From, SendThrowableType> sender)
                throws ReceiverThrowableType, SendThrowableType {
            output.receiveFrom(new FunctionSendWrapper<>(sender, function));
        }
    }

    static class FunctionSendWrapper<From, To, SenderThrowableType extends Throwable> implements Sender<To, SenderThrowableType> {

        final Sender<From, SenderThrowableType> sender;

        final Function<From, To> function;

        public FunctionSendWrapper(Sender<From, SenderThrowableType> sender, Function<From, To> function) {
            this.sender = sender;
            this.function = function;
        }

        @Override
        public <ReceiverThrowableType extends Throwable> void sendTo(Receiver<To, ReceiverThrowableType> receiver)
                throws ReceiverThrowableType, SenderThrowableType {
            sender.sendTo(new FunctionReceiverWrapper<>(receiver, function));
        }
    }

    static class FunctionReceiverWrapper<From, To, ReceiverThrowableType extends Throwable> implements Receiver<From, ReceiverThrowableType> {

        final Receiver<To, ReceiverThrowableType> receiver;

        final Function<From, To> function;

        public FunctionReceiverWrapper(Receiver<To, ReceiverThrowableType> receiver, Function<From, To> function) {
            this.receiver = receiver;
            this.function = function;
        }

        @Override
        public void receive(From item) throws ReceiverThrowableType {
            receiver.receive(function.map(item));
        }

        @Override
        public void finished() throws ReceiverThrowableType {
            receiver.finished();
        }
    }

    public static <From, To, ReceiverThrowableType extends Throwable> Output<From, ReceiverThrowableType> filter(Function<From, To> function,
            final Output<To, ReceiverThrowableType> output) {
        return new FunctionOutputWrapper<>(output, function);
    }

    private Filters() {
    }
}
