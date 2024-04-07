package org.example;


import org.example.model.Column;
import org.example.model.ColumnType;
import org.example.model.Record;
import org.example.service.DatabaseService;
import org.example.service.TableService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        TableService tableService = new TableService();
        DatabaseService databaseService = DatabaseService.getInstance(tableService);
        databaseService.createTable("t1");

        Record r1 = new Record(List.of(new Column("name", ColumnType.STRING),
                new Column("age", ColumnType.INT)), List.of("Alice",23));
        Record r2 = new Record(List.of(new Column("name", ColumnType.STRING),
                new Column("age", ColumnType.INT)), List.of("John",21));
        Record r3 = new Record(List.of(new Column("name", ColumnType.STRING),
                new Column("age", ColumnType.INT)), List.of("Sam",20));
        databaseService.insertRecords("t1",List.of(r1,r2,r3));
        System.out.println(databaseService.printRecords("t1"));
        Map<String,Object> searchFilter = new HashMap<>();
        searchFilter.put("name","John");
        System.out.println(databaseService.filterRecords("t1",searchFilter));
        databaseService.dropTable("t1");
        System.out.println(databaseService.filterRecords("t1",searchFilter));
        System.out.println(databaseService.printRecords("t1"));
    }
}