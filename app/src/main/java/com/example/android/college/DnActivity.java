package com.example.android.college;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DnActivity extends AppCompatActivity {
    Button f;
    DatabaseReference rootRef,demoRef;
    TextView e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dn);
        f=(Button)findViewById(R.id.f);
        e=(TextView)findViewById(R.id.e);
        rootRef= FirebaseDatabase.getInstance().getReference();
        demoRef=rootRef;


        demoRef.child("JAVA").child("A").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    String location = locationSnapshot.getValue().toString();
                    Log.d("LOL", "onDataChange: " + location);
                }
                String v=dataSnapshot.getValue(String.class);
                e.setText(v);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
