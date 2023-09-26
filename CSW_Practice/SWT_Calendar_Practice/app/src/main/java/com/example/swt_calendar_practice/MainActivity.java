/*
* 참고 사이트
* https://stickode.tistory.com/140
* https://cpcp127.tistory.com/21
*/

package com.example.swt_calendar_practice;

import androidx.annotation.NonNull; // 매개변수, 필드, 메서드 반환값 등에 null이 아님을 표시하기 위해 사용되는 것
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// activity_main.xml에서 추가한 디자인 부분
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

// 저장을 위한 FileInput, Ouput
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    // activity_main.xml에서 추가한 디자인들을 public 선언 후 각 디자인들의 변수를 선언
    public String readSchedule = null;
    public String str = null; // editText에 들어갈 문자열을 null로 선언
    public CalendarView calendarView;
    public Button save_Btn;
    public TextView titletextView, scheduleTextView;
    public EditText editText;
    
    // 초기 설정부
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // activity_main.xml에서 추가한 디자인들을 변수에 각 디자인들의 id를 선언
        titletextView = findViewById(R.id.titletextView);
        calendarView = findViewById(R.id.calendarView);
        editText = findViewById(R.id.editText);
        save_Btn = findViewById(R.id.saveBtn);
        scheduleTextView = findViewById(R.id.scheduletextView);

        // https://5stralia.tistory.com/14
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() { // 날짜 선택 listener
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                // 속성을 보이게 할지 안보이게 할지 정하는 부분, VISIBLE, INVISIBLE
                titletextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                editText.setVisibility(View.VISIBLE);
                
                // diaryTextView.setText(String.format("%d / %d / %d", year, month, dayOfMonth));
                // 위의 코드는 text로 달력의 날짜를 보이게 할때 사용하면 됨 ㅇㅇ
                editText.setText(""); // text 지정인데 우리는 받아올 것 이므로 비워둔다.
            }
        });
        // 저장 버튼 onClick 이벤트
        save_Btn.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               // onClick 이벤트에 따른 각 속성별 기능
               // 저장 버튼을 누르면 Day Select 전까지 저장 버튼과 editText가 안 보이게 함
               str = editText.getText().toString();
               save_Btn.setVisibility(View.INVISIBLE); // 저장 버튼 안 보이기
               editText.setVisibility(View.INVISIBLE); // 일정 입력창 안 보이기
           }
        });
    }
    public void checkDay(int cYear, int cMonth, int cDay){

    }
}