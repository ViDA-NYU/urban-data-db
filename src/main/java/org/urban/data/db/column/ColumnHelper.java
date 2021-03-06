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
package org.urban.data.db.column;

import java.io.File;

/**
 * COllection of helper methods for database column and column files.
 * 
 * @author Heiko Mueller <heiko.mueller@nyu.edu>
 */
public final class ColumnHelper {
    
    /**
     * Get the column identifier from the column name. It is expected that the
     * column identifier is a number at the start of the file name.
     * 
     * @param file
     * @return 
     */
    public static int getColumnId(File file) {
        
        String[] tokens = file.getName().split("\\.");
        try {
            return Integer.parseInt(tokens[0]);
        } catch (java.lang.NumberFormatException ex) {
        }
        return Integer.parseInt(tokens[2]);
    }
    
}
