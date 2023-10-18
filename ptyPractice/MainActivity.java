package kr.ac.uc.calendar;

import android.content.Context;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.threetenabp.AndroidThreeTen;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity implements OnItemListener{

    TextView tvMonth;

    RecyclerView rvCalendar,rvTodo;

    private static final String TAG = "error_save";
    private static final String TAG1 = "error_read";

    Button btnSave;
    EditText etInput;

    String fileName;
    String yearMonDay;

    ImageButton btnPre,btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidThreeTen.init(this);

        tvMonth = findViewById(R.id.tvMonth);
        btnPre = findViewById(R.id.btnPre);
        btnNext = findViewById(R.id.btnNext);
        rvCalendar = findViewById(R.id.rvCalendar);
        rvTodo = findViewById(R.id.rvTodo);
        btnSave = findViewById(R.id.btnSave);

        CalendarUtil.selectedDate = LocalDate.now();
       // yearMonDay = String.valueOf(CalendarUtil.selectedDate);
        fileName = dayMonthFromDate(CalendarUtil.selectedDate)+".txt";




        setMonthView();
        setTodoView();

        btnSave.setOnClickListener(v -> {

            saveFile();
            setTodoView();
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

    private String dayMonthFromDate(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 d일");
        return date.format(dtf);
    }




    private  void setMonthView(){
        tvMonth.setText(monthFromDate(CalendarUtil.selectedDate));

        ArrayList<String> dayList = dayInMonthArray(CalendarUtil.selectedDate);



        CalendarAdapter adapter = new CalendarAdapter(dayList,MainActivity.this);

        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(),7);

        rvCalendar.setLayoutManager(manager);

        rvCalendar.setAdapter(adapter);
    }

    private void setTodoView(){

        TodoAdapter adapter = new TodoAdapter(openFile()); //날

        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(),1);

        rvTodo.setLayoutManager(manager);
        rvTodo.setAdapter(adapter);

    }




    private ArrayList<String> dayInMonthArray(LocalDate date){
        ArrayList<String> dayList = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(date);

        //해당 월의 마지막 날짜 가져오기 ex)31
        int lastDay = yearMonth.lengthOfMonth();

        //해당 월의 첫 번째 날 가져오기
        LocalDate firstDay = CalendarUtil.selectedDate.withDayOfMonth(1);

        //firstDay 요일 가져오기 (1~7)
        int dayOfWeek = firstDay.getDayOfWeek().getValue();

        for (int i = 1; i <42; i++){
            if(i <= dayOfWeek || i >lastDay + dayOfWeek){
                dayList.add("");
            }
            else {
                dayList.add(String.valueOf(i - dayOfWeek));
            }
        }
        int cnt =0;
        for(int i =0;i<7;i++) {
            if (dayList.get(i).equals(""))
                cnt++;
        }
        if(cnt == 7){
            while(cnt>0) {
                dayList.remove(0);
                cnt--;
            }
        }
        return  dayList;
    }

    public ArrayList<String> openFile(){
        ArrayList<String> todoList = new ArrayList<>();

        byte[] txt = new byte[1024];
        try {

            FileInputStream inFs  = openFileInput(fileName);
            inFs.read(txt);
            inFs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        StringTokenizer st = new StringTokenizer(txt.toString(), "\n");
        while (st.hasMoreTokens()) {
            todoList.add(st.nextToken());
        }*/

        return todoList;
    }

    public void saveFile(){
        FileOutputStream outFs;
        try {
            outFs = openFileOutput(fileName,Context.MODE_APPEND);
            String str = etInput.getText().toString();
            outFs.write(str.getBytes());
            outFs.close();
        }
        catch (IOException e){
            Log.d(TAG,"save_error");
            e.printStackTrace();
        }
    }





    //CalendarAdapter > holder.itemView.setOnClickListener 에서 클릭하면 Main > onItemClick 실행.
    //onItemClick 에서 filename 읽기 > setTodoView 메소드 만들고 거기서 adapter >tokenizer 로 스플릿해서 todoCell 에 나눠넣기.
    @Override
    public void onItemClick(String dayText) {
        //monthFromDate(CalendarUtil.selectedDate) = 2023년 xx월
        yearMonDay = monthFromDate(CalendarUtil.selectedDate)+ " " + dayText + "일";
        //yearMonDay = 2023년 xx월 xx일
        fileName = yearMonDay+".txt";
        setTodoView();
        Toast.makeText(this,yearMonDay,Toast.LENGTH_SHORT).show();
    }


}