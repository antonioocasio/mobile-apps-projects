package edu.lewisu.cs.antonioocasio.courseratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private CourseRating courseRating;
    private EditText courseNameEditText;
    private EditText instructorNameEditText;
    private Spinner courseTypeSpinner;
    private RatingBar courseRatingBar;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        courseRating = new CourseRating();

        courseNameEditText = findViewById(R.id.courseNameEditText);
        courseNameEditText.addTextChangedListener(new NameTextListener(courseNameEditText));

        instructorNameEditText = findViewById(R.id.instructorNameEditText);
        instructorNameEditText.addTextChangedListener(new NameTextListener(instructorNameEditText));

        courseTypeSpinner = findViewById(R.id.courseTypeSpinner);
        courseTypeSpinner.setOnItemSelectedListener(new CourseTypeSelectedListener());


        courseRatingBar = findViewById(R.id.ratingBar);
        courseRatingBar.setOnRatingBarChangeListener(new RatingChangedListener());

//        submitButton = findViewById(R.id.submitButton);
//        submitButton.setOnClickListener(new SubmitClickListener());
    }

    public void buttonClick(View v){
        Toast.makeText(getApplicationContext(),
                courseRating.toString(),
                Toast.LENGTH_SHORT).show();
    }

//    private class SubmitClickListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            Toast.makeText(getApplicationContext(), courseRating.toString(), Toast.LENGTH_SHORT).show();
//
//        }
//
//    }

    private class CourseTypeSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String courseType = (String) adapterView.getItemAtPosition(i);
            if (i != 0) {
                courseRating.setCourseType(courseType);
                Log.d(TAG, "course type: " + courseRating.getCourseType());
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class RatingChangedListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            courseRating.setRating((int) v);
            Log.d(TAG, "rating " + v);


        }
    }

    private class NameTextListener implements TextWatcher {

        private View editText;

        public NameTextListener(View v) {
            editText = v;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (editText == courseNameEditText) {
                courseRating.setCourseName(charSequence.toString());
                Log.d(TAG, "updating course name to " + courseRating.getCourseName());
            } else if (editText == instructorNameEditText) {
                courseRating.setInstructorName(charSequence.toString());
                Log.d(TAG, "updating instructor name to " + courseRating.getInstructorName());
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}


