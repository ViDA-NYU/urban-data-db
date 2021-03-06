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
package org.urban.data.db;

import java.io.File;
import java.util.Iterator;
import org.urban.data.core.set.HashIDSet;
import org.urban.data.core.set.HashObjectSet;
import org.urban.data.core.set.IDSet;
import org.urban.data.core.set.IdentifiableObjectSet;
import org.urban.data.db.column.Column;
import org.urban.data.db.eq.EQ;
import org.urban.data.db.eq.EQReader;
import org.urban.data.db.term.TermIndexReader;

/**
 * A database is a set of columns. Each columns contains a list of node
 * identifier.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public class Database implements Iterable<Column> {
    
    private final HashObjectSet<Column> _columns;
    
    public <T extends EQ> Database(Iterable<T> nodes) {
        
        _columns = new HashObjectSet<>();
        
        for (T node : nodes) {
            for (int columnId : node.columns()) {
                Column column;
                if (!_columns.contains(columnId)) {
                    column = new Column(columnId);
                    _columns.add(column);
                } else {
                    column = _columns.get(columnId);
                }
                column.add(node.id());
            }
        }
    }
    
    public Database(EQReader reader) throws java.io.IOException {
	
        this(reader.read());
    }
    
    public Database(TermIndexReader reader) throws java.io.IOException {
	
        this(reader.read());
    }
    
    public Database(File file) throws java.io.IOException {
        
        this(new EQReader(file));
    }
    
    public IDSet columnIds() {
    
        HashIDSet columns = new HashIDSet();
        for (Column column : _columns) {
            columns.add(column.id());
        }
        return columns;
    }
    
    public IdentifiableObjectSet<Column> columns() {
        
        return _columns;
    }
    
    public Column get(int columnId) {
        
        return _columns.get(columnId);
    }
    
    @Override
    public Iterator<Column> iterator() {

        return _columns.iterator();
    }
    
    public int size() {
    
        return _columns.length();
    }
}
