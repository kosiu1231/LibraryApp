package com.example.libraryapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditBookActivity extends AppCompatActivity {

    public static final String EXTRA_BOOK_ID = "BOOK_ID";
    public static final String EXTRA_BOOK_TITLE = "BOOK_TITLE";
    public static final String EXTRA_BOOK_AUTHOR = "BOOK_AUTHOR";

    private EditText editTitleEditText;
    private EditText editAuthorEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        editTitleEditText = findViewById(R.id.edit_book_title);
        editAuthorEditText = findViewById(R.id.edit_book_author);

        Bundle extras;
        if ((extras = getIntent().getExtras()) != null && !extras.isEmpty()) {
            editTitleEditText.setText(extras.getString(EXTRA_BOOK_TITLE));
            editAuthorEditText.setText(extras.getString(EXTRA_BOOK_AUTHOR));
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener((view) -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editTitleEditText.getText())
                    || TextUtils.isEmpty(editAuthorEditText.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                if (extras != null) {
                    int id;
                    if((id = extras.getInt(EXTRA_BOOK_ID, -1)) != -1) {
                        replyIntent.putExtra(EXTRA_BOOK_ID, id);
                    }
                }

                String title = editTitleEditText.getText().toString();
                replyIntent.putExtra(EXTRA_BOOK_TITLE, title);
                String author = editAuthorEditText.getText().toString();
                replyIntent.putExtra(EXTRA_BOOK_AUTHOR, author);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}