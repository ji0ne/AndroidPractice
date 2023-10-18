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

        String todo = todoList.get(position);
        holder.cbTodo.setText(todo);

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
        }
    }

}
