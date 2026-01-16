package com.example.listyishcity;

import android.os.Bundle;
import android.view.View;
//import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> dataList;
    City new_city;
    Button delButton;
    Button addButton;
    Button confirmButton;
    int idx_to_delete;
    EditText input ;
    TextInputLayout textBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.add_button);
        delButton  = findViewById(R.id.del_button);
        textBox = findViewById(R.id.textInputLayout);
        input = findViewById(R.id.enter_text);
        cityList = findViewById(R.id.city_list);
        confirmButton = findViewById(R.id.confirm_button);
        idx_to_delete=-1;
        new_city = null;
//        set this element to be invisible on create
        input.setVisibility(View.INVISIBLE);
        confirmButton.setVisibility(View.INVISIBLE);
        textBox.setVisibility(View.INVISIBLE);
//        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
//        dataList.addAll(Arrays.asList(cities));
        //a list of cities to start with initially
        dataList.add(new City("Edmonton"));
        dataList.add(new City("Toronto"));
        dataList.add(new City("Dubai"));
        dataList.add(new City("Miami"));
        dataList.add(new City("Paris"));
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);



        cityList.setAdapter(cityAdapter);
//        cityList.setOnItemClickListener((parent, view, position, id) -> idx_to_delete = position);
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            idx_to_delete = position;
            String new_button_text = "Delete " + dataList.get(idx_to_delete).getName();
            delButton.setText(new_button_text);
        });

    }
    public void save_input(View view){
        String input_text = input.getText().toString().trim();
        if (!input_text.isEmpty()){
            new_city= new City(input_text);
            input.getText().clear(); // clear the text box after writing
//            String new_button_text = "Add " + input_text; //i had the order of the button usage wrong
//            addButton.setText(new_button_text);
            if (new_city != null) {
                dataList.add(new_city);
                cityAdapter.notifyDataSetChanged();
                new_city = null;
                input.setVisibility(View.INVISIBLE);
                textBox.setVisibility(View.INVISIBLE);
                confirmButton.setVisibility(View.INVISIBLE);
//            addButton.setText(R.string.add_city_button);
            }

        }
    }
    public void add_city(View view){ // forgot to set new_city back to null
//        if (new_city != null) {
//            dataList.add(new_city);
//            cityAdapter.notifyDataSetChanged();
//            new_city = null;
//            addButton.setText(R.string.add_city_button);
//        }
        input.setVisibility(View.VISIBLE);
        confirmButton.setVisibility(View.VISIBLE);
        textBox.setVisibility(View.VISIBLE);

    }
    public void delete_city(View view){ // this was causing crashes for me before. I fixed it by checking idx==-1 and notifying the dataset
        if (idx_to_delete!=-1){
            dataList.remove(idx_to_delete);
            cityAdapter.notifyDataSetChanged();//bug: was missing this line
            delButton.setText(R.string.del_city_button);
            idx_to_delete=-1;
        }
    }
}