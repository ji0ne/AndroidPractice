package kr.ac.uc.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;

public class addSchedule extends AppCompatActivity {
    Button btnCancel, btnSave;
    EditText etSchedule;
    String readDay = null;
    String str = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        etSchedule = findViewById(R.id.etSchedule);

        btnCancel.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        btnSave.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            //MainActivity mainActivity =

            saveSchedule(readDay);
            str = etSchedule.getText().toString();
            //tvText.setText(str);
        });
    }

    @SuppressLint("WrongConstant")
    public void saveSchedule(String readDay) {
        FileOutputStream fos;
        try {
            fos = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS);
            String content = etSchedule.getText().toString();
            fos.write((content).getBytes());
            fos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}