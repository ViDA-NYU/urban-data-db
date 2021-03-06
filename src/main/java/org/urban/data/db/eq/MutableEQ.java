/*
 * Copyright 2018 New York University.
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
package org.urban.data.db.eq;

import java.io.PrintWriter;
import org.urban.data.core.object.IdentifiableObjectImpl;
import org.urban.data.core.set.HashIDSet;
import org.urban.data.core.set.IDSet;
import org.urban.data.db.column.ColumnElement;
import org.urban.data.db.term.Term;

/**
 * Equivalence class with mutable lists for columns and terms.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class MutableEQ extends IdentifiableObjectImpl implements EQ {
    
    private final HashIDSet _columns;
    private final HashIDSet _terms;
    
    public MutableEQ(int id, Term term) {
        
        super(id);
        
        _terms = new HashIDSet(term.id());
        _columns = new HashIDSet(term.columns());
    }
    
    public MutableEQ(int id, IDSet columns, IDSet terms) {
        
        super(id);
        
        _columns = new HashIDSet(columns);
        _terms = new HashIDSet(terms);
    }
    
    public void add(Term term) {
        
        // If terms a grouped based on similarity of their values prior to
        // grouping them based on the set of columns they occur in a term may
        // be added to an equivalence class that already contains the term. In
        // that case we return immediately.
        if (_terms.contains(term.id())) {
            return;
        }
        
        _terms.add(term.id());
        _columns.add(term.columns());
    }
    
    @Override
    public HashIDSet columns() {

        return _columns;
    }

    public int compareTo(ColumnElement el) {

        return Integer.compare(this.id(), el.id());
    }
    
    @Override
    public HashIDSet terms() {
        
        return _terms;
    }
    
    @Override
    public void write(PrintWriter out) {
	
        out.println(
                this.id() + "\t" +
                _terms.toIntString() + "\t" +
                _columns.toIntString()
        );
    }
}
