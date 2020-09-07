package de.sialos.moodcheck_0_3;


import java.util.Date;

public class LoggerModel {

    private int timeDate;
    private int resultSet;
    Date date = new Date();


    public LoggerModel(int resultSet) {
        this.resultSet = resultSet;
        this.timeDate = (int) date.getTime();

    }

    public int getTimeDate() {
        return timeDate;
    }

    public int getResultSet() {
        return resultSet;
    }

}
