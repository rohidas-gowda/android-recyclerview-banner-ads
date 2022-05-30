package com.andromojo.recyclerviewbannerads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> displayDataLists = new ArrayList<>();
    private Context context;

    private static final int company_data = 0;
    private static final int banner_ads_data = 1;

    public CompanyAdapter(List<Object> companyDetailsList, Context context) {
        this.displayDataLists = companyDetailsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case company_data:
                View companyData = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_details, parent, false);
                return new CompanyViewHolder(companyData);

            case banner_ads_data:
            default:
                View bannerAdsData = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_banner_ads, parent, false);
                return new BannerAdsViewHolder(bannerAdsData);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int companyViewType = getItemViewType(position);
        switch (companyViewType){
            case company_data:
                CompanyViewHolder companyViewHolder = (CompanyViewHolder) holder;
                CompanyDetails companyDetails = (CompanyDetails) displayDataLists.get(position);
                companyViewHolder.companyName.setText(companyDetails.getCompanyName());
                break;

            case banner_ads_data:
            default:
                BannerAdsViewHolder bannerAdsViewHolder = (BannerAdsViewHolder) holder;
                AdView adView = (AdView) displayDataLists.get(position);
                ViewGroup viewGroup = (ViewGroup) bannerAdsViewHolder.itemView;

                if (viewGroup.getChildCount() > 0){
                    viewGroup.removeAllViews();
                }
                if (viewGroup.getParent() != null){
                    ((ViewGroup)adView.getParent()).removeView(adView);
                }
                viewGroup.addView(adView);
        }
    }

    @Override
    public int getItemCount() {
        return displayDataLists.size();
    }

    public int getItemViewType(int position){
        if (position % MainActivity.ITEM_PER_AD==0){
            return banner_ads_data;
        } else {
            return company_data;
        }
    }

    public static class CompanyViewHolder extends RecyclerView.ViewHolder{
        TextView companyName;
        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);
            companyName = (TextView) itemView.findViewById(R.id.company_name);
        }
    }

    public static class BannerAdsViewHolder extends RecyclerView.ViewHolder{
        public BannerAdsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
