package net.hananet.bns2.sharedscheduler;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddSchedule extends AppCompatActivity {
    
    //변수 선언부
    CheckBox cb_fragmentPersonWeekly_monday ,cb_fragmentPersonWeekly_tuesday ,cb_fragmentPersonWeekly_wednesday,cb_fragmentPersonWeekly_thursday,
            cb_fragmentPersonWeekly_friday,cb_fragmentPersonWeekly_saturday,cb_fragmentPersonWeekly_sunday;

    EditText et_frgmentPersonalWeekly_addSchedule_startTime,et_frgmentPersonalWeekly_addSchedule_endTime;

    EditText et_frgmentPersonalWeekly_addSchedule_scheduleName,et_frgmentPersonalWeekly_addSchedule_scheduleMemo;

    Button btn_fragmentPersonalWeekly_addSchedule_saveSchedule;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_personal_weekly_add_scheduler);

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

        CompoundButton.OnCheckedChangeListener checkBoxChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        };


    }

    //모든 부분 입력 전에는 저장 버튼 비활성화
    //앞 일정보다 뒷 일정 시간이 더 작으면 토스트 메시지 뜨면서 입력 내용 리셋
    //일정 저장 > DB에 일정 저장



}
