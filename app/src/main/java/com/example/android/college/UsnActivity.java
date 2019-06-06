package com.example.android.college;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsnActivity extends AppCompatActivity {
EditText u;
Button s;
    DatabaseReference rootRef,demoRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usn);
        s=(Button)findViewById(R.id.usub);
        rootRef = FirebaseDatabase.getInstance().getReference();
        //database reference pointing to demo node
        demoRef = rootRef.child("JAVA");
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = u.getText().toString();

                demoRef.push().setValue(value);

                startActivity(new Intent(UsnActivity.this,DAActivity.class));
    }
    });
    }
}
