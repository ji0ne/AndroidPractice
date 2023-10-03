package kr.ac.uc.calendar;

<<<<<<< HEAD
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

=======
>>>>>>> e1926720de9ef39675ce072537172d0c9eb8b1ad
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< HEAD
import com.jakewharton.threetenabp.AndroidThreeTen;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnItemListener{

    TextView tvMonth;

    RecyclerView rvCalendar,rvTodo;

    Button btnSave;

=======
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
>>>>>>> e1926720de9ef39675ce072537172d0c9eb8b1ad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        AndroidThreeTen.init(this);
=======
>>>>>>> e1926720de9ef39675ce072537172d0c9eb8b1ad

        tvMonth = findViewById(R.id.tvMonth);
        ImageButton btnPre = findViewById(R.id.btnPre);
        ImageButton btnNext = findViewById(R.id.btnNext);
<<<<<<< HEAD
        rvCalendar = findViewById(R.id.rvCalendar);
        rvTodo = findViewById(R.id.rvTodo);
        btnSave = findViewById(R.id.btnSave);

        CalendarUtil.selectedDate = LocalDate.now();

        setMonthView();

        btnSave.setOnClickListener(v -> {
            //noteSave();     //데이터베이스 만들고 추가할 메소드.

            Toast.makeText(getApplicationContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();
        });

        btnPre.setOnClickListener(v -> {
            CalendarUtil.selectedDate = CalendarUtil.selectedDate.minusMonths(1);
            setMonthView();
        });
        btnNext.setOnClickListener(v -> {
            CalendarUtil.selectedDate = CalendarUtil.selectedDate.plusMonths(1);
=======
        recyclerView = findViewById(R.id.recyclerView);

        selectedDate = LocalDate.now();

        setMonthView();

        btnPre.setOnClickListener(v -> {
            selectedDate = selectedDate.minusMonths(1);
            setMonthView();
        });
        btnNext.setOnClickListener(v -> {
            selectedDate = selectedDate.plusMonths(1);
>>>>>>> e1926720de9ef39675ce072537172d0c9eb8b1ad
            setMonthView();
        });




    }

<<<<<<< HEAD

=======
    @RequiresApi(api = Build.VERSION_CODES.O)
>>>>>>> e1926720de9ef39675ce072537172d0c9eb8b1ad
    private String monthFromDate(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월");
        return date.format(dtf);
    }


<<<<<<< HEAD


    private  void setMonthView(){
        tvMonth.setText(monthFromDate(CalendarUtil.selectedDate));

        ArrayList<LocalDate> dayList = dayInMonthArray(CalendarUtil.selectedDate);
=======
    @RequiresApi(api = Build.VERSION_CODES.O)
    private  void setMonthView(){
        tvMonth.setText(monthFromDate(selectedDate));

        ArrayList<String> dayList = dayInMonthArray(selectedDate);
>>>>>>> e1926720de9ef39675ce072537172d0c9eb8b1ad

        CalendarAdapter adapter = new CalendarAdapter(dayList);

        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(),7);

<<<<<<< HEAD
        rvCalendar.setLayoutManager(manager);

        rvCalendar.setAdapter(adapter);

    }

    private void setTodoList(){
        NoteAdapter adapter = new NoteAdapter();
    }


    private ArrayList<LocalDate> dayInMonthArray(LocalDate date){
        ArrayList<LocalDate> dayList = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(date);

        //해당 월의 마지막 날짜 가져오기
        int lastDay = yearMonth.lengthOfMonth();

        //해당 월의 첫 번째 날 가져오기
        LocalDate firstDay = CalendarUtil.selectedDate.withDayOfMonth(1);

        //firstDay 요일 가져오기 (1~7)
=======
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> dayInMonthArray(LocalDate date){
        ArrayList<String>  dayList = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(date);

        int lastDay = yearMonth.lengthOfMonth();

        LocalDate firstDay = selectedDate.withDayOfMonth(1);

>>>>>>> e1926720de9ef39675ce072537172d0c9eb8b1ad
        int dayOfWeek = firstDay.getDayOfWeek().getValue();

        for (int i = 1; i <42; i++){
            if(i <= dayOfWeek || i >lastDay + dayOfWeek){
<<<<<<< HEAD
                dayList.add(null);
            }
            else{
                dayList.add(LocalDate.of(CalendarUtil.selectedDate.getYear(), CalendarUtil.selectedDate.getMonth(),i-dayOfWeek));
=======
                dayList.add("");
            }
            else{
                dayList.add(String.valueOf(i - dayOfWeek));
>>>>>>> e1926720de9ef39675ce072537172d0c9eb8b1ad
            }
        }
        return  dayList;
    }
<<<<<<< HEAD



    @Override
    public void onItemClick(String dayText) {
        String yearMonDay = monthFromDate(CalendarUtil.selectedDate)+ " " + dayText + "일";
        Toast.makeText(this,yearMonDay,Toast.LENGTH_SHORT).show();
    }
}
=======
}
>>>>>>> e1926720de9ef39675ce072537172d0c9eb8b1ad
