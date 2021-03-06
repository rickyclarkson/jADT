/*
Copyright 2012 James Iry

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.pogofish.jadt.emitter;

import com.pogofish.jadt.ast.Doc;
import com.pogofish.jadt.sink.Sink;
import com.pogofish.jadt.sink.SinkFactory;


/**
 * Dummy emitter for Doc that just outputs the srcInfo
 *
 * @author jiry
 */
public class DummyDocEmitter implements DocEmitter {
    private final Doc testDoc;
    private final String className;
    
    /**
     * Takes a String className that will be used to create a sink and a document that it will assert
     * it gets during emit
     */
    public DummyDocEmitter(Doc testDoc, String className) {
        super();
        this.testDoc = testDoc;
        this.className = className;
    }


    @Override
    public void emit(SinkFactory factory, Doc doc) {
        if (testDoc != doc) {
            throw new RuntimeException("testDoc and doc argument were not the same");
        }
        final Sink sink = factory.createSink(className);
        try {
            sink.write(doc.srcInfo);
        } finally {
            sink.close();
        }
    }

}
