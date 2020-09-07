package de.sialos.moodcheck_0_3;


import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerModel {

    private final int MAX_VALUE_OF_RESULT = 2;
    private Date date;
    private int id;
    private int timeDate;
    private int resultSet = 0;


    // Constructors
    public LoggerModel() {
        this.id = -1;
        this.date = new Date();
        this.timeDate = (int) date.getTime();
    }

    public LoggerModel(int id, int timeDate, int resultSet) {
        this.id = id;
        this.timeDate = timeDate;
        this.resultSet = resultSet;
    }

    public int getId() {
        return id;
    }

    public int getTimeDate() {
        return timeDate;
    }

    public int getResultSet() {
        return resultSet;
    }

    public void updateResultSet(int result) {
        if (result > 0 && result <= MAX_VALUE_OF_RESULT) {
            if (resultSet == 0) {
                resultSet = result;
            } else {
                resultSet = resultSet * 10 + result;
            }
        } else {
            // TODO Fehlerausgabe
        }
    }

}
