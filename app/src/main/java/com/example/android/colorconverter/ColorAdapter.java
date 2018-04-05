package com.example.android.colorconverter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by guidovdriet on 04-04-18.
 */

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {
    private Context mContext;
    private ArrayList<ColorValueCard> mColorValueCardsList;

    ColorAdapter(Context context, ArrayList<ColorValueCard> mColorValueCardsList) {
        mContext = context;
        this.mColorValueCardsList = mColorValueCardsList;
    }

    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.color_value_list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View colorView = inflater.inflate(layoutIdForListItem, parent, false);
        return new ColorViewHolder(colorView);
    }

    @Override
    public void onBindViewHolder(ColorViewHolder holder, int position) {
        ColorValueCard currentCard = mColorValueCardsList.get(position);
        String colorName = currentCard.getName();
        String colorHex = currentCard.getHex();
        String colorCmyk = currentCard.getCmyk();
        String colorRgb = currentCard.getRgb();

        holder.itemView.setBackgroundColor(Color.parseColor(colorHex));
        holder.mTextViewName.setText(colorName);
        holder.mTextViewHex.setText(colorHex);
        holder.mTextViewCmyk.setText(colorCmyk);
        holder.mTextViewRgb.setText(colorRgb);
    }

    @Override
    public int getItemCount() {
        return mColorValueCardsList.size();
    }

    class ColorViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewName;
        TextView mTextViewHex;
        TextView mTextViewCmyk;
        TextView mTextViewRgb;

        ColorViewHolder(View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.tv_card_color_name);
            mTextViewHex = itemView.findViewById(R.id.tv_card_color_hex);
            mTextViewCmyk = itemView.findViewById(R.id.tv_card_color_cmyk);
            mTextViewRgb = itemView.findViewById(R.id.tv_card_color_rgb);
        }
    }
}
