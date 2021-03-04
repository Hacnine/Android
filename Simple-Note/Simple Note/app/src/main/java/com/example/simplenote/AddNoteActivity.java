package com.example.simplenote;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText titleEt, descriptionEt;
    // private TextView noteViewTextPriority;
    private NumberPicker numberPicker;

    public static final String EXTRA_ID = "com.tusar.note.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.tusar.note.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.tusar.note.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.tusar.note.EXTRA_PRIORITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        init();

    }

    private void init() {

        titleEt = findViewById(R.id.titleEt);
        descriptionEt = findViewById(R.id.descriptionEt);
        numberPicker = findViewById(R.id.numberPicker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_back);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            //to show the exist title,description and priority in edit note mode
            titleEt.setText(intent.getStringExtra(EXTRA_TITLE));
            descriptionEt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        }  else {
            setTitle("Add Note");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu_2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveItem: {
                saveNote();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String title = titleEt.getText().toString().trim();
        String description = descriptionEt.getText().toString().trim();
        int priority = numberPicker.getValue();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please give title and description",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        //to save note,we don't need #id
        //but to update note,we need #id to identify which one will update
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();


    }
}


