package kr.ac.uc.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    ArrayList<String> todoList;

    public TodoAdapter(ArrayList<String> todoList){

        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {


                holder.cbTodo.setText(todoList.get(position));

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder{

        CheckBox cbTodo;
        Button btnDel;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            cbTodo = itemView.findViewById(R.id.cbTodo);
            btnDel = itemView.findViewById(R.id.btnDel);

            btnDel.setOnClickListener(v -> {
                //main에 position을 넘겨줘서 삭제 메소드 만들고 파일 열어서 자르고 todolist에 넣고
                //position에 해당하는 인덱스 todolist에서 삭제 후 파일 저장.
                deleteItem(getPosition());

            });


        }
    }
    public void deleteItem(int position) {
        todoList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, todoList.size());
    }


}
