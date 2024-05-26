package com.example.konanapm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    Button loginbutton;
    EditText usernameET, passwordET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loginbutton = findViewById(R.id.login_button);
        usernameET = findViewById(R.id.login_email);
        passwordET = findViewById(R.id.login_password);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserTable usertable;
                try {
                    usertable = new UserTable(usernameET.getText().toString(),passwordET.getText().toString(),-1);
                }catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    usertable = new UserTable("ERROR","Error",-1);
                }
                databaseHelper db = new databaseHelper(LoginActivity.this);
                db.sendToDatabase(usertable);
            }
        });



    }
}