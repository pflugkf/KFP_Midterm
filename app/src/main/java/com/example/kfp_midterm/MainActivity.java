package com.example.kfp_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExpensesFragment.ExpensesFragmentListener, AddExpenseFragment.AddExpenseFragmentListener, PickCategoryFragment.PickCategoryFragmentListener {

    ArrayList<Expense> expenses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.rootView, ExpensesFragment.newInstance(expenses), "expenses").commit();
    }

    @Override
    public ArrayList<Expense> getExpensesList() {
        return expenses;
    }

    @Override
    public void goToExpensesSummary() {
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, new ExpensesSummaryFragment()).addToBackStack(null).commit();
    }

    @Override
    public void goToAddExpense() {
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, new AddExpenseFragment(), "add_expense").addToBackStack(null).commit();
    }

    @Override
    public void removeExpense(Expense expenseToDelete) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).name.equals(expenseToDelete.name)) {
                expenses.remove(i);
            }
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, ExpensesFragment.newInstance(expenses)).commit();
    }

    @Override
    public void getCategory() {
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, new PickCategoryFragment()).addToBackStack(null).commit();
    }

    @Override
    public void returnNewExpense(Expense newExpense) {
        expenses.add(newExpense);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancel() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void returnCategory(String pickedCategory) {
        AddExpenseFragment addExpenseFragment = (AddExpenseFragment) getSupportFragmentManager().findFragmentByTag("add_expense");
        if(addExpenseFragment != null){
            addExpenseFragment.setCategory(pickedCategory);
        }
        getSupportFragmentManager().popBackStack();
    }
}