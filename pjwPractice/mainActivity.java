package net.hananet.bns2.sharedscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_main_toPersonalWeeklyPlan;

    Fragment personalWeeklyScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_main_toPersonalWeeklyPlan = findViewById(R.id.btn_main_toPersonalWeeklyPlan);

        btn_main_toPersonalWeeklyPlan.setOnClickListener(v -> {

            personalWeeklyScheduler = new personalWeeklyScheduler();

            //여기에서 새 레이아웃 호출 > 새 레이아웃이 fragment 호스팅
            
            //personal_weekly_schedule 레이아웃으로 전환
            setContentView(R.layout.personal_weekly_schedule);

            //레이아웃의 frameLayout에 fragment를 호스팅
            
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.layout_personalWeekly_FragmentContainer,personalWeeklyScheduler);
                fragmentTransaction.commit();
            



        });



    }
}