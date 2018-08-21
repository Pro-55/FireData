package com.example.admin.firedata;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button buttonOne, buttonTwo, buttonClear;
    private TextView mainTextView;
    private static final String TAG = "MainActivity";

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference messageRef = rootRef.child("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        mainTextView = findViewById(R.id.mainTextView);

        buttonOne = findViewById(R.id.buttonOne);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageRef.setValue(buttonOne.getText().toString() + " Snapped!");
            }
        });

        buttonTwo = findViewById(R.id.buttonTwo);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageRef.setValue(buttonTwo.getText().toString() + " Snapped!");
            }
        });

        buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageRef.setValue("____");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        messageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String textViewMessage = dataSnapshot.getValue(String.class);
                Log.d(TAG,": LogTag: String: " + dataSnapshot.getValue());
                mainTextView.setText(textViewMessage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setText(String buttonText) {
        mainTextView.setText(buttonText);
    }
}
