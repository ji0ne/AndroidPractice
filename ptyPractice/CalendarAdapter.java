package kr.ac.uc.calendar;

import android.graphics.Color;
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
    ArrayList<LocalDate> dayList;



    public CalendarAdapter(ArrayList<LocalDate> dayList){
        this.dayList = dayList;

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
        LocalDate day = dayList.get(position);

        if(day == null){
            holder.tvDay.setText("");
        }
        else {
            //해당 일자 넣기                      //day 날짜를 String 변환
            holder.tvDay.setText(String.valueOf(day.getDayOfMonth()));

            //현재 날짜 색상 칠하기
                if(day.equals(CalendarUtil.selectedDate)){
                    holder.parentView.setBackgroundColor(Color.LTGRAY);
                }
        }
            //일요일 빨간색
        if(position % 7 == 0 || position == 0){
            holder.tvDay.setTextColor(Color.RED);

        }   //토요일 ㄹ파랑
        else if ((position+1) % 7 == 0){
            holder.tvDay.setTextColor(Color.BLUE);
        }

        //날짝 클릭했을때
        holder.itemView.setOnClickListener(v -> {

            int iYear = day.getYear();
            int iMonth = day.getMonthValue();
            int iDay = day.getDayOfMonth();

            String yearMonDay = iYear+"년 "+iMonth+"월 "+iDay+"일";
            Toast.makeText(holder.itemView.getContext(), yearMonDay, Toast.LENGTH_SHORT).show();


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




