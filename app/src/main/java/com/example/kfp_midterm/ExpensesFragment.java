package com.example.kfp_midterm;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpensesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpensesFragment extends Fragment implements ExpensesRecyclerViewAdapter.ExpensesAdapterToFragmentListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_EXPENSES_LIST = "ARG_EXPENSES_LIST";

    // TODO: Rename and change types of parameters
    private ArrayList<Expense> expensesList;

    public ExpensesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param expenses Parameter 1.
     * @return A new instance of fragment ExpensesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpensesFragment newInstance(ArrayList<Expense> expenses) {
        ExpensesFragment fragment = new ExpensesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_EXPENSES_LIST, expenses);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            expensesList = (ArrayList<Expense>) getArguments().getSerializable(ARG_EXPENSES_LIST);
        }
    }

    ExpensesFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mListener = (ExpensesFragmentListener) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expenses, container, false);
    }

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ExpensesRecyclerViewAdapter adapter;
    Button summaryButton;
    Button addExpensesButton;
    TextView recordCount;
    TextView totalExpenses;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.expenses_fragment_title);
        expensesList = mListener.getExpensesList();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ExpensesRecyclerViewAdapter(expensesList, this);
        recyclerView.setAdapter(adapter);

        recordCount = view.findViewById(R.id.number_records);
        totalExpenses = view.findViewById(R.id.total_expenses);

        if(expensesList != null){
            recordCount.setText(String.valueOf(expensesList.size()));

            double total = 0.0;

            for(Expense expense : expensesList) {
                total += expense.getAmount();
            }
            //totalExpenses.setText(Double.toString(total));
            totalExpenses.setText(String.format("$%.2f", total));
        } else {
            recordCount.setText("0");
            totalExpenses.setText("$0.0");
        }

        summaryButton = view.findViewById(R.id.expenses_summary_button);

        summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToExpensesSummary();
            }
        });

        addExpensesButton = view.findViewById(R.id.buttonAddExpenses);

        addExpensesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToAddExpense();
            }
        });

    }

    @Override
    public void deleteExpense(Expense expenseToDelete) {
        mListener.removeExpense(expenseToDelete);
    }

    interface ExpensesFragmentListener {
        ArrayList<Expense> getExpensesList();
        void goToExpensesSummary();
        void goToAddExpense();
        void removeExpense(Expense expenseToDelete);
    }
}