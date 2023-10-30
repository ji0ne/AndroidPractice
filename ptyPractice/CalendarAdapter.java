package kr.ac.uc.calendar;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>{
    ArrayList<String> dayList;
    OnItemListener onItemListener;


    public CalendarAdapter(ArrayList<String> dayList , OnItemListener onItemListener){
        this.dayList = dayList;
        this.onItemListener = onItemListener;

    }


    @NonNull
    @Override
    public CalendarAdapter.CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.CalendarViewHolder holder, int position) {
        //날짜 변수에 담기
        String day = dayList.get(position);
        int day1 =CalendarUtil.selectedDate.getDayOfMonth();
        holder.tvDay.setText(day);

            //현재 날짜 색상 칠하기
        // 다른 달에는 표시가 안되게 수정
        if(day.equals(String.valueOf(day1))){
            holder.parentView.setBackgroundResource(R.drawable.layout_background2);
        }
        //토요일 ㄹ파랑
        if( (position+1) % 7 == 0){
            holder.tvDay.setTextColor(Color.BLUE);
           
        }   
        //일요일 빨강색
        else if (position % 7 == 0 || position == 0){
            holder.tvDay.setTextColor(Color.RED);
        }

        //날짝 클릭했을때
        //
        holder.itemView.setOnClickListener(v -> {

           /* int iYear = day.getYear();
            int iMonth = day.getMonthValue();
            int iDay = day.getDayOfMonth();
            holder.itemView.setBackgroundColor(Color.GREEN);
            String yearMonDay = iYear+"년 "+iMonth+"월 "+iDay+"일";
            Toast.makeText(holder.itemView.getContext(), yearMonDay, Toast.LENGTH_SHORT).show(); */
            holder.parentView.setBackgroundResource(R.drawable.layout_background2);
            //클릭시 todo 배경 레이아웃 바꾸기
            //다른 날 클릭하면 전에 클릭했던 날짜 배경레이아웃 원래대로 돌리게 만들기

            if(Integer.parseInt(day)<dayList.size())
                onItemListener.onItemClick(day);

        });


    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    class CalendarViewHolder extends  RecyclerView.ViewHolder{
        TextView tvDay;
        View parentView;
        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);

            parentView = itemView.findViewById(R.id.parentView);
            tvDay = itemView.findViewById(R.id.tvDay);
        }
    }
}




