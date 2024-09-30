package com.example.assignment2;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class MainActivity extends AppCompatActivity{

        private EditText etName, etEmail, etPhone, etAddress;
        private Spinner spinnerCity, spinnerProduct;
        private SeekBar seekBarAmount;
        private TextView SeekBarProgress;
        private Switch switchOffers;
        private Button btnNext;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            etName = findViewById(R.id.etName);
            etEmail = findViewById(R.id.etEmail);
            etPhone = findViewById(R.id.etPhone);
            etAddress = findViewById(R.id.etAddress);
            spinnerCity = findViewById(R.id.spinnerCity);
            spinnerProduct = findViewById(R.id.spinnerProduct);
            seekBarAmount = findViewById(R.id.seekBarAmount);
            SeekBarProgress = findViewById(R.id.tvSeekBarProgress);
            switchOffers = findViewById(R.id.switchOffers);
            btnNext = findViewById(R.id.btnNext);

            String[] cities = {"Select a city", "Sylhet", "Barishal", "Chattogram", "Dhaka", "Khulna", "Rajshahi", "Rangpur", "Mymensingh"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCity.setAdapter(adapter);

            String[] products = {"Select a product", "Pen", "Pencil", "Eraser", "Exercise Book", "Book"};
            ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, products);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerProduct.setAdapter(adapter2);


            seekBarAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                    SeekBarProgress.setText("Amount: " + progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar){}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar){}
            });

            btnNext.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(validateInputs()){
                        String name = etName.getText().toString().trim();
                        String email = etEmail.getText().toString().trim();
                        String phone = etPhone.getText().toString().trim();
                        String address = etAddress.getText().toString().trim();
                        String city = spinnerCity.getSelectedItem().toString();
                        String product = spinnerProduct.getSelectedItem().toString();
                        int customAmount = seekBarAmount.getProgress();
                        boolean receiveOffers = switchOffers.isChecked();

                        Intent intent = new Intent(MainActivity.this, CheckActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("email", email);
                        intent.putExtra("phone", phone);
                        intent.putExtra("address", address);
                        intent.putExtra("city", city);
                        intent.putExtra("product", product);
                        intent.putExtra("amount", customAmount);
                        intent.putExtra("offers", receiveOffers);
                        startActivity(intent);
                    }
                }
            });
        }

        private boolean validateInputs(){
            String nameRegex = "^[a-zA-Z\\s]+$";
            if(TextUtils.isEmpty(etName.getText()) || !etName.getText().toString().matches(nameRegex)){
                etName.setError("Please enter a valid name");
                return false;
            }

            if(TextUtils.isEmpty(etEmail.getText()) || !Patterns.EMAIL_ADDRESS.matcher(etEmail.getText()).matches()){
                etEmail.setError("Please enter a valid email");
                return false;
            }

            String phoneRegex = "^(01[3-9]\\d{8})$";
            if(TextUtils.isEmpty(etPhone.getText()) || !etPhone.getText().toString().matches(phoneRegex)){
                etPhone.setError("Please enter a valid phone number");
                return false;
            }

            if(spinnerCity.getSelectedItem().toString().equals("Select a city")){
                Toast.makeText(this, "Please select a city", Toast.LENGTH_SHORT).show();
                return false;
            }

            if(TextUtils.isEmpty(etAddress.getText())){
                etAddress.setError("Please enter your address");
                return false;
            }

            if(spinnerProduct.getSelectedItem().toString().equals("Select a product")){
                Toast.makeText(this, "Please select a product", Toast.LENGTH_SHORT).show();
                return false;
            }

            return true;
        }
}
