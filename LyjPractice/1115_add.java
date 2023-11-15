package kr.ac.uc.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class addSchedule extends AppCompatActivity {
    List<String> scheduleData = new ArrayList<>();
    Button btnCancel, btnSave;
    EditText etSchedule, etTitle, etStartTime, etEndTime;
    String readDay = null;
    String str = null;
    String title = "";
    String startTime = "";
    String endTime = "";
    String schedule = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        etSchedule = findViewById(R.id.etSchedule);
        etTitle = findViewById(R.id.etTitle);
        etStartTime = findViewById(R.id.etStartTime);
        etEndTime = findViewById(R.id.etEndTime);

        btnCancel.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        btnSave.setOnClickListener(v -> {
            saveScheduleData();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putStringArrayListExtra("scheduleData", (ArrayList<String>) scheduleData);
            startActivity(intent);

            Toast.makeText(this, "일정이 저장되었습니다.", Toast.LENGTH_SHORT).show();

            /*saveSchedule(readDay);
            str = etSchedule.getText().toString();
            *//*title = etTitle.getText().toString();
            startTime = etStartTime.getText().toString();
            endTime = etEndTime.getText().toString();*//*
            intent.putExtra("schedule", str);
            intent.putExtra("title", title);
            *//*intent.putExtra("startTime", startTime);
            intent.putExtra("endTime", endTime);*/
        });
    }

    /*@SuppressLint("WrongConstant")
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
    }*/

    private void saveScheduleData() {
        // EditText에서 데이터 가져오기
        title = etTitle.getText().toString();
        startTime = etStartTime.getText().toString();
        endTime = etEndTime.getText().toString();
        schedule = etSchedule.getText().toString();

        // 데이터를 리스트에 추가
        scheduleData.add(title);
        scheduleData.add(startTime);
        scheduleData.add(endTime);
        scheduleData.add(schedule);
    }
}