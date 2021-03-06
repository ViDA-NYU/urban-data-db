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
package org.urban.data.db.term;

import java.io.PrintWriter;
import org.urban.data.core.object.Entity;
import org.urban.data.core.set.IDSet;
import org.urban.data.core.set.ImmutableIDSet;
import org.urban.data.core.profiling.datatype.label.DataType;
import org.urban.data.core.profiling.datatype.DefaultDataTypeAnnotator;
import org.urban.data.db.eq.EQ;

/**
 * A term in a database. Each term has a unique identifier and a unique name.
 * The database term also has a list of columns that contain the term.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class Term extends Entity implements EQ {
    
    private final IDSet _columns;
    
    public Term(int id, String value, IDSet columns) {
        
        super(id, value);
        
        _columns = columns;
    }

    @Override
    public IDSet columns() {
        
        return _columns;
    }

    @Override
    public IDSet terms() {

        return new ImmutableIDSet(this.id());
    }

    /**
     * The data type of the term value.
     * 
     * @return 
     */
    public DataType type() {
        
        return new DefaultDataTypeAnnotator().getType(this.name());
    }

    @Override
    public void write(PrintWriter out) {

        out.println(
                this.id() + "\t" +
                this.name() + "\t" +
                this.columns().toIntString()
        );
    }
}
