/*
* 참고 사이트
* https://stickode.tistory.com/140
* https://cpcp127.tistory.com/21
*/

package com.example.swt_calendar_practice;

import androidx.annotation.NonNull; // 매개변수, 필드, 메서드 반환값 등에 null이 아님을 표시하기 위해 사용되는 것
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    public TextView titletextView, scheduleTextView; // 제목 text, 일정을 보여주는 text
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
                scheduleTextView.setVisibility(View.INVISIBLE); // 날짜에 저장된 일정은 저장 전까지 보이면 안되므로 INVISIBLE 처리
                                
                // diaryTextView.setText(String.format("%d / %d / %d", year, month, dayOfMonth));
                // 위의 코드는 text로 달력의 날짜를 보이게 할때 사용하면 됨 ㅇㅇ
                editText.setText(""); // text 지정인데 우리는 받아올 것 이므로 비워둔다.
                checkDay(year, month, dayOfMonth);
            }
        });
        // 저장 버튼 onClick 이벤트
        save_Btn.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               // onClick 이벤트에 따른 각 속성별 기능
               // 저장 버튼을 누르면 Day Select 전까지 저장 버튼과 editText가 안 보이게 함
               saveDiary(readSchedule);
               str = editText.getText().toString(); // editText에 입력한 내용을 str에 저장
               scheduleTextView.setText(str); // scheduleTextView에 str을 넣기
               save_Btn.setVisibility(View.INVISIBLE); // 저장 버튼 안 보이기
               editText.setVisibility(View.INVISIBLE); // 일정 입력창 안 보이기
               scheduleTextView.setVisibility(View.VISIBLE);
           }
        });
    }
    // 일정 확인 부분
    public void checkDay(int cYear, int cMonth, int cDay){
        readSchedule = "" + cYear + "-" + (cMonth+1)+""+"-"+cDay+".txt"; // 저장할 파일 이름 설정
        FileInputStream fis; //FileInputSystem

        try { // try catch - 예외구문 처리
            fis = openFileInput(readSchedule);

            byte[] fileData = new byte[fis.available()];
            fis.read(fileData); // fileData 읽기
            fis.close(); // input 닫기

            str = new String(fileData);

            editText.setVisibility(View.INVISIBLE);
            scheduleTextView.setVisibility(View.VISIBLE);
            scheduleTextView.setText(str);

            save_Btn.setVisibility(View.INVISIBLE);

            if(scheduleTextView.getText() == null){
                scheduleTextView.setVisibility(View.INVISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                titletextView.setVisibility(View.VISIBLE);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    // 완벽히 알맞은 코드나 충돌 가능성이 있는 코드를 사용할 때 @SuppressLint를 사용
    @SuppressLint("WrongConstant") 
    public void saveDiary(String readSchedule){
        FileOutputStream fos;
        try{
            fos = openFileOutput(readSchedule, MODE_NO_LOCALIZED_COLLATORS);
            String content = editText.getText().toString();
            fos.write((content).getBytes());
            fos.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}