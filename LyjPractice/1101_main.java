package kr.ac.uc.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialCalendar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> list = new ArrayList<String>();
    CalendarView calendarView;
    TextView tvDay, tvText;
    EditText etSchedule;
    Button btnSave, btnChange, btnDel, btnNext;
    String readDay = null;
    String str = null;
    String title;
    String startTime;
    String endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        tvDay = findViewById(R.id.tvDay);
        calendarView = findViewById(R.id.calendarView);
        tvText = findViewById(R.id.tvText);
        etSchedule = findViewById(R.id.etSchedule);
        btnSave = findViewById(R.id.btnSave);
        btnChange = findViewById(R.id.btnChange);
        btnDel = findViewById(R.id.btnDel);
        btnNext = findViewById(R.id.btnNext);

        //오늘 날짜 표시
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        tvDay.setText(sdf.format(date));

        tvText.setText("");
        etSchedule.setVisibility(View.INVISIBLE);
        btnSave.setVisibility(View.INVISIBLE);
        btnChange.setVisibility(View.INVISIBLE);
        btnDel.setVisibility(View.INVISIBLE);
        btnNext.setVisibility(View.INVISIBLE);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            tvDay.setText(String.format("%d-%02d-%02d", year, (month + 1), dayOfMonth)); //클릭한 날짜 표시
            tvText.setText("");
            etSchedule.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.VISIBLE);
            btnChange.setVisibility(View.VISIBLE);
            btnDel.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            checkDay(year, month, dayOfMonth);
        });

        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(this, addSchedule.class);
            startActivity(intent);
        });

        /*btnSave.setOnClickListener(v -> {
            saveSchedule(readDay);
            str = etSchedule.getText().toString();
            tvText.setText(str);
            //tvText.setText(etSchedule.getText());
            etSchedule.setText("");
        });*/

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        startTime = intent.getStringExtra("startTime");
        endTime = intent.getStringExtra("endTime");
        list.add(title);
        list.add(startTime);
        list.add(endTime);
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

            tvText.setText(str);

            btnChange.setOnClickListener(v -> {
                etSchedule.setText(str);
                tvText.setText(etSchedule.getText());
            });

            btnDel.setOnClickListener(v -> {
                tvText.setVisibility(View.INVISIBLE);
                etSchedule.setText("");
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