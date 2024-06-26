package com.example.tam_ro.nativeDisplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.displayunits.DisplayUnitListener;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnitContent;
import com.example.tam_ro.MyApplication;
import com.example.tam_ro.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Map;

public class NativeDisplayActivity extends AppCompatActivity implements DisplayUnitListener {

    private String TAG = NativeDisplayActivity.class.getSimpleName();
    FragmentCommunicator mListener;
    NativeTabsAdapter tabsAdapter;
    ViewPager2 viewPager;
    CleverTapAPI cleverTapAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_display);
        NativeDisplayActivity.this.setTitle("Native Display");
        cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.getDefaultInstance(this).setDisplayUnitListener(this);

        viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        tabsAdapter = new NativeTabsAdapter(this);
        viewPager.setAdapter(tabsAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position) {
                            default:
                            case 0:
                                tab.setText("Image/Text");
                                break;
                            case 1:
                                tab.setText("Empty");
                                break;
                            case 2:
                                tab.setText("Banner");
                                break;
                            case 3:
                                tab.setText("Carousel");
                                break;
                        }
                    }
                }).attach();
    }

    public void setFragmentListener(FragmentCommunicator fragmentCommunicator){
        mListener = fragmentCommunicator;
    }

    @Override
    public void onDisplayUnitsLoaded(ArrayList<CleverTapDisplayUnit> units) {
        Log.d(TAG, "onDisplayUnitsLoaded() called with: units = [" + units.toString() + "]");
        mListener.loadData(units);
    }
}
