package com.example.studentsrepository;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.studentsrepository.model.Model;
import com.example.studentsrepository.model.Student;

import java.util.List;

public class EditStudentActivity extends AppCompatActivity {

    List<Student> data = Model.instance.getAllStudents();
    int index;

    private EditText studentName;
    private EditText studentId;
    private EditText studentPhone;
    private EditText studentAddress;
    private CheckBox flagCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_student);

        Log.

        index = getIntent().getIntArrayExtra("INDEX")[0];
        String[] studentDetails = getIntent().getStringArrayExtra("STUDENT_DETAILS_ARRAY");
        boolean flag = getIntent().getBooleanArrayExtra("IS_VALID_STUDENT")[0];

        studentName = findViewById(R.id.edit_name_et);
        studentId = findViewById(R.id.edit_id_et);
        studentPhone = findViewById(R.id.edit_phone_et);
        studentAddress = findViewById(R.id.edit_address_et);
        flagCB = findViewById(R.id.edit_cb);

        studentName.setText(studentDetails[0]);
        studentId.setText(studentDetails[1]);
        studentPhone.setText(studentDetails[2]);
        studentAddress.setText(studentDetails[3]);
        flagCB.setChecked(flag);
    }

    public void onClickCancelButton(View view)
    {
        Intent intent = new Intent(this, StudentDetailsActivity.class);
        startActivity(intent);
    }

    public void onClickSaveButton(View view)
    {
        data.get(index).setName(studentName.getText().toString());
        data.get(index).setId(studentId.getText().toString());
        data.get(index).setPhone(studentPhone.getText().toString());
        data.get(index).setAddress(studentAddress.getText().toString());
        data.get(index).setFlag(flagCB.isChecked());
//        intent.putExtra("SAVED_DETAILS", new String[] {studentName.getText().toString(), studentId.getText().toString(),
//                                                studentPhone.getText().toString(), studentAddress.getText().toString()});
//        intent.putExtra("SAVED_FLAG", flagCB.isChecked());
        Intent intent = new Intent(this, StudentDetailsActivity.class);
        startActivity(intent);
    }

    public void onClickDeleteButton(View view)
    {
        Intent intent = new Intent(this, StudentDetailsActivity.class);
        startActivity(intent);
    }
}                              