package com.example.part2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText code;

    String validationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        code = findViewById(R.id.code);
    }

    public void sendButton_click(View view) {

        String personName = name.getText().toString();
        String emailAddress = email.getText().toString();

        Random random = new Random();

        validationCode = String.valueOf(100000 + random.nextInt(900000));

        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailto:" + emailAddress));

        intent.putExtra(Intent.EXTRA_SUBJECT, "Validation Code");

        intent.putExtra(Intent.EXTRA_TEXT,
                "Hello " + personName + ",\n\n" +
                        "Your validation code is: " + validationCode);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this,
                    "No email application found!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void validateButton_click(View view) {

        String enteredCode = code.getText().toString();

        if (enteredCode.equals(validationCode)) {

            Toast.makeText(this,
                    "Validation successful!",
                    Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this,
                    "Incorrect validation code!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}