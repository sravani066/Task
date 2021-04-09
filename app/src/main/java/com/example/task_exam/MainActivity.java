package com.example.task_exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.task_exam.Adapter.MyAdapter;
import com.example.task_exam.model.Item;
import com.example.task_exam.utility.PreferenceUtils;
import com.example.task_exam.utility.Validate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText et_email;
    private EditText et_mobile;
    private Button btn_btnSubmit;
    private RecyclerView recyclerView;
    private  ArrayList<Item> usersList = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_email = findViewById(R.id.email);
        et_mobile = findViewById(R.id.mobile);
        btn_btnSubmit = findViewById(R.id.btnSubmit);
        recyclerView = findViewById(R.id.recyclerView);

        usersList = PreferenceUtils.getUsers(getApplicationContext());

        InitialiseAdapter();

        btn_btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter E-mail", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!Validate.isEmail(et_email.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please enter valid E-mail", Toast.LENGTH_SHORT).show();
                    return;
                } else if (et_mobile.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter mobile no", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!(et_mobile.getText().toString().length() > 6 && et_mobile.getText().toString().length() <=13 )) {
                    Toast.makeText(getApplicationContext(), "Please enter a correct mobile no", Toast.LENGTH_SHORT).show();
                    return;
                }
                String email = et_email.getText().toString();
                String mobile = et_mobile.getText().toString();
                PreferenceUtils.saveUserData(getApplicationContext(), email, mobile);

                Item item = new Item();
                item.setEmail(email);
                item.setMobile(mobile);

                usersList.add(item);
                PreferenceUtils.saveUsers(getApplicationContext(),usersList);
                InitialiseAdapter();
                //adapter.addAll(usersList);

            }
        });
    }

    public void InitialiseAdapter(){
        adapter = new MyAdapter(this,usersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}