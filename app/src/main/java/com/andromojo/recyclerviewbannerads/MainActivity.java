package com.andromojo.recyclerviewbannerads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ITEM_PER_AD = 4;
    private static final String BANNER_AD_ID = "ca-app-pub-3940256099942544/6300978111";
    private List<Object> displayDataLists = new ArrayList<>();

    RecyclerView recyclerView;
    CompanyAdapter companyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getCompanyData();
        getBannerAdsData();
        loadBannerAds();

        companyAdapter = new CompanyAdapter(displayDataLists, this);
        recyclerView.setAdapter(companyAdapter);
    }

    public void getCompanyData(){
        CompanyDetails companyDetails1 = new CompanyDetails();
        companyDetails1.setCompanyName("Apple");
        displayDataLists.add(companyDetails1);

        CompanyDetails companyDetails2 = new CompanyDetails();
        companyDetails2.setCompanyName("Microsoft");
        displayDataLists.add(companyDetails2);

        CompanyDetails companyDetails3 = new CompanyDetails();
        companyDetails3.setCompanyName("Google");
        displayDataLists.add(companyDetails3);

        CompanyDetails companyDetails4 = new CompanyDetails();
        companyDetails4.setCompanyName("Amazon");
        displayDataLists.add(companyDetails4);

        CompanyDetails companyDetails5 = new CompanyDetails();
        companyDetails5.setCompanyName("HP");
        displayDataLists.add(companyDetails5);

        CompanyDetails companyDetails6 = new CompanyDetails();
        companyDetails6.setCompanyName("Dell");
        displayDataLists.add(companyDetails6);

        CompanyDetails companyDetails7 = new CompanyDetails();
        companyDetails7.setCompanyName("Tesla");
        displayDataLists.add(companyDetails7);

        CompanyDetails companyDetails8 = new CompanyDetails();
        companyDetails8.setCompanyName("Samsung");
        displayDataLists.add(companyDetails8);

        CompanyDetails companyDetails9 = new CompanyDetails();
        companyDetails9.setCompanyName("Sony");
        displayDataLists.add(companyDetails9);

        CompanyDetails companyDetails10 = new CompanyDetails();
        companyDetails10.setCompanyName("Intel");
        displayDataLists.add(companyDetails10);

        CompanyDetails companyDetails11 = new CompanyDetails();
        companyDetails11.setCompanyName("IBM");
        displayDataLists.add(companyDetails11);

        CompanyDetails companyDetails12 = new CompanyDetails();
        companyDetails12.setCompanyName("LG");
        displayDataLists.add(companyDetails12);

        CompanyDetails companyDetails13 = new CompanyDetails();
        companyDetails13.setCompanyName("Panasonic");
        displayDataLists.add(companyDetails13);

        CompanyDetails companyDetails14 = new CompanyDetails();
        companyDetails14.setCompanyName("Facebook");
        displayDataLists.add(companyDetails14);

        CompanyDetails companyDetails15 = new CompanyDetails();
        companyDetails15.setCompanyName("Cisco");
        displayDataLists.add(companyDetails15);

        CompanyDetails companyDetails16 = new CompanyDetails();
        companyDetails16.setCompanyName("Lenovo");
        displayDataLists.add(companyDetails16);
    }

    public void getBannerAdsData(){
        for (int i=0; i<displayDataLists.size(); i+=ITEM_PER_AD){
            AdView adView = new AdView(MainActivity.this);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(BANNER_AD_ID);
            displayDataLists.add(i, adView);
        }
    }

    public void loadBannerAds(){
        for(int i=0; i<displayDataLists.size();i++){
            Object item = displayDataLists.get(i);
            if(item instanceof AdView){
                final AdView adView = (AdView) item;
                adView.loadAd(new AdRequest.Builder().build());
            }
        }
    }
}