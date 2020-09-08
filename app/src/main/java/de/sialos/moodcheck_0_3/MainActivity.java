package de.sialos.moodcheck_0_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LoggerModel loggerModel;

    private TextView txtFrage;
    private Button btn_1; // "schlecht"
    private Button btn_2; // "gut"
    private Button btnSend; // "send"

    private String[] questions;
    private int questionCounter;
    private int questionCountTotal;
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFrage = findViewById(R.id.txt_frage);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btnSend = findViewById(R.id.btn_3);

        loggerModel = new LoggerModel();
        questions = Questions.questions;
        questionCountTotal = questions.length;
        showNextQuestion();

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

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result != 0) {
                    loggerModel.updateResultSet(result);
                    if (questionCounter == questionCountTotal) {
                        sendResultSetAndClose();

                    } else if (questionCounter + 1 == questionCountTotal) {
                        btnSend.setText("send");
                        showNextQuestion();

                    } else {
                        showNextQuestion();
                    }
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.str_choose_answer_first), Toast.LENGTH_SHORT).show();
                } // schreibe "Bitte Antwort auswählen"
            }
        });
    }

    private void showNextQuestion() {
        txtFrage.setText(questions[questionCounter]);
        questionCounter++;
        result = 0;
    }

    private void sendResultSetAndClose() {
        // Schreibe neue Zeile in DB
        DataBaseHelper db = new DataBaseHelper(MainActivity.this);
        boolean success = db.addRow(loggerModel);
        // Gib Erfolgsmeldung aus ...
        txtFrage.setText(String.format("success: %s \nDanke, das war's schon", success));
        // ... warte kurz ...
        try
        {
            Thread.sleep(4000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        // ... schließe den Antwortzyklus
        finish();
    }
}