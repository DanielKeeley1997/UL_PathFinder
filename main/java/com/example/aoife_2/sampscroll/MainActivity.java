package com.example.aoife_2.sampscroll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.File;
import java.util.regex.Pattern;


public class MainActivity extends Activity implements OnClickListener {
    SQLiteDatabase mydatabase;
    boolean valid = false;
    Button button;
    EditText ul_id;
    private static final String ulid_regrex = "\\d{6,9}";
    private static final String REQUIRED_MSG = "required";
    private static final String ID_MSG = "Invalid UL ID";
    // Error Messages
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        mydatabase = this.openOrCreateDatabase("characters", android.content.Context.MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS timetables(id VARCHAR PRIMARY KEY,timetable VARCHAR);");
        // Check for existing timetable
        File savedTimetable = new File("ULTimetable");
        if(savedTimetable.exists()) {
            Intent goToTimetable = new Intent(MainActivity.this, displayTimetable.class);
            MainActivity.this.startActivity(goToTimetable);
        }
        //Get the ids of view objects
        findAllViewsId();
        button.setOnClickListener(this);
    }

    private void findAllViewsId() {
        button = (Button) findViewById(R.id.submit);
        ul_id = (EditText) findViewById(R.id.ul_id);
    }

    @Override
    public void onClick(View v) {
        EditText txtID = (EditText) findViewById(R.id.ul_id);
        String ID = (txtID.getText()).toString();
        String regexID = "[0-9]{8}";
        final Cursor cursor;
        cursor = mydatabase.rawQuery("Select count(*) from timetables where id='" + ID + "';",null);
        cursor.moveToFirst();
        if(cursor.getInt(0) == 1) {
            valid = true;
        }
        cursor.close();
        boolean hasInternet = false;
        if(!valid) {
           final CheckWifi hasWifi = new CheckWifi(getApplicationContext());
           hasInternet = hasWifi.isConnectingToInternet(); // Checks for internet connection
        }

        if (ID.matches(regexID) && (hasInternet || valid)) {
            Toast.makeText(getApplicationContext(), "Loading timetable...", Toast.LENGTH_SHORT).show();
            String exists = "0";
            if(valid )
                exists = "1";
            txtID.setText(""); // Clear Student ID text field
            Intent goToTimetable = new Intent(MainActivity.this, displayTimetable.class);
            goToTimetable.putExtra("id", ID);
            goToTimetable.putExtra("exists", exists);
            MainActivity.this.startActivity(goToTimetable);
        }

        // Student ID not valid
        else if (!ID.matches(regexID)) {
            Toast.makeText(getApplicationContext(), ID_MSG, Toast.LENGTH_SHORT).show();
        }

        // No internet access
        else {
            Toast.makeText(getApplicationContext(), "No Internet Access", Toast.LENGTH_SHORT).show();
        }
    }
  ////  public static boolean isID(EditText editText, boolean required) {
      //  return isValid(editText, ulid_regrex, ID_MSG, required);
    //}
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {
        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);
        // text required and editText is blank, so return false
        if ( required && !hasText(editText) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        };

        return true;
    }
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }
}