package kr.ac.uc.calendar;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.threetenabp.AndroidThreeTen;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;


import java.io.FileNotFoundException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnItemListener{

    TextView tvMonth;

    RecyclerView rvCalendar,rvTodo;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidThreeTen.init(this);

        tvMonth = findViewById(R.id.tvMonth);
        ImageButton btnPre = findViewById(R.id.btnPre);
        ImageButton btnNext = findViewById(R.id.btnNext);
        rvCalendar = findViewById(R.id.rvCalendar);
        rvTodo = findViewById(R.id.rvTodo);
        btnSave = findViewById(R.id.btnSave);

        CalendarUtil.selectedDate = LocalDate.now();

        setMonthView();
        setTodoList();

        btnSave.setOnClickListener(v -> {
            //noteSave();     //데이터베이스 만들고 추가할 메소드.
            //todo.xml 에 etInput 텍스트 가져와서 저장.
            Toast.makeText(getApplicationContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();
        });

        btnPre.setOnClickListener(v -> {
            CalendarUtil.selectedDate = CalendarUtil.selectedDate.minusMonths(1);
            setMonthView();
        });
        btnNext.setOnClickListener(v -> {
            CalendarUtil.selectedDate = CalendarUtil.selectedDate.plusMonths(1);
            setMonthView();
        });




    }


    private String monthFromDate(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월");
        return date.format(dtf);
    }




    private  void setMonthView(){
        tvMonth.setText(monthFromDate(CalendarUtil.selectedDate));

        ArrayList<LocalDate> dayList = dayInMonthArray(CalendarUtil.selectedDate);

        CalendarAdapter adapter = new CalendarAdapter(dayList);

        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(),7);

        rvCalendar.setLayoutManager(manager);

        rvCalendar.setAdapter(adapter);

    }

    private void setTodoList(){

        NoteAdapter adapter = new NoteAdapter();
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(),1);
        rvTodo.setLayoutManager(manager);
        rvTodo.setAdapter(adapter);
    }


    private ArrayList<LocalDate> dayInMonthArray(LocalDate date){
        ArrayList<LocalDate> dayList = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(date);

        //해당 월의 마지막 날짜 가져오기
        int lastDay = yearMonth.lengthOfMonth();

        //해당 월의 첫 번째 날 가져오기
        LocalDate firstDay = CalendarUtil.selectedDate.withDayOfMonth(1);

        //firstDay 요일 가져오기 (1~7)
        int dayOfWeek = firstDay.getDayOfWeek().getValue();

        for (int i = 1; i <42; i++){
            if(i <= dayOfWeek || i >lastDay + dayOfWeek){
                dayList.add(null);
            }
            else{
                dayList.add(LocalDate.of(CalendarUtil.selectedDate.getYear(), CalendarUtil.selectedDate.getMonth(),i-dayOfWeek));
            }
        }
        return  dayList;
    }



    @Override
    public void onItemClick(String dayText) {
        String yearMonDay = monthFromDate(CalendarUtil.selectedDate)+ " " + dayText + "일";
        Toast.makeText(this,yearMonDay,Toast.LENGTH_SHORT).show();
    }
}