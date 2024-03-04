package com.example.tam_ro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.clevertap.android.sdk.CleverTapAPI;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE);

        Button btnProductViewed,btnProductAddedtoCart;

        btnProductViewed=findViewById(R.id.btnProductViewed);
        btnProductAddedtoCart=findViewById(R.id.btnProductAddedtoCart);

        btnProductViewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clevertapDefaultInstance.pushEvent("Product viewed");
                Toast.makeText(getApplicationContext(),"Product Viewed",Toast.LENGTH_SHORT).show();
            }
        });

        btnProductAddedtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clevertapDefaultInstance.pushEvent("Product Added to Cart");
                Toast.makeText(getApplicationContext(),"Product Added to Cart",Toast.LENGTH_SHORT).show();
            }
        });

    }
}