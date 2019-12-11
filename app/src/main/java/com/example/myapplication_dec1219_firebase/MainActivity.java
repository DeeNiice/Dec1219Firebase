package com.example.myapplication_dec1219_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference androidRef;

    EditText etId, etCode, etDate, etApi;
    List<AndroidVersion> list;
    int i; // ANO TOH

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         db = FirebaseDatabase.getInstance();
         androidRef = db.getReference("AndroidVer");
        //androidRef.setValue("Hello World!");

         etId = findViewById(R.id.et1);
         etCode = findViewById(R.id.et2);
         etDate = findViewById(R.id.et3);
         etApi = findViewById(R.id.et4);
         list = new ArrayList<AndroidVersion>();


    }

    public void addRecord(View v){
       String id= androidRef.push().getKey();
        String cname= etCode.getText().toString();
        String date= etDate.getText().toString();
        String api= etApi.getText().toString();

        AndroidVersion version = new AndroidVersion(id,cname, date, api);
        androidRef.child(id).setValue(version);

    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        androidRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                for(DataSnapshot androidObj: ds.getChildren() ){
                    AndroidVersion av = androidObj.getValue(AndroidVersion.class);
                    list.add(av);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void moveFirst(View v){
        //AndroidVersion firstObj = list.get(0);
        etId.setText(list.get(0).getId());
        etCode.setText(list.get(0).getcName());
        etDate.setText(list.get(0).getrDate());
        etApi.setText(list.get(0).getApiLevel());

    }

    public void movePrevious(View v){
        etId.setText(list.get(--i).getcName());
    }

    public void moveNext(View v){
        etId.setText(list.get(i).getcName());
    }

    public void editRecord(View v){
        String id = etId.getText().toString();
        String name = etCode.getText().toString();
        String date = etDate.getText().toString();
        String api = etApi.getText().toString();

        AndroidVersion av = new AndroidVersion(id,name,date,api);
        androidRef.child(id).setValue(av);
    }

    public void removeRecord(View v){
        String id = etId.getText().toString();
        androidRef.child(id).removeValue();

    }
}
