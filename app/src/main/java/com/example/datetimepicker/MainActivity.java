package com.example.datetimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private Button pick;
    private Calendar datetimeCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        pick = findViewById(R.id.btn_pick);
        datetimeCalendar = Calendar.getInstance();


        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate();
            }
        });

    }

    private void pickDate() {
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        datetimeCalendar.set(Calendar.YEAR, year);
                        datetimeCalendar.set(Calendar.MONTH, month);
                        datetimeCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        pickTime();
                    }
                },
                datetimeCalendar.get(Calendar.YEAR),
                datetimeCalendar.get(Calendar.MONTH),
                datetimeCalendar.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }

    private void pickTime() {
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        datetimeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        datetimeCalendar.set(Calendar.MINUTE, minute);

                        setDateTimeResult();
                    }
                },
                datetimeCalendar.get(Calendar.HOUR_OF_DAY),
                datetimeCalendar.get(Calendar.MINUTE),
                false
        );
        dialog.show();
    }

    private void setDateTimeResult() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd yyyy 'at' KK:mm:a", Locale.getDefault());
        result.setText(sdf.format(datetimeCalendar.getTime()));
    }
}
