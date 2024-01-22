package com.example.food_drink;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.food_drink.databinding.ActivityAddNoteBinding;

public class AddNoteActivity extends AppCompatActivity {

    private ActivityAddNoteBinding binding;
    private NotesDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = new NotesDatabaseHelper(this);

        binding.saveButton.setOnClickListener(view -> {
            String title = binding.titleEditText.getText().toString();
            String content = binding.contentEditText.getText().toString();

            Note note = new Note (0, title, content);
            db.insertNote(note);
            Toast.makeText(this, "Đã Thêm", Toast.LENGTH_SHORT).show();
            finish();
        });


    }
}
