package com.luoromeo.study.test.translations.api.design.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.luoromeo.study.test.translations.api.design.core.Input;
import com.luoromeo.study.test.translations.api.design.core.Output;
import com.luoromeo.study.test.translations.api.design.core.Receiver;
import com.luoromeo.study.test.translations.api.design.core.Sender;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月24日 11:21
 * @modified By
 */
public class Inputs {

    static class TextInput implements Input<String, IOException> {

        final File source;

        final Reader reader;

        public TextInput(File source) throws IOException {
            this.source = source;
            reader = new FileReader(source);
        }

        @Override
        public <ReceiverThrowableType extends Throwable> void transferTo(Output<String, ReceiverThrowableType> output)
                throws IOException, ReceiverThrowableType {
            final TextSender sender = new TextSender(reader);
            output.receiveFrom(sender);
            try {
                reader.close();
            } catch (Exception e) {

            }
        }
    }

    static class TextSender implements Sender<String, IOException> {

        final Reader reader;

        BufferedReader bufferedReader;

        public TextSender(Reader reader) throws FileNotFoundException {
            this.reader = reader;
            this.bufferedReader = new BufferedReader(reader);
        }

        @Override
        public <ReceiverThrowableType extends Throwable> void sendTo(Receiver<String, ReceiverThrowableType> receiver)
                throws ReceiverThrowableType, IOException {
            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                receiver.receive(readLine);
            }
        }
    }

    public static Input<String, IOException> text(File source) throws IOException {
        return new TextInput(source);
    }

    private Inputs() {
    }

}
