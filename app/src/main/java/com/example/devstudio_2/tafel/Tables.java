package com.example.devstudio_2.tafel;

public class Tables {

    String tableId;
    String tablename;
    String isReserved;
    String reserveDate;
    String reserveTime;

    public Tables() {

    }

    public Tables(String tableId, String tablename, String isReserved,
                  String reserveDate, String reserveTime) {
        this.tableId = tableId;
        this.tablename = tablename;
        this.isReserved = isReserved;
        this.reserveDate = reserveDate;
        this.reserveTime = reserveTime;
    }

}
