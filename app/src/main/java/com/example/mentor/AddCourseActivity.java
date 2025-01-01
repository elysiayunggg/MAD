package com.example.mentor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.bumptech.glide.Glide; // Add this import if you're using Glide
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddCourseActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView courseImageView;

    private EditText titleEditText, descriptionEditText, lessonsEditText;
    private LinearLayout modulesLayout;
    private Button addModulesButton;
    private Button doneButton;

    private Bitmap selectedImageBitmap;

    private EditText imageUrlInput;
    private Button fetchImageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable navigation (back button)
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(AddCourseActivity.this, MyCourseActivity.class);
            startActivity(intent);
            finish(); // End AddCourseActivity
        });

        // Initialize views
        titleEditText = findViewById(R.id.courseTitle);
        descriptionEditText = findViewById(R.id.courseDescription);
        lessonsEditText = findViewById(R.id.tvLessons);
        modulesLayout = findViewById(R.id.modulesLayout);
        addModulesButton = findViewById(R.id.addModules);
        doneButton = findViewById(R.id.doneButton);
        courseImageView = findViewById(R.id.courseImageView);
        imageUrlInput = findViewById(R.id.imageUrlInput);
        fetchImageButton = findViewById(R.id.fetchImageButton);

        // Fetch image when button is clicked
        fetchImageButton.setOnClickListener(v -> {
            String imageUrl = imageUrlInput.getText().toString().trim();
            if (!imageUrl.isEmpty()) {
                loadImageFromUrl(imageUrl);
            } else {
                Toast.makeText(this, "Please enter a valid URL.", Toast.LENGTH_SHORT).show();
            }
        });

        addModulesButton.setOnClickListener(v -> addModulesField());



        doneButton.setOnClickListener(v -> {
            // Collect data from fields
            String title = titleEditText.getText().toString().trim();
            String description = descriptionEditText.getText().toString().trim();
            String lessonsCountStr = lessonsEditText.getText().toString().trim();
            String imageUrl = imageUrlInput.getText().toString().trim();

            // Initialize a StringBuilder to hold error messages
            StringBuilder errorMessage = new StringBuilder();

            // Validate each field and add errors to the message
            if (title.isEmpty()) {
                errorMessage.append("- Please enter a title.\n");
            }
            if (description.isEmpty()) {
                errorMessage.append("- Please enter a description.\n");
            }
            if (lessonsCountStr.isEmpty()) {
                errorMessage.append("- Please enter the number of lessons.\n");
            }
            if (imageUrl.isEmpty()) {
                errorMessage.append("- Please provide an image URL.\n");
            }

            // If there are errors, show the error message and stop the process
            if (errorMessage.length() > 0) {
                new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Incomplete Information")
                        .setMessage(errorMessage.toString())
                        .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                        .setCancelable(false)
                        .show();
                return;
            }

            // If all fields are valid, proceed
            int lessonsCount = Integer.parseInt(lessonsCountStr);

            // Collect requirements (optional fields)
            List<String> modules = new ArrayList<>();
            for (int i = 0; i < modulesLayout.getChildCount(); i++) {
                LinearLayout row = (LinearLayout) modulesLayout.getChildAt(i);
                EditText requirementField = (EditText) row.getChildAt(0);
                String module = requirementField.getText().toString().trim();
                if (!module.isEmpty()) {
                    modules.add(module);
                }
            }

            // Pass data back to MyCourseActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("courseTitle", title);
            resultIntent.putExtra("courseDescription", description);
            resultIntent.putExtra("lessonsCount", lessonsCount);
            resultIntent.putStringArrayListExtra("modules", new ArrayList<>(modules));
            resultIntent.putExtra("imageUrl", imageUrl);

            setResult(RESULT_OK, resultIntent);

            // Show success dialog
            showSuccessDialog();
        });
    }

    // Load image from URL
    private void loadImageFromUrl(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("GlideError", "Error loading image", e);
                        return false; // Important to return false so the error placeholder can be displayed
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(courseImageView);
    }

    // Dynamically add a new requirement field (EditText) and delete icon
    private void addModulesField() {
        // Create a new LinearLayout row
        LinearLayout moduleRow = new LinearLayout(this);
        moduleRow.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rowParams.setMargins(0, 16, 0, 0); // Add top margin for spacing between rows
        moduleRow.setLayoutParams(rowParams);

        // Create a new EditText for the requirement
        EditText requirementField = new EditText(this);
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1); // Match weight = 1
        requirementField.setLayoutParams(editTextParams);
        requirementField.setHint("Module");
        requirementField.setPadding(10, 15, 10, 10); // Match padding
        requirementField.setTextColor(getResources().getColor(R.color.light_gray)); // Match text color
        requirementField.setBackground(getResources().getDrawable(R.drawable.edit_text_background)); // Match background

        // Create a delete icon (ImageView) for removing the field
        ImageView deleteIcon = new ImageView(this);
        LinearLayout.LayoutParams deleteIconParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        deleteIconParams.setMargins(16, 0, 0, 0); // Add some spacing
        deleteIcon.setLayoutParams(deleteIconParams);
        deleteIcon.setImageResource(R.drawable.baseline_delete_24); // Match delete icon
        deleteIcon.setContentDescription("Delete Module");
        deleteIcon.setPadding(10, 10, 30, 10); // Match padding

        // Add click listener to delete the specific row
        deleteIcon.setOnClickListener(v -> modulesLayout.removeView(moduleRow));

        // Add the EditText and delete icon to the row
        moduleRow.addView(requirementField);
        moduleRow.addView(deleteIcon);

        // Add the new requirement row to the requirements layout
        modulesLayout.addView(moduleRow);
    }

    // Attach delete listeners to existing rows in the requirements layout
    private void attachDeleteListenersToExistingRows() {
        for (int i = 0; i < modulesLayout.getChildCount(); i++) {
            View row = modulesLayout.getChildAt(i);
            if (row instanceof LinearLayout) {
                LinearLayout rowLayout = (LinearLayout) row;
                View deleteIcon = rowLayout.getChildAt(1); // Assuming the delete icon is the second view
                if (deleteIcon instanceof ImageView) {
                    deleteIcon.setOnClickListener(v -> modulesLayout.removeView(rowLayout));
                }
            }
        }
    }

    // Method triggered when ImageView is clicked
    public void chooseImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Handle the result of the image picker
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                // Convert the selected image to a Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                selectedImageBitmap = bitmap;
                // Set the Bitmap to the ImageView
                courseImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSuccessDialog() {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Course Added")
                .setMessage("The course has been successfully added.")
                .setPositiveButton("OK", (dialog, which) -> {
                    // Close the dialog and the activity
                    dialog.dismiss();
                    finish(); // Close AddCourseActivity and return to MyCourseActivity
                })
                .setCancelable(false) // Prevent dismissing the dialog by clicking outside
                .show();
    }

    @Override
    public void onBackPressed() {
        // Navigate to MyCourseActivity when the back button is pressed
        Intent intent = new Intent(AddCourseActivity.this, MyCourseActivity.class);
        startActivity(intent);
        finish(); // End AddCourseActivity
        super.onBackPressed();
    }
}