package de.sialos.moodcheck_0_3;


import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerModel {

    private final int MAX_VALUE_OF_RESULT = 2;

    Date date = new Date();
    // SimpleDateFormat ist veraltet und "troublesome", in NÄCHSTER Version etwas neueres benutzen!
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String readableDateTime = sdf.format(date);
    private int timeDate;
    private int result;
    private int resultSet = 0;

    public String getReadableDateTime() {
        return readableDateTime;
    }

    public LoggerModel() {
        this.timeDate = (int) date.getTime();
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
