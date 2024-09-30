package com.example.assignment2;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CheckActivity extends AppCompatActivity{

    private RadioGroup radioGroupShippingMethod;
    private CheckBox cbCashOnDelivery, cbBkash, cbRocket, cbNagad, cbVisaMastercard;
    private RatingBar ratingBar;
    private Button btnSubmitOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        radioGroupShippingMethod = findViewById(R.id.radioGroupShippingMethod);
        cbCashOnDelivery = findViewById(R.id.cbCashOnDelivery);
        cbBkash = findViewById(R.id.cbBkash);
        cbRocket = findViewById(R.id.cbRocket);
        cbNagad = findViewById(R.id.cbNagad);
        cbVisaMastercard = findViewById(R.id.cbVisaMastercard);
        ratingBar = findViewById(R.id.ratingBar);
        btnSubmitOrder = findViewById(R.id.btnSubmitOrder);

        cbCashOnDelivery.setOnCheckedChangeListener((buttonView, isChecked)->{
            if(isChecked) uncheckOthers(cbCashOnDelivery);
        });
        cbBkash.setOnCheckedChangeListener((buttonView, isChecked)->{
            if(isChecked) uncheckOthers(cbBkash);
        });
        cbRocket.setOnCheckedChangeListener((buttonView, isChecked)->{
            if(isChecked) uncheckOthers(cbRocket);
        });
        cbNagad.setOnCheckedChangeListener((buttonView, isChecked)->{
            if(isChecked) uncheckOthers(cbNagad);
        });
        cbVisaMastercard.setOnCheckedChangeListener((buttonView, isChecked)->{
            if(isChecked) uncheckOthers(cbVisaMastercard);
        });

        btnSubmitOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int selectedShippingMethodId = radioGroupShippingMethod.getCheckedRadioButtonId();

                if(selectedShippingMethodId == -1){
                    Toast.makeText(CheckActivity.this, "Please select a shipping method", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!cbCashOnDelivery.isChecked() && !cbBkash.isChecked() && !cbRocket.isChecked() && !cbNagad.isChecked() && !cbVisaMastercard.isChecked()){
                    Toast.makeText(CheckActivity.this, "Please select a payment method", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedShippingMethod = findViewById(selectedShippingMethodId);
                String selectedPaymentMethod = getSelectedPaymentMethod();
                float rating = ratingBar.getRating();

                Intent intent = new Intent(CheckActivity.this, ResultActivity.class);
                intent.putExtras(getIntent());
                intent.putExtra("shippingMethod", selectedShippingMethod.getText().toString());
                intent.putExtra("paymentMethod", selectedPaymentMethod);
                intent.putExtra("rating", rating);
                startActivity(intent);
            }
        });
    }

    private void uncheckOthers(CheckBox selected){
        if(selected != cbCashOnDelivery) cbCashOnDelivery.setChecked(false);
        if(selected != cbBkash) cbBkash.setChecked(false);
        if(selected != cbRocket) cbRocket.setChecked(false);
        if(selected != cbNagad) cbNagad.setChecked(false);
        if(selected != cbVisaMastercard) cbVisaMastercard.setChecked(false);
    }

    private String getSelectedPaymentMethod(){
        if(cbCashOnDelivery.isChecked()) return "Cash on Delivery";
        if(cbBkash.isChecked()) return "bKash";
        if(cbRocket.isChecked()) return "Rocket";
        if(cbNagad.isChecked()) return "Nagad";
        if(cbVisaMastercard.isChecked()) return "VISA/MasterCard";
        return "";
    }
}
