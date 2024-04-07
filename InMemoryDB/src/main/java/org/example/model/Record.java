package org.example.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Record {
    private Map<Column, Object> columnValues = new HashMap<>();
    public Record(List<Column> columns,List<Object> values){
        int totalColumns = columns.size();
        for(int i = 0;i<totalColumns;i++){
            columnValues.put(columns.get(i), values.get(i));
        }
    }
}
