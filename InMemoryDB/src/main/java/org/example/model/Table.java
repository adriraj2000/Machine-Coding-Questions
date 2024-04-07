package org.example.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Table {
    private String name;
    private List<Record> recordList;
    public Table(String name){
        this.name = name;
        recordList = new ArrayList<>();
    }

    public void addRecords(List<Record> records){
        recordList.addAll(records);
    }
}
