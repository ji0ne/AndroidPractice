package kr.ac.uc.calendar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TextView tvMonth;
    LocalDate selectedDate;

    RecyclerView recyclerView;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMonth = findViewById(R.id.tvMonth);
        ImageButton btnPre = findViewById(R.id.btnPre);
        ImageButton btnNext = findViewById(R.id.btnNext);
        recyclerView = findViewById(R.id.recyclerView);

        selectedDate = LocalDate.now();

        setMonthView();

        btnPre.setOnClickListener(v -> {
            selectedDate = selectedDate.minusMonths(1);
            setMonthView();
        });
        btnNext.setOnClickListener(v -> {
            selectedDate = selectedDate.plusMonths(1);
            setMonthView();
        });




    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthFromDate(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월");
        return date.format(dtf);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private  void setMonthView(){
        tvMonth.setText(monthFromDate(selectedDate));

        ArrayList<String> dayList = dayInMonthArray(selectedDate);

        CalendarAdapter adapter = new CalendarAdapter(dayList);

        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(),7);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> dayInMonthArray(LocalDate date){
        ArrayList<String>  dayList = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(date);

        int lastDay = yearMonth.lengthOfMonth();

        LocalDate firstDay = selectedDate.withDayOfMonth(1);

        int dayOfWeek = firstDay.getDayOfWeek().getValue();

        for (int i = 1; i <42; i++){
            if(i <= dayOfWeek || i >lastDay + dayOfWeek){
                dayList.add("");
            }
            else{
                dayList.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  dayList;
    }
}
