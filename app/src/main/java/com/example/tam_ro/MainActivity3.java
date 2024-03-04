package com.example.tam_ro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.clevertap.android.pushtemplates.PushTemplateNotificationHandler;
import com.clevertap.android.pushtemplates.TemplateRenderer;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.interfaces.NotificationHandler;

public class MainActivity3 extends AppCompatActivity {
    private CleverTapAPI clevertapDefaultInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        clevertapDefaultInstance=CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setNotificationHandler((NotificationHandler)new PushTemplateNotificationHandler());


        //to get the logs for push notification
        TemplateRenderer.setDebugLevel(3);

        Button btnBasic,btnAutoCar,btnManualCar,btnRating,btnProductCatalog,btnFiveIcons,btnTimer,btnZeroBezel,btnInputBox;
        btnBasic = findViewById(R.id.btnBasicPush);
        btnAutoCar = findViewById(R.id.btnAutoCar);
        btnManualCar = findViewById(R.id.btnManualCar);
        btnRating = findViewById(R.id.btnRatingTemp);
        btnProductCatalog = findViewById(R.id.btnProductCat);
        btnFiveIcons = findViewById(R.id.btnFiveIcons);
        btnTimer = findViewById(R.id.btnTimerTemplate);
        btnZeroBezel = findViewById(R.id.btnZeroBezel);
        btnInputBox = findViewById(R.id.btnInputBox);

        btnBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clevertapDefaultInstance.pushEvent("Basic Template");
            }
        });

        btnAutoCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clevertapDefaultInstance.pushEvent("Auto Carousel");
            }
        });

        btnManualCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clevertapDefaultInstance.pushEvent("Manual Carousel");
            }
        });

        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clevertapDefaultInstance.pushEvent("Rating Template");
            }
        });

        btnProductCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clevertapDefaultInstance.pushEvent("Product Catalog");
            }
        });

        btnFiveIcons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clevertapDefaultInstance.pushEvent("Five Icons");
            }
        });

        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clevertapDefaultInstance.pushEvent("Timer Template");
            }
        });

        btnZeroBezel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clevertapDefaultInstance.pushEvent("Zero Bezel");
            }
        });

        btnInputBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clevertapDefaultInstance.pushEvent("Input Box");
            }
        });

    }
}