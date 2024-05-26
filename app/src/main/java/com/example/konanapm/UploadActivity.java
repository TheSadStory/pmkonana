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

public class UploadActivity extends AppCompatActivity {

    EditText platform, username, password;
    Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upload);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        platform = findViewById(R.id.uploadPlatform);
        username = findViewById(R.id.uploadUsername);
        password = findViewById(R.id.uploadPassword);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plat = platform.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                databaseHelper db = new databaseHelper(UploadActivity.this);
                int nr = 0;
                UserTable userTable = new UserTable();
                db.getUserID(userTable, nr);
                UserDataTable userdataTable;
                try {
                     userdataTable = new UserDataTable(-1, plat, user, pass, 3);
                    Toast.makeText(UploadActivity.this, "Singup successful", Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    Toast.makeText(UploadActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                     userdataTable = new UserDataTable(-1,"ERROR","Error","error",-1);
                }
                db.sendToDatabase2(userdataTable);
                String test = String.valueOf(nr);
                Toast.makeText(UploadActivity.this, "es geht: "+test, Toast.LENGTH_SHORT).show();

            }
        });


    }
}