/*
 * Copyright 2019 New York University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.urban.data.db.term;

import org.urban.data.core.object.filter.ObjectFilter;
import org.urban.data.core.set.HashObjectSet;
import org.urban.data.core.set.IdentifiableObjectSet;

/**
 * Term consumer to filter a set of column terms.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class ColumnTermFilter implements TermConsumer {
    
    private final ObjectFilter _filter;
    private final HashObjectSet<ColumnTerm> _terms;

    public ColumnTermFilter(ObjectFilter filter) {

        _filter = filter;
        _terms = new HashObjectSet();
    }

    @Override
    public void close() {

    }

    @Override
    public void consume(ColumnTerm term) {

        if (_filter.contains(term.id())) {
            _terms.add(term);
        }
    }

    @Override
    public void open() {

    }

    public IdentifiableObjectSet<ColumnTerm> terms() {

        return _terms;
    }
}