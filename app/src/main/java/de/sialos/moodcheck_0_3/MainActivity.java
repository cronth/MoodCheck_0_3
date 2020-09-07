package de.sialos.moodcheck_0_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LoggerModel loggerModel;

    Button btn_1; // "schlecht"
    Button btn_2; // "gut"
    Button btn_3; // "send"

    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loggerModel = new LoggerModel();
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "1 gedrückt", Toast.LENGTH_SHORT).show();
                result = 1;
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "2 gedrückt", Toast.LENGTH_SHORT).show();
                result = 2;
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result != 0) {
                    // Schreibe neue Zeile in DB
                    loggerModel.updateResultSet(result);
                    DataBaseHelper db = new DataBaseHelper(MainActivity.this);
                    boolean success = db.addRow(loggerModel);

                    // Hole letzte Reihe aus DB, um zu zeigen, dass alles klappt
                    LoggerModel lastLoggerModel = new LoggerModel();
                    lastLoggerModel = db.getLastEntry();


                    Toast.makeText(MainActivity.this,
                            "ResultSet is " + loggerModel.getResultSet()
                                    + "\nTime is " + loggerModel.getReadableDateTime()
                                    + "\nWrite to DB is success: " + success
                                    + "\nid of last entry:" + lastLoggerModel.getId()
                                    + "\ntime: " + lastLoggerModel.getReadableDateTime()
                                    + "\nresultSet: " + lastLoggerModel.getResultSet()
                            , Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.str_choose_answer_first), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}