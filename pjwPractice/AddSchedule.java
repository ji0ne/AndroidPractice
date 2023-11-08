package net.hananet.bns2.sharedscheduler;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import net.hananet.bns2.sharedscheduler.R;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddSchedule extends AppCompatActivity {

    List<String> selectedDays = new ArrayList<>();
    List<String> scheduleData = new ArrayList<>();
    String day = "";

    String startTime;
    String endTime;
    String startTimeText = startTime;
    String endTimeText = endTime;
    
    //변수 선언부
    CheckBox cb_fragmentPersonWeekly_monday ,cb_fragmentPersonWeekly_tuesday ,cb_fragmentPersonWeekly_wednesday,cb_fragmentPersonWeekly_thursday,
            cb_fragmentPersonWeekly_friday,cb_fragmentPersonWeekly_saturday,cb_fragmentPersonWeekly_sunday;

    EditText et_frgmentPersonalWeekly_addSchedule_startTime,et_frgmentPersonalWeekly_addSchedule_endTime;

    EditText et_frgmentPersonalWeekly_addSchedule_scheduleName,et_frgmentPersonalWeekly_addSchedule_scheduleMemo;

    Button btn_fragmentPersonalWeekly_addSchedule_saveSchedule,btn_fragmentPersonalWeekly_addSchedule_toBack;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_personal_weekly_add_scheduler);

        setInstance(); //11.08 )인스턴스화하는 코드를 함수로 함

        CompoundButton.OnCheckedChangeListener checkBoxChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // 체크박스 상태가 변경될 때 호출되는 메서드


                // 체크박스에 따라서 요일 문자열(day)을 설정
                if (buttonView.getId() == R.id.cb_fragmentPersonWeekly_monday) {
                    day = "Monday";
                } else if (buttonView.getId() == R.id.cb_fragmentPersonWeekly_tuesday) {
                    day = "Tuesday";
                } else if (buttonView.getId() == R.id.cb_fragmentPersonWeekly_wednesday) {
                    day = "Wednesday";
                } else if (buttonView.getId() == R.id.cb_fragmentPersonWeekly_thursday) {
                    day = "Thursday";
                } else if (buttonView.getId() == R.id.cb_fragmentPersonWeekly_friday) {
                    day = "Friday";
                } else if (buttonView.getId() == R.id.cb_fragmentPersonWeekly_saturday) {
                    day = "Saturday";
                } else if (buttonView.getId() == R.id.cb_fragmentPersonWeekly_sunday) {
                    day = "Sunday";
                }


                // 체크 여부에 따라서 리스트에 추가 또는 제거
                if (isChecked) {
                    selectedDays.add(day);
                    //11.08 ) 토스트메시지로 체크된 요일 확인
                    Toast.makeText(getApplicationContext() , day ,Toast.LENGTH_SHORT).show();
                } else {
                    selectedDays.remove(day);
                }

            }
        };

        btn_fragmentPersonalWeekly_addSchedule_saveSchedule.setOnClickListener(v ->
        {
            saveScheduleData(); //저장하는 함수 호출
            saveTimeData();
            if(!startTimeText.isEmpty() && !endTimeText.isEmpty())
            {
                Intent intent = new Intent(AddSchedule.this, PersonalSchedule.class);
                intent.putStringArrayListExtra("scheduleData", (ArrayList<String>) scheduleData);
                intent.putStringArrayListExtra("selectedDays" , (ArrayList<String>) selectedDays);
                startActivity(intent);
            }


        });

        btn_fragmentPersonalWeekly_addSchedule_toBack.setOnClickListener(v -> {
            //레이아웃 전환 : 뒤로가기
            Intent MainIntent = new Intent(getApplicationContext() , MainActivity.class);
            //화면 전환 수행을 위한 인텐트 객체 생성 , AddSchedule 엑티비티로 전환
            startActivity(MainIntent); //startActivity 메서드로 인텐트 실행
        });


    }

    //시간입력관리

    private void saveTimeData()
    {
        startTime = et_frgmentPersonalWeekly_addSchedule_startTime.getText().toString();
        endTime = et_frgmentPersonalWeekly_addSchedule_endTime.getText().toString();

        if (!startTimeText.isEmpty() && !endTimeText.isEmpty()) {
            int startTime = Integer.parseInt(startTimeText);
            int endTime = Integer.parseInt(endTimeText);

            if (endTime < startTime) {
                Toast.makeText(getApplicationContext(), "종료 시간이 시작 시간보다 이전에 설정될 수 없습니다.", Toast.LENGTH_SHORT).show();
                et_frgmentPersonalWeekly_addSchedule_startTime.getText().clear();
                et_frgmentPersonalWeekly_addSchedule_endTime.getText().clear();
            }

        }


        scheduleData.add("Start Time: " + startTime);
        scheduleData.add("End Time: " + endTime);


    }

    private void saveScheduleData() { //저장버튼 클릭 시 가장 먼저 동작하는 함수
        // EditText에서 데이터 가져오기

        String scheduleName = et_frgmentPersonalWeekly_addSchedule_scheduleName.getText().toString();
        String scheduleMemo = et_frgmentPersonalWeekly_addSchedule_scheduleMemo.getText().toString();

        // 데이터를 리스트에 추가

        scheduleData.add("Schedule Name: " + scheduleName);
        scheduleData.add("Schedule Memo: " + scheduleMemo);
        scheduleData.add("Selected Days: " + selectedDays.toString());
        
        clearInputFields();
    }

    public void clearInputFields() { // 입력 필드 리셋

        et_frgmentPersonalWeekly_addSchedule_scheduleName.getText().clear();
        et_frgmentPersonalWeekly_addSchedule_scheduleMemo.getText().clear();
        selectedDays.clear();

    }

    //모든 부분 입력 전에는 저장 버튼 비활성화
    //앞 일정보다 뒷 일정 시간이 더 작으면 토스트 메시지 뜨면서 입력 내용 리셋
    //일정 저장 > DB에 일정 저장

    public void setInstance()
    {
        cb_fragmentPersonWeekly_monday = findViewById(R.id.cb_fragmentPersonWeekly_monday);
        cb_fragmentPersonWeekly_tuesday = findViewById(R.id.cb_fragmentPersonWeekly_tuesday);
        cb_fragmentPersonWeekly_wednesday = findViewById(R.id.cb_fragmentPersonWeekly_wednesday);
        cb_fragmentPersonWeekly_thursday = findViewById(R.id.cb_fragmentPersonWeekly_thursday);
        cb_fragmentPersonWeekly_friday = findViewById(R.id.cb_fragmentPersonWeekly_friday);
        cb_fragmentPersonWeekly_saturday = findViewById(R.id.cb_fragmentPersonWeekly_saturday);
        cb_fragmentPersonWeekly_sunday = findViewById(R.id.cb_fragmentPersonWeekly_sunday);

        et_frgmentPersonalWeekly_addSchedule_startTime = findViewById(R.id.et_frgmentPersonalWeekly_addSchedule_startTime);
        et_frgmentPersonalWeekly_addSchedule_endTime = findViewById(R.id.et_frgmentPersonalWeekly_addSchedule_endTime);
        et_frgmentPersonalWeekly_addSchedule_scheduleName = findViewById( R.id.et_frgmentPersonalWeekly_addSchedule_scheduleName);
        et_frgmentPersonalWeekly_addSchedule_scheduleMemo = findViewById(R.id.et_frgmentPersonalWeekly_addSchedule_scheduleMemo);

        btn_fragmentPersonalWeekly_addSchedule_saveSchedule = findViewById(R.id.btn_fragmentPersonalWeekly_addSchedule_saveSchedule);
        btn_fragmentPersonalWeekly_addSchedule_toBack = findViewById(R.id.btn_fragmentPersonalWeekly_addSchedule_toBack);
    }




}
