package com.example.assignment2;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity{

    private TextView Response;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);

            Response = findViewById(R.id.costomerDetails);

            Bundle extras = getIntent().getExtras();
            if (extras != null){
                String response = "Details:\n";
                response += "Name: " + extras.getString("name") + "\n";
                response += "Email: " + extras.getString("email") + "\n";
                response += "Phone: " + extras.getString("phone") + "\n";
                response += "City: " + extras.getString("city") + "\n";
                response += "Address: " + extras.getString("address") + "\n";
                response += "Product: " + extras.getString("product") + "\n";
                response += "Custom Amount: " + extras.getInt("amount") + "\n";
                response += "Receive Offers: " + (extras.getBoolean("offers") ? "Yes" : "No") + "\n";
                response += "Shipping Method: " + extras.getString("shippingMethod") + "\n";
                response += "Payment Method: " + extras.getString("paymentMethod") + "\n";
                response += "App Rating: " + extras.getFloat("rating") + " stars\n";

                Response.setText(response);
            }
        }
    }
