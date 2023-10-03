package net.hananet.bns2.sharedscheduler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class personalWeeklyScheduler extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_weekly_scheduler,container,false);
        //return inflater.inflate(R.layout.fragment_personal_weekly_scheduler, container, false);
        return view;
    }
}