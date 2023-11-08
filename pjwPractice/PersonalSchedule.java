package net.hananet.bns2.sharedscheduler;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PersonalSchedule extends AppCompatActivity {

    TextView textViewStartTime,textViewEndTime,textViewScheduleName,textViewScheduleMemo,textViewSelectedDays;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_schedule);

        textViewStartTime = findViewById(R.id.textViewStartTime);
        textViewEndTime = findViewById(R.id.textViewEndTime);
        textViewScheduleName = findViewById(R.id.textViewScheduleName);
        textViewScheduleMemo = findViewById(R.id.textViewScheduleMemo);
        textViewSelectedDays = findViewById(R.id.textViewSelectedDays);

        // 저장된 정보 가져오기
        List<String> scheduleData = getIntent().getStringArrayListExtra("scheduleData");
        List<String> selectedDays = getIntent().getStringArrayListExtra("selectedDays");


        if (scheduleData != null) {
            // 정보 표시
            textViewStartTime.setText("Start Time: " + scheduleData.get(0));
            textViewEndTime.setText("End Time: " + scheduleData.get(1));
            textViewScheduleName.setText("Schedule Name: " + scheduleData.get(2));
            textViewScheduleMemo.setText("Schedule Memo: " + scheduleData.get(3));
            textViewSelectedDays.setText("Selected Days: " + selectedDays);
        }
    }




}
