package com.example.user.timetabledemo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
   public String DateResult;
   // TextView theDate = (TextView)getActivity().findViewById(R.id.dateField);
   public int Year;
   public int Month;
   public int Day;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        DateResult = day +"/" + month+1 + "/" + year;
        //NewTask.Date = DateResult;
        Year = year;
        Month = month;
        Day = day;
        ((NewTask)getActivity()).setDateView(DateResult);//set the date text field to the date selected.
    }
    public int getYear(){
        return Year;
    }
}