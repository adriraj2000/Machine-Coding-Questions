package org.example.service;

import org.example.model.Record;

import java.util.List;
import java.util.Map;

public class DatabaseService {
    private static DatabaseService instance;
    private TableService tableService;
    private DatabaseService(TableService tableService){
        this.tableService = tableService;
    }

    public void createTable(String tableName){
        tableService.createTable(tableName);
    }

    public void dropTable(String tableName){
        tableService.deleteTable(tableName);
    }

    public void insertRecords(String tableName, List<Record> records){
        tableService.insertRecords(tableName, records);
    }

    public List<Record> printRecords(String tableName){
        return tableService.printRecords(tableName);
    }

    public List<Record> filterRecords(String tableName, Map<String, Object> searchFilter){
        return tableService.filterRecords(tableName, searchFilter);
    }

    public static synchronized DatabaseService getInstance(TableService tableService) {
        if(instance == null){
            instance = new DatabaseService(tableService);
        }
        return instance;
    }
}
