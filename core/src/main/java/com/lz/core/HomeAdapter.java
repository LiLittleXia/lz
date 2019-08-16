package com.lz.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<User, HomeViewHolder> {

    public HomeAdapter(int layoutResId, @Nullable List<User> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull HomeViewHolder helper, User item) {

    }

}
