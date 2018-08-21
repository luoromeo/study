package com.luoromeo.study.test.translations.api.design.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.luoromeo.study.test.translations.api.design.core.Output;
import com.luoromeo.study.test.translations.api.design.core.Receiver;
import com.luoromeo.study.test.translations.api.design.core.Sender;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月24日 11:36
 * @modified By
 */
public class Outputs {

    static class TextOutput implements Output<String, IOException> {

        final File destination;
        final Writer writer;

        public TextOutput(File destination) throws IOException {
            this.destination = destination;
            writer = new FileWriter(destination);
        }

        @Override
        public <SendThrowableType extends Throwable> void receiveFrom(Sender<String, SendThrowableType> sender)
                throws IOException, SendThrowableType {
            final TextFileReceiver textFileReceiver = new TextFileReceiver(writer);
            sender.sendTo(textFileReceiver);
        }
    }

    static class TextFileReceiver implements Receiver<String, IOException> {

        final Writer writer;

        public TextFileReceiver(Writer writer) {
            this.writer = writer;
        }

        @Override
        public void receive(String item) throws IOException {
            writer.write(item);
        }

        @Override
        public void finished() throws IOException {
        }
    }

    public static Output<String, IOException> text(File destination) throws IOException {
        return new TextOutput(destination);
    }

    private Outputs() {}
}
