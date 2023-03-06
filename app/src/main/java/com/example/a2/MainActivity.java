package com.example.a2;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,Title,Category;
    Button insert;
    Button view;
    DB ddb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name =(EditText)findViewById(R.id.editTextTextPersonName);
        Title =(EditText)findViewById(R.id.editTextTextPersonName2);
        Category =(EditText)findViewById(R.id.editTextTextPersonName3);
        insert=(Button)findViewById(R.id.button);
        view=(Button)findViewById(R.id.button2);
        ddb=new DB(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameT=name.getText().toString();
                String TitleT=Title.getText().toString();
                String  CategoryT=Category.getText().toString();
                Boolean check=ddb.insert(nameT,TitleT,CategoryT);
                if(check==true)
                    Toast.makeText(MainActivity.this, "New Entry Insertted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry not Insertted", Toast.LENGTH_SHORT).show();

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=ddb.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "no Entry ", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append(" 🍂:"+res.getString(1)+"\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Your selected book/s");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}
