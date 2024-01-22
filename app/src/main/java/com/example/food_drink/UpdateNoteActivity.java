package com.example.food_drink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.food_drink.databinding.ActivityAddNoteBinding;
import com.example.food_drink.databinding.ActivityUpdateNoteBinding;

public class UpdateNoteActivity extends AppCompatActivity {

    private ActivityUpdateNoteBinding binding;
    private int noteId = -1;
    private NotesDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new NotesDatabaseHelper(this);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("note_id", -1);
        if (noteId == -1) {
            finish();
            return;
        }

        Note note = db.getNoteByID(noteId);
        binding.updateTitleEditText.setText(note.getTitle());
        binding.updateContentEditText.setText(note.getContent());

        binding.updateSaveButton.setOnClickListener(view -> {
            String newTitle = binding.updateTitleEditText.getText().toString();
            String newContent = binding.updateContentEditText.getText().toString();

            if (!newTitle.isEmpty() && !newContent.isEmpty()) {
                Note updateNote = new Note(noteId, newTitle, newContent);
                db.updateNote(updateNote);
                finish();
                Toast.makeText(this, "Đã lưu cập nhật", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Tiêu đề và nội dung không được để trống", Toast.LENGTH_SHORT).show();
            }
        });


    }
}