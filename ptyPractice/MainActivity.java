package kr.ac.uc.calendar;

import android.content.Context;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    TextView tvTodoDay;
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
        etInput = findViewById(R.id.etInput);
        tvTodoDay = findViewById(R.id.tvTodoDay);

        CalendarUtil.selectedDate = LocalDate.now();
       // yearMonDay = String.valueOf(CalendarUtil.selectedDate);
        fileName = dayMonthFromDate(CalendarUtil.selectedDate)+".txt";



        tvTodoDay.setText(dayMonthFromDate(CalendarUtil.selectedDate)+" 일정");
        setMonthView();
        setTodoView();


        btnSave.setOnClickListener(v -> {

            saveFile();
            setTodoView();
        });

        //달을 바꿀때 마다
        btnPre.setOnClickListener(v -> {
            CalendarUtil.selectedDate = CalendarUtil.selectedDate.minusMonths(1);
            setMonthView();
            setTodoView();
        });
        btnNext.setOnClickListener(v -> {
            CalendarUtil.selectedDate = CalendarUtil.selectedDate.plusMonths(1);
            setMonthView();
            setTodoView();
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

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);

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

    private ArrayList<String> openFile(){
        ArrayList<String> todoList = new ArrayList<>();


        try {

            FileInputStream inFs  = this.openFileInput(fileName);
            byte[] txt = new byte[inFs.available()];
            inFs.read(txt);
            inFs.close();
            String str = new String(txt);
            StringTokenizer st = new StringTokenizer(str, "\n");
            while (st.hasMoreTokens()) {
                todoList.add(st.nextToken());
            }
        } catch (FileNotFoundException e) {
            //파일 없을때 todo로 recycler view 활용 말고 다른 표현방법 만들기
            todoList.add("일정이 없습니다.");
        } catch (IOException e){
            e.printStackTrace();
        }


        return todoList;
    }

    private void saveFile(){
        //time picker 로 시간 설정 > string 으로 바꿔서 saveFile()에 던져주고 txt에 같이 저장 > 시간순정렬이 안되네
        // ArrayList string 으로 하나 더 만들어서 시간만 넣기 파일열때 int 바꿔서 정렬 , 그럼 todolist는 어케 정렬하지
        // sqlite 쓰는게 맞다
        try {
            FileOutputStream outFs = this.openFileOutput(fileName,Context.MODE_APPEND);
            String str = etInput.getText().toString()+"\n";
            outFs.write(str.getBytes());
            outFs.close();
        }catch (FileNotFoundException e){

        }
        catch (IOException e){
            e.printStackTrace();
        }

        etInput.setText("");

    }





    //CalendarAdapter > holder.itemView.setOnClickListener 에서 클릭하면 Main > onItemClick 실행.
    //onItemClick 에서 클릭한 날짜 가져와서 filename 저장  >
    // setTodoView  실행
    @Override
    public void onItemClick(String dayText) {
        //monthFromDate(CalendarUtil.selectedDate) = 2023년 xx월
        yearMonDay = monthFromDate(CalendarUtil.selectedDate)+ " " + dayText + "일";
        //yearMonDay = 2023년 xx월 xx일
        fileName = yearMonDay+".txt";
        tvTodoDay.setText(yearMonDay+" 일정");
        setTodoView();
    }


}