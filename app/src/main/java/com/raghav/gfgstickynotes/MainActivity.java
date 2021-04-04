package com.raghav.gfgstickynotes;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    EditText mEditText;
    //creating a new note
    StickyNote note = new StickyNote();

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        //getting the reference of the EditText
        mEditText = findViewById(R.id.editText);
        //retrieve the text from the saved file in memory(if any) and set
        //it to the edittext
        mEditText.setText(note.getStick(this));

    }

//function to update the Widget(Sticky Note) every time user saves the note
    public void updateWidget() {
        //the AppWidgetManager helps us to manage all the widgets from this app
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        //the RemoteViews class allows us to inflate a layout resource file hierarchy and
        //provides some basic operations for modifying the content of the inflated hierarchy
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.new_app_widget);
        //by using ComponentName class we can get specific application component
        //and to identify the component we pass the package name and the class name
        //inside of that pacakge
        ComponentName thisWidget = new ComponentName(this, AppWidget.class);

        //update the text of the textview of the widget
        remoteViews.setTextViewText(R.id.appwidget_text, mEditText.getText().toString());
        // finally us the AppWidgetManager instance to update all the widgets
        appWidgetManager.updateAppWidget(thisWidget, remoteViews);
    }

//this function saves the current status of the EditText
    public void saveButton(android.view.View v) {

        //update the content of file stored in the memory
        note.setStick(mEditText.getText().toString(), this);
        updateWidget();
        Toast.makeText(this, "Updated Successfully!!", Toast.LENGTH_SHORT).show();

    }


}
 
