package org.example.service;

import org.example.model.Column;
import org.example.model.Record;
import org.example.model.Table;
import org.example.validator.ValidatorFactory;

import java.util.*;
import java.util.stream.Collectors;

public class TableService {
    private Map<String, Table> tableMap;
    private ValidatorFactory validatorFactory;
    private Map<String, List<Object>> columnValues;
    public TableService(){
        validatorFactory = ValidatorFactory.getInstance();
        tableMap = new HashMap<>();
        columnValues = new HashMap<>();
    }

    public void createTable(String tableName){
        Table table = new Table(tableName);
        tableMap.put(tableName, table);
    }

    public void deleteTable(String tableName){
        Table table = tableMap.get(tableName);
        if(table == null){
            System.out.println("Table does not exists");
            return;
        }
        for (Record record : table.getRecordList()){
            removeColumnMapping(record);
        }
        tableMap.remove(tableName);
    }

    public void insertRecords(String tableName, List<Record> records){
        for(Record record : records){
            if(!validatorFactory.validate(record.getColumnValues().values().stream().toList())){
                System.out.println("Data exceeding limit");
                return;
            }
        }
        for (Record record : records){
            createColumnMapping(record);
        }
        Table table = tableMap.get(tableName);
        table.addRecords(records);
    }

    public List<Record> filterRecords(String tableName, Map<String, Object> searchFilter){
        Table table = tableMap.get(tableName);
        if(table == null){
            System.out.println("Table does not exists cannot filter");
            return new ArrayList<>();
        }
        List<Record> records = table.getRecordList();
        return records.stream().filter(record -> matchesFilter(searchFilter)).collect(Collectors.toList());
    }

    private void createColumnMapping(Record record){
        for(Column column : record.getColumnValues().keySet()){
            columnValues.putIfAbsent(column.getName(), new ArrayList<>());
            columnValues.get(column.getName()).add(record.getColumnValues().get(column));
        }
    }

    private void removeColumnMapping(Record record){
        for(Column column : record.getColumnValues().keySet()){
            if(columnValues.containsKey(column.getName()))
            {
                columnValues.remove(column.getName());
            }
        }
    }

    private boolean matchesFilter(Map<String, Object> searchFilter){
        for (Map.Entry<String, Object> entry : searchFilter.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (columnValues.containsKey(key)) {
                List<Object> rowValues = columnValues.get(key);
                if (!rowValues.contains(value)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public List<Record> printRecords(String tableName){
        Table table = tableMap.get(tableName);
        if(table == null){
            System.out.println("Table does not exist");
            return new ArrayList<>();
        }
        return table.getRecordList();
    }
}
