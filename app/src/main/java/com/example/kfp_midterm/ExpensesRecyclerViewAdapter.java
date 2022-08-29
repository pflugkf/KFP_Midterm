package com.example.kfp_midterm;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpensesRecyclerViewAdapter extends RecyclerView.Adapter<ExpensesRecyclerViewAdapter.ExpensesViewHolder>{
    ArrayList<Expense> expenses;
    ExpensesAdapterToFragmentListener mListener;

    public ExpensesRecyclerViewAdapter(ArrayList<Expense> expenses, ExpensesAdapterToFragmentListener mListener){
        this.expenses = expenses;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ExpensesRecyclerViewAdapter.ExpensesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_list_item, parent, false);
        ExpensesViewHolder expensesViewHolder = new ExpensesRecyclerViewAdapter.ExpensesViewHolder(view, mListener);

        return expensesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesViewHolder holder, int position) {

        if(expenses.size() != 0){
            Expense expense = expenses.get(position);
            holder.nameText.setText(expense.getName());
            holder.amount.setText(Double.toString(expense.getAmount()));
            holder.date.setText(expense.getDate());
            holder.category.setText(expense.getCategory());
            holder.position = position;
        }

    }

    @Override
    public int getItemCount() {
        return this.expenses.size();
    }

    public class ExpensesViewHolder extends RecyclerView.ViewHolder {

        TextView nameText;
        TextView amount;
        TextView date;
        TextView category;
        ImageButton delete;
        int position;
        ExpensesAdapterToFragmentListener mListener;

        public ExpensesViewHolder(@NonNull View itemView, ExpensesAdapterToFragmentListener mListener) {
            super(itemView);
            this.mListener = mListener;
            this.nameText = itemView.findViewById(R.id.expense_name);
            this.amount = (TextView)itemView.findViewById(R.id.expense_amount);
            this.date = (TextView)itemView.findViewById(R.id.expense_date);
            this.category = (TextView)itemView.findViewById(R.id.expense_category);
            this.delete = itemView.findViewById(R.id.deleteButton);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.deleteExpense(expenses.get(position));
                }
            });
        }

        public void setNameText(String text) {
            nameText.setText(text);
        }
    }

    interface ExpensesAdapterToFragmentListener {
        void deleteExpense(Expense expenseToDelete);
    }
}
