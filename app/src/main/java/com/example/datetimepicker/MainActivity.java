package com.example.datetimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private TextView result;
    private TextView dateArabic;
    private Button pick;
    private Calendar datetimeCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        dateArabic = findViewById(R.id.dateArabic);
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

        //  2020-09-29 20:09:48

        SimpleDateFormat fromApi = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd yyyy 'at' KK:mm:a", Locale.getDefault());

        String apiDate = fromApi.format(datetimeCalendar.getTime());


        Log.d("MainActivity", sdf.format(datetimeCalendar.getTime()));
        Log.d("MainActivity", apiDate);
        

        convertStringTODate(apiDate);
        result.setText(sdf.format(datetimeCalendar.getTime()));
    }

    private void convertStringTODate(String apiDate) {

        Log.d("MainActivity", "**********************");
        Log.d("MainActivity", apiDate);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            date1 = dateFormat.parse(apiDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("MainActivity", date1 + "");


        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("a hh:mm ", new Locale("ar"));

        Log.d("MainActivity", dateTimeFormat.format(date1));


        SimpleDateFormat dateWeekFormat = new SimpleDateFormat("dd-MM-yyy");

        Log.d("MainActivity", dateWeekFormat.format(date1));
    }
}
