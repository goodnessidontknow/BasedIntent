package com.appdev.basedintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RequiresApi(api = Build.VERSION_CODES.M)
public class DatePickerFragment extends DialogFragment {

    private static final String ARG_DATE = "date";
    private DatePicker mDatePicker;
    private TimePicker mTimePicker;

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        mDatePicker = view.findViewById(R.id.datepicker);
        mTimePicker = view.findViewById(R.id.timepicker);

        initializeDatePicker();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    Fragment target = getTargetFragment();
                    if (target instanceof OnDateUpdateListener){
                        Date date = new Date(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth(), mTimePicker.getHour(), mTimePicker.getMinute());
                        ((OnDateUpdateListener) target).onDateUpdate(date);
                    }
                });
        return builder.create();
    }

    private void initializeDatePicker() {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        mDatePicker.init(year, month, day, null);
        mTimePicker.setIs24HourView(true);
        mTimePicker.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        mTimePicker.setMinute(calendar.get(Calendar.MINUTE));
    }

    interface OnDateUpdateListener{
        void onDateUpdate(Date date);

    }
}
