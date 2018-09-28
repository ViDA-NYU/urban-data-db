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
package org.urban.data.db.column.similarity;

import java.io.File;
import org.urban.data.core.util.count.IdentifiableCount;
import org.urban.data.core.util.count.IdentifiableCounterSet;
import org.urban.data.db.io.TermIndexReader;
import org.urban.data.db.term.ColumnTerm;
import org.urban.data.db.term.TermConsumer;

/**
 * Column weight factory that uses the total number of values in a column as
 * the scale factor.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class ColumnSizeWeightFactory extends ColumnWeightFactory {

    private class ColumnSizeComputer implements TermConsumer {

        private IdentifiableCounterSet _columns = null;
        
        @Override
        public void close() {

        }

        @Override
        public void consume(ColumnTerm term) {

	    for (IdentifiableCount col : term.columns()) {
                _columns.inc(col.id(), col.count());
            }
        }

        public IdentifiableCounterSet getColumnSizes() {
            
            return _columns;
        }
        
        @Override
        public void open() {

            _columns = new IdentifiableCounterSet();
        }
        
    }
    
    @Override
    public IdentifiableCounterSet getScales(File file) throws java.io.IOException {

        ColumnSizeComputer consumer = new ColumnSizeComputer();
        
        new TermIndexReader().read(file, consumer);
        
        return consumer.getColumnSizes();
    }
}