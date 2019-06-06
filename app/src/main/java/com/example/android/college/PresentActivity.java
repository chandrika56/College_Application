package com.example.android.college;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

public class PresentActivity extends AppCompatActivity {
    int ps=0;
    int as=0;
    //int as2=0;
    //int ps2=0;
    DatabaseReference rootRef,demoRef;
   TextView b;
   Button a,z,x;
   int score=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present);
        b=(TextView)findViewById(R.id.tv);
        a=(Button)findViewById(R.id.bt3);
        z=(Button) findViewById(R.id.PRESENT);
        x=(Button) findViewById(R.id.ABSENT);
        rootRef = FirebaseDatabase.getInstance().getReference();
        //database reference pointing to demo node
        demoRef = rootRef.child("JAVA");
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        //mDatabase.child("Present").setValue(ps);
       // mDatabase.child("Absent").setValue(ps);
        a.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                score = score + 1;
                display(score);
            }


            private void display(int number) {
                TextView displayInteger = (TextView) findViewById(R.id.tv);
                displayInteger.setText("1DS16CS0: " + number);
            }
        });

        z.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                mDatabase.child("JAVA").child("Present").runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(final MutableData currentData) {
                        if (currentData.getValue() == null) {
                            currentData.setValue("1");
                        } else {
                            String stringValue = (String) currentData.getValue();
                             int intValue=1;
                            // int intValue = Integer.parseInt(stringValue);
                            int increasedIntValue = intValue + 1;
                            currentData.setValue(Integer.toString(increasedIntValue));
                        }
                        return Transaction.success(currentData);
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot currentData) {
                        if (databaseError != null) {
                            System.out.println("Firebase counter increment failed!");
                        } else {
                            System.out.println("Firebase counter increment succeeded!");
                        }
                    }
                });


              //  ps=ps+1;
               // demoRef.push().child("Present").setValue(ps);

            }
        });

        x.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                mDatabase.child("JAVA").child("Absent").runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(final MutableData currentData) {
                        if (currentData.getValue() == null) {
                            currentData.setValue("0");
                        } else {
                            String stringValue = (String) currentData.getValue();
                            int intValue = Integer.parseInt(stringValue);
                            int increasedIntValue = intValue + 1;
                            currentData.setValue(Integer.toString(increasedIntValue));
                        }
                        return Transaction.success(currentData);
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot currentData) {
                        if (databaseError != null) {
                            System.out.println("Firebase counter increment failed!");
                        } else {
                            System.out.println("Firebase counter increment succeeded!");
                        }
                    }
                });



                //ps=ps-1;
//                demoRef.push().child("Absent").setValue(ps);

            }
        });


    }
}
