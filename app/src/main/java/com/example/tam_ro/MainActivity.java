package com.example.tam_ro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.clevertap.android.pushtemplates.TemplateRenderer;
import com.clevertap.android.sdk.CTInboxListener;
import com.clevertap.android.sdk.CTInboxStyleConfig;
import com.clevertap.android.sdk.CleverTapAPI;
import com.example.tam_ro.nativeDisplay.NativeDisplayActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements CTInboxListener{
    EditText txtname,txtemail,txtaddr,txtcontact,txtId,txtGender,txtDOB;
    Button btnRegister,btnProfilePush,btnEvents,btnPushTemplates,btnAppInbox,btnNativeDisplay;
    CleverTapAPI clevertapDefaultInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE);//Set Log level to VERBOSE
        TemplateRenderer.setDebugLevel(3);

        clevertapDefaultInstance.setCTNotificationInboxListener(this);
        //Initialize the inbox and wait for callbacks on overridden methods
        clevertapDefaultInstance.initializeInbox();

        //CleverTapAPI.createNotificationChannel(getApplicationContext(),"CleverTap-TAM","CleverTap-TAM","Your Channel Description",NotificationManager.IMPORTANCE_MAX,true);


        btnRegister=findViewById(R.id.btnRegister);
        btnProfilePush=findViewById(R.id.btnProfilePush);
        btnEvents=findViewById(R.id.btnEvents);
        btnPushTemplates=findViewById(R.id.btnPushTemplates);
        btnAppInbox=findViewById(R.id.btnAppInbox);
        btnNativeDisplay=findViewById(R.id.btnNativeDisplay);

        txtId=findViewById(R.id.txtIdentity);
        txtGender=findViewById(R.id.txtGender);
        txtname=findViewById(R.id.txtName);
        txtcontact=findViewById(R.id.txtContact);
        txtemail=findViewById(R.id.txtEmail);
        txtaddr=findViewById(R.id.txtLocation);
        txtDOB=findViewById(R.id.txtDOB);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,email,contact,addr,dob;

                name=txtname.getText().toString();
                email=txtemail.getText().toString();
                contact=txtcontact.getText().toString();
                addr=txtaddr.getText().toString();
                dob=txtDOB.getText().toString();

                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
                profileUpdate.put("Name", name);    // String
                profileUpdate.put("Email", email); // Email address of the user
                profileUpdate.put("Phone", contact);   // Phone (with the country code, starting with +)
                profileUpdate.put("Address",addr);
                profileUpdate.put("DOB", new Date(dob));         // Date of Birth. Set the Date object to the appropriate value first

                profileUpdate.put("MSG-email", false);        // Disable email notifications
                profileUpdate.put("MSG-push", true);          // Enable push notifications
                profileUpdate.put("MSG-sms", false);          // Disable SMS notifications
                profileUpdate.put("MSG-whatsapp", true);      // Enable WhatsApp notifications

                clevertapDefaultInstance.onUserLogin(profileUpdate);
                Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
            }
        });

        btnProfilePush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id,gender;

                id=txtId.getText().toString();
                gender=txtGender.getText().toString();

                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();

                profileUpdate.put("Identity", id);                    // String or number
                profileUpdate.put("Gender", gender);                           // Can be either M or F

                clevertapDefaultInstance.pushProfile(profileUpdate);
                Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
            }
        });

        btnEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i1);
            }
        });

        btnPushTemplates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(MainActivity.this,MainActivity3.class);
                startActivity(i2);
            }
        });

        btnNativeDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this, NativeDisplayActivity.class);
                startActivity(i1);
            }
        });

    }

    @Override
    public void inboxDidInitialize() {
        btnAppInbox.setOnClickListener(v -> {
            ArrayList<String> tabs = new ArrayList<>();
            tabs.add("Promotions");
            tabs.add("Offers");//We support upto 2 tabs only. Additional tabs will be ignored

            CTInboxStyleConfig styleConfig = new CTInboxStyleConfig();
            styleConfig.setFirstTabTitle("First Tab");
            styleConfig.setTabs(tabs);//Do not use this if you don't want to use tabs
            styleConfig.setTabBackgroundColor("#FF0000");
            styleConfig.setSelectedTabIndicatorColor("#0000FF");
            styleConfig.setSelectedTabColor("#0000FF");
            styleConfig.setUnselectedTabColor("#FFFFFF");
            styleConfig.setBackButtonColor("#FF0000");
            styleConfig.setNavBarTitleColor("#FF0000");
            styleConfig.setNavBarTitle("MY INBOX");
            styleConfig.setNavBarColor("#FFFFFF");
            styleConfig.setInboxBackgroundColor("#ADD8E6");
            if (clevertapDefaultInstance != null) {
                clevertapDefaultInstance.showAppInbox(styleConfig); //With Tabs
            }
            //ct.showAppInbox();//Opens Activity with default style configs
        });
    }

    @Override
    public void inboxMessagesDidUpdate() {

    }
}
