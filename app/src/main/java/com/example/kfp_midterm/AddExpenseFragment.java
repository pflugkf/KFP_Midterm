package com.example.kfp_midterm;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddExpenseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddExpenseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddExpenseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddExpenseFragment newInstance(String param1, String param2) {
        AddExpenseFragment fragment = new AddExpenseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    AddExpenseFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mListener = (AddExpenseFragmentListener) context;
    }

    String dateEntered = "None";
    String category = "None";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(dateEntered == null) {

        } else {

        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_expense, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView dateTextView = view.findViewById(R.id.dateText);
        EditText expenseName = view.findViewById(R.id.editTextExpenseName);
        EditText expenseAmount = view.findViewById(R.id.editTextExpenseAmount);
        TextView categoryText = view.findViewById(R.id.categoryText);

        view.findViewById(R.id.setDateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateEntered = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
                                dateTextView.setText(dateEntered);
                            }
                        }, year, month, day);

                dpd.show();

            }
        });

        dateTextView.setText(dateEntered);
        categoryText.setText(category);

        view.findViewById(R.id.pickCategoryButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.getCategory();
            }
        });

        view.findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = "";
                String date = "";
                double amount = 0.0;
                String expenseCategory = "";

                if(expenseName.getText().toString().equals("")){
                    Toast.makeText(view.getContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                } else {
                    if(expenseAmount.getText().toString().equals("")) {
                        Toast.makeText(view.getContext(), "Please enter a valid amount", Toast.LENGTH_SHORT).show();
                    } else {
                        if(dateTextView.getText().toString().equals("None")){
                            Toast.makeText(view.getContext(), "Please enter a date", Toast.LENGTH_SHORT).show();
                        } else {
                            if(categoryText.getText().toString().equals("None")){
                                Toast.makeText(view.getContext(), "Please choose a category", Toast.LENGTH_SHORT).show();
                            } else {
                                name = expenseName.getText().toString();
                                date = dateEntered;
                                amount = Double.parseDouble(expenseAmount.getText().toString());
                                expenseCategory = category;

                                Expense newExpense = new Expense(name, amount, date, expenseCategory);
                                mListener.returnNewExpense(newExpense);
                            }
                        }
                    }
                }


            }
        });

        view.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.cancel();
            }
        });
    }

    public void setCategory(String chosenCategory){
        category = chosenCategory;
    }

    interface AddExpenseFragmentListener {
        void getCategory();
        void returnNewExpense(Expense newExpense);
        void cancel();
    }
}