package kr.ac.uc.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    TextView tvDay, tvTitle, tvStartTime, tvEndTime, tvSchedule;
    Button btnChange, btnDel, btnNext, btnCheck;
    String readDay = null;
    String str = null;
    String title;
    String startTime;
    String endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDay = findViewById(R.id.tvDay);
        calendarView = findViewById(R.id.calendarView);
        tvTitle = findViewById(R.id.tvTitle);
        tvStartTime = findViewById(R.id.tvStartTime);
        tvEndTime = findViewById(R.id.tvEndTime);
        tvSchedule = findViewById(R.id.tvSchedule);
        btnChange = findViewById(R.id.btnChange);
        btnDel = findViewById(R.id.btnDel);
        btnNext = findViewById(R.id.btnNext);
        btnCheck = findViewById(R.id.btnCheck);

        //오늘 날짜 표시
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        tvDay.setText(sdf.format(date));

        tvTitle.setText("");
        tvStartTime.setText("");
        tvEndTime.setText("");
        tvSchedule.setText("");
        btnChange.setVisibility(View.INVISIBLE);
        btnDel.setVisibility(View.INVISIBLE);
        btnNext.setVisibility(View.INVISIBLE);
        btnCheck.setVisibility(View.INVISIBLE);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            tvDay.setText(String.format("%d-%02d-%02d", year, (month + 1), dayOfMonth)); //클릭한 날짜 표시
            tvTitle.setText("");
            tvStartTime.setText("");
            tvEndTime.setText("");
            tvSchedule.setText("");
            btnChange.setVisibility(View.VISIBLE);
            btnDel.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            btnCheck.setVisibility(View.VISIBLE);

            checkDay(year, month, dayOfMonth);
        });

        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(this, addSchedule.class);
            startActivity(intent);
        });

        btnCheck.setOnClickListener(view -> {
            List<String> scheduleData = getIntent().getStringArrayListExtra("scheduleData");

            if (scheduleData != null) {
                tvTitle.setText("Schedule Title: " + scheduleData.get(0));
                tvStartTime.setText("Start Time: " + scheduleData.get(1));
                tvEndTime.setText("End Time: " + scheduleData.get(2));
                tvSchedule.setText("Schedule: " + scheduleData.get(3));
            }
        });

        /*Intent intent = getIntent();
        title = intent.getStringExtra("title");
        startTime = intent.getStringExtra("startTime");
        endTime = intent.getStringExtra("endTime");*/
    }

    public void checkDay(int cYear, int cMonty, int cDay) {
        readDay = "" + cYear + "-" + (cMonty + 1) + "-" + cDay + ".txt";
        FileInputStream fis;

        try {
            fis = openFileInput(readDay);

            byte[] fileData = new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            str = new String(fileData);

            //tvText.setText(str);

            btnChange.setOnClickListener(v -> {
                //etSchedule.setText(str);
                //tvText.setText(etSchedule.getText());
            });

            btnDel.setOnClickListener(v -> {
                //tvText.setVisibility(View.INVISIBLE);
                //etSchedule.setText("");
                removeDiary(readDay);
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    public void removeDiary(String readDay) {
        FileOutputStream fos;
        try {
            fos = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS);
            String content = "";
            fos.write((content).getBytes());
            fos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
}