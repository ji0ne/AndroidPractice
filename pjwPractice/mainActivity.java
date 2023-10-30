package net.hananet.bns2.sharedscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //버튼 변수 선언 : 버튼 객체 나타냄
    Button btn_main_toPersonalWeeklyPlan;
    Button btn_main_toAddSchedule;
    // 프래그먼트 변수 선언 : 프래그먼트 객체 생성
    //Fragment personalWeeklySchedulerMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) { //: OnCreate 메서드 오버라이드, 엑티비티 생성 시 호출되는 초기화 코드 작성
        super.onCreate(savedInstanceState); //상위 클래스의 onCreate 메서드 호출
        setContentView(R.layout.activity_main); //현 엑티비티의 레이아웃을 activity_main으로 설정

        //xml 레이아웃에서 id로 버튼을 찾아 변수에 연결
        btn_main_toPersonalWeeklyPlan = findViewById(R.id.btn_main_toPersonalWeeklyPlan);
        btn_main_toAddSchedule = findViewById(R.id.btn_main_toAddSchedule);


       /* if(personalWeeklySchedulerMain == null)
        {
            //personalWeeklySchedulerMain이 아직 생성되지 않은 경우에만 생성
            personalWeeklySchedulerMain = new PersonalWeeklyScheduler();

            //프래그먼트 매니저를 가져와서 프래그먼트 관리를 시작
            FragmentManager fragmentManager = getSupportFragmentManager();
            //프래그먼트 트랜잭션(프래그먼트 추가,교체,제거 가능) 시작
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //프래그먼트 교체


            fragmentTransaction.replace(R.id.layout_personalWeekly_FragmentContainer,personalWeeklySchedulerMain);
            //프래그먼트 트랜잭션을 커밋 ( 변경사항 적용)
            fragmentTransaction.commit();
        }

        //버튼 클릭 시 동작 함수
        btn_main_toPersonalWeeklyPlan.setOnClickListener(v -> {

            //프래그먼트 트랜잭션(프래그먼트 추가,교체,제거 가능) 시작
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();


            fragmentTransaction.replace(R.id.layout_personalWeekly_FragmentContainer,personalWeeklySchedulerMain);
            //프래그먼트 트랜잭션을 커밋 ( 변경사항 적용)
            fragmentTransaction.commit();

        }); */

        btn_main_toPersonalWeeklyPlan.setOnClickListener(v -> {

            Intent PersonalIntent = new Intent(getApplicationContext() , PersonalSchedule.class);
            //화면 전환 수행을 위한 인텐트 객체 생성 , AddSchedule 엑티비티로 전환
            startActivity(PersonalIntent); //startActivity 메서드로 인텐트 실행

        });


        btn_main_toAddSchedule.setOnClickListener(v -> {
            
            //토스트 메시지 출력
            Toast.makeText(getApplicationContext() , "일정 추가 페이지입니다 .",Toast.LENGTH_SHORT).show();

            //레이아웃 전환
            Intent intent = new Intent(getApplicationContext() , AddSchedule.class);
            //화면 전환 수행을 위한 인텐트 객체 생성 , AddSchedule 엑티비티로 전환
            startActivity(intent); //startActivity 메서드로 인텐트 실행

        });

    }
}