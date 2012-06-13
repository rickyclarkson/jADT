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
package com.pogofish.jadt.samples.ast;

// START SNIPPET: imports

import static com.pogofish.jadt.samples.ast.data.Arg.*;
import static com.pogofish.jadt.samples.ast.data.Expression.*;
import static com.pogofish.jadt.samples.ast.data.Function.*;
import static com.pogofish.jadt.samples.ast.data.Statement.*;
import static com.pogofish.jadt.samples.ast.data.Type.*;
import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
// END SNIPPET: imports

import com.pogofish.jadt.samples.ast.data.*;

/**
 * This class shows how to use the code generated by the sample ast.  It is marked up with START SNIPPET and END SNIPPET boundaries to support
 * /JADT/src/site/apt/index.apt
 */
public class Usage {
    public Function sampleFunction() {
        // START SNIPPET: sampleFunction
        Function sampleFunction = _Function(_Int(), "addTwo",
                asList(_Arg(_Int(), "x"), _Arg(_Int(), "y")),
                asList(_Return(_Add(_Variable("x"), _Variable("y")))));
        // END SNIPPET: sampleFunction
        
        return sampleFunction;
    }

    // START SNIPPET: expressionLiterals
    public Set<Integer> expressionLiterals(Expression expression) {
        return expression.accept(new Expression.Visitor<Set<Integer>>() {
            @Override
            public Set<Integer> visit(Add x) {
                final Set<Integer> results = expressionLiterals(x.left);
                results.addAll(expressionLiterals(x.right));
                return results;
            }

            @Override
            public Set<Integer> visit(Variable x) {
                return new HashSet<Integer>();
            }

            @Override
            public Set<Integer> visit(IntLiteral x) {
                return new HashSet<Integer>(asList(x.value));
            }

            @Override
            public Set<Integer> visit(LongLiteral x) {
                return new HashSet<Integer>();
            }
        });
    }
    // END SNIPPET: expressionLiterals

    // START SNIPPET: hasReturn
    public boolean hasReturn(List<Statement> statements) {
        boolean hasReturn = false;
        for (Statement statement : statements) {
            hasReturn = hasReturn || statement.accept(new Statement.VisitorWithDefault<Boolean>() {
                @Override
                public Boolean visit(Return x) {
                    return true;
                }

                @Override
                public Boolean getDefault(Statement x) {
                    return false;
                }
            });
        }
        return hasReturn;
    }
    // END SNIPPET: hasReturn
}