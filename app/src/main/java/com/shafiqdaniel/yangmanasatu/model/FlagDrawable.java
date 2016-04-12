package com.shafiqdaniel.yangmanasatu.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import com.shafiqdaniel.yangmanasatu.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by shafiq on 11/04/16.
 */
public class FlagDrawable {
    private HashMap<String,Drawable> stringDrawableHashMap;
    private Context context;

    public FlagDrawable(Context context){
        this.context = context;
        stringDrawableHashMap = new HashMap<>();
        setUpFlags();
    }

    private void setUpFlags() {
        stringDrawableHashMap = new HashMap<String, Drawable>()
        {{
            put(context.getString(R.string.perak),
                    ResourcesCompat.getDrawable(context.getResources(), R.drawable.perak_flag, null));
            put(context.getString(R.string.perlis),
                    ResourcesCompat.getDrawable(context.getResources(), R.drawable.perlis_flag, null));
            put(context.getString(R.string.johor),
                    ResourcesCompat.getDrawable(context.getResources(), R.drawable.johor_flag, null));
            put(context.getString(R.string.kedah),
                    ResourcesCompat.getDrawable(context.getResources(), R.drawable.kedah_flag, null));
            put(context.getString(R.string.kelantan),
                    ResourcesCompat.getDrawable(context.getResources(), R.drawable.kelantan_flag, null));
            put(context.getString(R.string.melaka),
                    ResourcesCompat.getDrawable(context.getResources(), R.drawable.melaka_flag, null));
            put(context.getString(R.string.negerisembilan),
                    ResourcesCompat.getDrawable(context.getResources(), R.drawable.negerisembilan_flag, null));
            put(context.getString(R.string.pahang),
                    ResourcesCompat.getDrawable(context.getResources(), R.drawable.pahang_flag, null));
            put(context.getString(R.string.penang),
                    ResourcesCompat.getDrawable(context.getResources(), R.drawable.penang_flag, null));
            put(context.getString(R.string.sabah),
                    ResourcesCompat.getDrawable(context.getResources(), R.drawable.sabah_flag, null));
            put(context.getString(R.string.sarawak),
                    ResourcesCompat.getDrawable(context.getResources(), R.drawable.sarawak_flag, null));
            put(context.getString(R.string.selangor),
                    ResourcesCompat.getDrawable(context.getResources(), R.drawable.selangor_flag, null));
            put(context.getString(R.string.terengganu),
                    ResourcesCompat.getDrawable(context.getResources(), R.drawable.terengganu_flag, null));
        }};
    }

    public Drawable getFlag(String key) {
        return stringDrawableHashMap.get(key);
    }
}
