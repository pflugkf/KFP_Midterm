package com.example.kfp_midterm;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpensesSummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpensesSummaryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_EXPENSES_LIST = "ARG_EXPENSES_LIST";

    // TODO: Rename and change types of parameters
    private ArrayList<Expense> expensesList;

    public ExpensesSummaryFragment() {
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
    public static ExpensesSummaryFragment newInstance(ArrayList<Expense> expenses) {
        ExpensesSummaryFragment fragment = new ExpensesSummaryFragment();
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

    ListView listView;
    ExpensesSummaryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses_summary, container, false);
        listView = view.findViewById(R.id.listView);
        getActivity().setTitle(R.string.summary_fragment_title);


        adapter = new ExpensesSummaryAdapter(getActivity(), R.layout.expenses_summary_list_item, expensesList);
        listView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expenses_summary, container, false);
    }

    class ExpensesSummaryAdapter extends ArrayAdapter<Expense> {

        public ExpensesSummaryAdapter(@NonNull Context context, int resource, @NonNull List<Expense> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.expenses_summary_list_item, parent, false);
            }

            Expense expense = getItem(position);

            TextView date = convertView.findViewById(R.id.textDate);
            TextView total = convertView.findViewById(R.id.textTotalExpenses);

            date.setText(expense.getDate());
            total.setText(String.valueOf(expense.getAmount()));

            return convertView;
        }
    }
}