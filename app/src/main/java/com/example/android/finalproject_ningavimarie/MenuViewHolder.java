package com.example.android.finalproject_ningavimarie;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuViewHolder extends RecyclerView.ViewHolder {

    public CardView cardView;
    public TextView menuItemTitle;
    public TextView menuItemPrice;
    public TextView menuItemDescription;
    public ImageView menuItemPicture;


    public MenuViewHolder(final View itemView, final Context context) {
        super(itemView);

        cardView = itemView.findViewById(R.id.card_view);
        menuItemTitle = itemView.findViewById(R.id.menu_item_name);
        menuItemPrice = itemView.findViewById(R.id.menu_item_price);
        menuItemDescription = itemView.findViewById(R.id.menu_item_description);
        menuItemPicture = itemView.findViewById(R.id.menu_item_picture);


    }


}
