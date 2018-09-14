package com.example.aoife_2.sampscroll;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.StringReader;

public class displayTimetable extends Activity {

    SQLiteDatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaytimetable);
        mydatabase = this.openOrCreateDatabase("characters", android.content.Context.MODE_PRIVATE, null);
        String ID = "";
        String exists = "";
        String[] colours = {"#9ac0cd", "#ff960a", "#009c6e", "#84001b", "#54dde9"};
        String[] modules = new String[5];
        Bundle extras = getIntent().getExtras();
        if (extras != null)

        {
            ID = extras.getString("id");
            exists = extras.getString("exists");
        }

        String textToPrint = "";
        try {
            if (exists.equals("0")) {
                getTimetable scraper = new getTimetable(ID); // Scrapes UL Timetable website
                textToPrint = scraper.getTextOutput(); // Get formatted timetable as a string
                mydatabase.execSQL("INSERT INTO timetables VALUES('" + ID + "','" + textToPrint + "');");
            } else {
                final Cursor cursor;
                cursor = mydatabase.rawQuery("Select timetable from timetables where id='" + ID + "';", null);
                cursor.moveToFirst();
                if (cursor == null) {
                    return;
                } else {
                    while (!cursor.isAfterLast()) {
                        textToPrint = cursor.getString(0);
                        cursor.moveToNext();
                    }
                }
                cursor.close();
            }
            final String ids = ID;
            String regex = "[-]";
            String line;
            int x, y;
            x = 0;
            y = 0;
            int modulecount = 0;
            String colours2[][] = new String[5][9];
            String split[] = new String[5];
            String[][] timetable = new String[5][9];
            BufferedReader BufferedReader = new BufferedReader(new StringReader(textToPrint));
            while ((line = BufferedReader.readLine()) != null) {
                boolean duplicate = false;
                split = line.split(regex);
                for (int i = 0; i < 5; i++) {
                    if (split[2].equals(modules[i])) {
                        duplicate = true;
                    }
                }
                if (duplicate == false) {
                    modules[modulecount] = split[2];
                    modulecount++;
                }
                String color = "";
                if (modules[0].equals(split[2]))
                    color = colours[0];
                else if (modules[1].equals(split[2]))
                    color = colours[1];
                else if (modules[2].equals(split[2]))
                    color = colours[2];
                else if (modules[3].equals(split[2]))
                    color = colours[3];
                else if (modules[4].equals(split[2]))
                    color = colours[4];
                switch (split[0]) {
                    case "Monday":
                        x = 0;
                        break;
                    case "Tuesday":
                        x = 1;
                        break;
                    case "Wednesday":
                        x = 2;
                        break;
                    case "Thursday":
                        x = 3;
                        break;
                    case "Friday":
                        x = 4;
                        break;
                }
                switch (split[1]) {
                    case "09:00":
                        y = 0;
                        break;
                    case "10:00":
                        y = 1;
                        break;
                    case "11:00":
                        y = 2;
                        break;
                    case "12:00":
                        y = 3;
                        break;
                    case "13:00":
                        y = 4;
                        break;
                    case "14:00":
                        y = 5;
                        break;
                    case "15:00":
                        y = 6;
                        break;
                    case "16:00":
                        y = 7;
                        break;
                    case "17:00":
                        y = 8;
                        break;

                }
                timetable[x][y] = split[2] + "\n" + split[3] + "\n" + split[4];
                System.out.printf(timetable[x][y] + "|" + x + y + "|");
                colours2[x][y] = color;

            }

                //monday 9:00 - 17:00
                if (timetable[0][0] != null) {
                    Button m1 = (Button) findViewById(R.id.mon1);
                    m1.setText(timetable[0][0]);
                    m1.setBackgroundColor(Color.parseColor(colours2[0][0]));
                    m1.setClickable(true);
                }
                if (timetable[0][1] != null) {
                    Button m2 = (Button) findViewById(R.id.mon2);
                    m2.setText(timetable[0][1]);
                    m2.setBackgroundColor(Color.parseColor(colours2[0][1]));
                    m2.setClickable(true);
                }
                if (timetable[0][2] != null) {
                    Button m3 = (Button) findViewById(R.id.mon3);
                    m3.setText(timetable[0][2]);
                    m3.setBackgroundColor(Color.parseColor(colours2[0][2]));
                    m3.setClickable(true);
                }
                if (timetable[0][3] != null) {
                    Button m4 = (Button) findViewById(R.id.mon4);
                    m4.setText(timetable[0][3]);
                    m4.setBackgroundColor(Color.parseColor(colours2[0][3]));
                    m4.setClickable(true);
                }
                if (timetable[0][4] != null) {
                    Button m5 = (Button) findViewById(R.id.mon5);
                    m5.setText(timetable[0][4]);
                    m5.setBackgroundColor(Color.parseColor(colours2[0][4]));
                    m5.setClickable(true);
                }
                if (timetable[0][5] != null) {
                    Button m6 = (Button) findViewById(R.id.mon6);
                    m6.setText(timetable[0][5]);
                    m6.setBackgroundColor(Color.parseColor(colours2[0][5]));
                    m6.setClickable(true);
                }
                if (timetable[0][6] != null) {
                    Button m7 = (Button) findViewById(R.id.mon7);
                    m7.setText(timetable[0][6]);
                    m7.setBackgroundColor(Color.parseColor(colours2[0][6]));
                    m7.setClickable(true);
                }
                if (timetable[0][7] != null) {
                    Button m8 = (Button) findViewById(R.id.mon8);
                    m8.setText(timetable[0][7]);
                    m8.setBackgroundColor(Color.parseColor(colours2[0][7]));
                    m8.setClickable(true);
                }
                if (timetable[0][8] != null) {
                    Button m9 = (Button) findViewById(R.id.mon9);
                    m9.setText(timetable[0][8]);
                    m9.setBackgroundColor(Color.parseColor(colours2[0][8]));
                    m9.setClickable(true);
                }
                //Tuesday 9:00 - 17:00
                if (timetable[1][0] != null) {
                    Button t1 = (Button) findViewById(R.id.tue1);
                    t1.setText(timetable[1][0]);
                    t1.setBackgroundColor(Color.parseColor(colours2[1][0]));
                    t1.setClickable(true);
                }
                if (timetable[1][1] != null) {
                    Button t2 = (Button) findViewById(R.id.tue2);
                    t2.setText(timetable[1][1]);
                    t2.setBackgroundColor(Color.parseColor(colours2[1][1]));
                    t2.setClickable(true);
                }
                if (timetable[1][2] != null) {
                    Button t3 = (Button) findViewById(R.id.tue3);
                    t3.setText(timetable[1][2]);
                    t3.setBackgroundColor(Color.parseColor(colours2[1][2]));
                    t3.setClickable(true);
                }
                if (timetable[1][3] != null) {
                    Button t4 = (Button) findViewById(R.id.tue4);
                    t4.setText(timetable[1][3]);
                    t4.setBackgroundColor(Color.parseColor(colours2[1][3]));
                    t4.setClickable(true);
                }
                if (timetable[1][4] != null) {
                    Button t5 = (Button) findViewById(R.id.tue5);
                    t5.setText(timetable[1][4]);
                    t5.setBackgroundColor(Color.parseColor(colours2[1][4]));
                    t5.setClickable(true);
                }
                if (timetable[1][5] != null) {
                    Button t6 = (Button) findViewById(R.id.tue6);
                    t6.setText(timetable[1][5]);
                    t6.setBackgroundColor(Color.parseColor(colours2[1][5]));
                    t6.setClickable(true);
                }
                if (timetable[1][6] != null) {
                    Button t7 = (Button) findViewById(R.id.tue7);
                    t7.setText(timetable[1][6]);
                    t7.setBackgroundColor(Color.parseColor(colours2[1][6]));
                    t7.setClickable(true);
                }
                if (timetable[1][7] != null) {
                    Button t8 = (Button) findViewById(R.id.tue8);
                    t8.setText(timetable[1][7]);
                    t8.setBackgroundColor(Color.parseColor(colours2[1][7]));
                    t8.setClickable(true);
                }
                if (timetable[1][8] != null) {
                    Button t9 = (Button) findViewById(R.id.tue9);
                    t9.setText(timetable[1][8]);
                    t9.setBackgroundColor(Color.parseColor(colours2[1][8]));
                    t9.setClickable(true);
                }
                //wednesday 9:00 - 17:00
                if (timetable[2][0] != null) {
                    Button w1 = (Button) findViewById(R.id.wed1);
                    w1.setText(timetable[2][0]);
                    w1.setBackgroundColor(Color.parseColor(colours2[2][0]));
                    w1.setClickable(true);
                }
                if (timetable[2][1] != null) {
                    Button w2 = (Button) findViewById(R.id.wed2);
                    w2.setText(timetable[2][1]);
                    w2.setBackgroundColor(Color.parseColor(colours2[2][1]));
                    w2.setClickable(true);
                }
                if (timetable[2][2] != null) {
                    Button w3 = (Button) findViewById(R.id.wed3);
                    w3.setText(timetable[2][2]);
                    w3.setBackgroundColor(Color.parseColor(colours2[2][2]));
                    w3.setClickable(true);
                }
                if (timetable[2][3] != null) {
                    Button w4 = (Button) findViewById(R.id.wed4);
                    w4.setText(timetable[2][3]);
                    w4.setBackgroundColor(Color.parseColor(colours2[2][3]));
                    w4.setClickable(true);
                }
                if (timetable[2][4] != null) {
                    Button w5 = (Button) findViewById(R.id.wed5);
                    w5.setText(timetable[2][4]);
                    w5.setBackgroundColor(Color.parseColor(colours2[2][4]));
                    w5.setClickable(true);
                }
                if (timetable[2][5] != null) {
                    Button w6 = (Button) findViewById(R.id.wed6);
                    w6.setText(timetable[2][5]);
                    w6.setBackgroundColor(Color.parseColor(colours2[2][5]));
                    w6.setClickable(true);
                }
                if (timetable[2][6] != null) {
                    Button w7 = (Button) findViewById(R.id.wed7);
                    w7.setText(timetable[2][6]);
                    w7.setBackgroundColor(Color.parseColor(colours2[2][6]));
                    w7.setClickable(true);
                }
                if (timetable[2][7] != null) {
                    Button w8 = (Button) findViewById(R.id.wed8);
                    w8.setText(timetable[2][7]);
                    w8.setBackgroundColor(Color.parseColor(colours2[2][7]));
                    w8.setClickable(true);
                }
                if (timetable[2][8] != null) {
                    Button w9 = (Button) findViewById(R.id.wed9);
                    w9.setText(timetable[2][8]);
                    w9.setBackgroundColor(Color.parseColor(colours2[2][8]));
                    w9.setClickable(true);
                }
                //thursday 9:00 - 17:00
                if (timetable[3][0] != null) {
                    Button th1 = (Button) findViewById(R.id.thur1);
                    th1.setText(timetable[3][0]);
                    th1.setBackgroundColor(Color.parseColor(colours2[3][0]));
                    th1.setClickable(true);
                }
                if (timetable[3][1] != null) {
                    Button th2 = (Button) findViewById(R.id.thur2);
                    th2.setText(timetable[3][1]);
                    th2.setBackgroundColor(Color.parseColor(colours2[3][1]));
                    th2.setClickable(true);
                }
                if (timetable[3][2] != null) {
                    Button th3 = (Button) findViewById(R.id.thur3);
                    th3.setText(timetable[3][2]);
                    th3.setBackgroundColor(Color.parseColor(colours2[3][2]));
                    th3.setClickable(true);
                }
                if (timetable[3][3] != null) {
                    Button th4 = (Button) findViewById(R.id.thur4);
                    th4.setText(timetable[3][3]);
                    th4.setBackgroundColor(Color.parseColor(colours2[3][3]));
                    th4.setClickable(true);
                }
                if (timetable[3][4] != null) {
                    Button th5 = (Button) findViewById(R.id.thur5);
                    th5.setText(timetable[3][4]);
                    th5.setBackgroundColor(Color.parseColor(colours2[3][4]));
                    th5.setClickable(true);
                }
                if (timetable[3][5] != null) {
                    Button th6 = (Button) findViewById(R.id.thur6);
                    th6.setText(timetable[3][5]);
                    th6.setBackgroundColor(Color.parseColor(colours2[3][5]));
                    th6.setClickable(true);
                }
                if (timetable[3][6] != null) {
                    Button th7 = (Button) findViewById(R.id.thur7);
                    th7.setText(timetable[3][6]);
                    th7.setBackgroundColor(Color.parseColor(colours2[3][6]));
                    th7.setClickable(true);
                }
                if (timetable[3][7] != null) {
                    Button th8 = (Button) findViewById(R.id.thur8);
                    th8.setText(timetable[3][7]);
                    th8.setBackgroundColor(Color.parseColor(colours2[3][7]));
                    th8.setClickable(true);
                }
                if (timetable[3][8] != null) {
                    Button th9 = (Button) findViewById(R.id.thur9);
                    th9.setText(timetable[3][8]);
                    th9.setBackgroundColor(Color.parseColor(colours2[3][8]));
                    th9.setClickable(true);
                }
                //friday 9:00 - 17:00
                if (timetable[4][0] != null) {
                    Button f1 = (Button) findViewById(R.id.fri1);
                    f1.setText(timetable[4][0]);
                    f1.setBackgroundColor(Color.parseColor(colours2[4][0]));
                    f1.setClickable(true);
                }
                if (timetable[4][1] != null) {
                    Button f2 = (Button) findViewById(R.id.fri2);
                    f2.setText(timetable[4][1]);
                    f2.setBackgroundColor(Color.parseColor(colours2[4][1]));
                    f2.setClickable(true);
                }
                if (timetable[4][2] != null) {
                    Button f3 = (Button) findViewById(R.id.fri3);
                    f3.setText(timetable[4][2]);
                    f3.setBackgroundColor(Color.parseColor(colours2[4][2]));
                    f3.setClickable(true);
                }
                if (timetable[4][3] != null) {
                    Button f4 = (Button) findViewById(R.id.fri4);
                    f4.setText(timetable[4][3]);
                    f4.setBackgroundColor(Color.parseColor(colours2[4][3]));
                    f4.setClickable(true);
                }
                if (timetable[4][4] != null) {
                    Button f5 = (Button) findViewById(R.id.fri5);
                    f5.setText(timetable[4][4]);
                    f5.setBackgroundColor(Color.parseColor(colours2[4][4]));
                    f5.setClickable(true);
                }
                if (timetable[4][5] != null) {
                    Button f6 = (Button) findViewById(R.id.fri6);
                    f6.setText(timetable[4][5]);
                    f6.setBackgroundColor(Color.parseColor(colours2[4][5]));
                    f6.setClickable(true);
                }
                if (timetable[4][6] != null) {
                    Button f7 = (Button) findViewById(R.id.fri7);
                    f7.setText(timetable[4][6]);
                    f7.setBackgroundColor(Color.parseColor(colours2[4][6]));
                    f7.setClickable(true);
                }
                if (timetable[4][7] != null) {
                    Button f8 = (Button) findViewById(R.id.fri8);
                    f8.setText(timetable[4][7]);
                    f8.setBackgroundColor(Color.parseColor(colours2[4][7]));
                    f8.setClickable(true);
                }
                if (timetable[4][8] != null) {
                    Button f9 = (Button) findViewById(R.id.fri9);
                    f9.setText(timetable[4][8]);
                    f9.setBackgroundColor(Color.parseColor(colours2[4][8]));
                    f9.setClickable(true);
                }
            }
            catch(
                    Exception e
            ){
            }

    }

    public  void getID(View v){
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        String [] text =  buttonText.split("\n");
        String buildingCode = text[2];
            if (buttonText != null) {
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                intent.putExtra("BUILDING_CODE", buildingCode);  // pass your values and retrieve them in the other Activity using buildingcode
                startActivity(intent);

            }
    }
}
