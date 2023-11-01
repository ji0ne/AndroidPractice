package kr.ac.uc.calendar;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface OnItemListener {
    void onItemClick(String dayText);
    void onDeleteCilck(int position);
    void onCheckedChange(int position , boolean isChecked);
}
