package com.example.android.finalproject_ningavimarie;


import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private List<MenuItems> menuItems;
    private Context context;
    Dialog myDialog;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();


    private ArrayList<MenuItems> menuOrder = new ArrayList<>();

    public DatabaseReference mDatabase;

    public MenuAdapter(List<MenuItems> menuItems, Context context) {
        this.menuItems = menuItems;
        this.context = context;
    }


    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_menu, parent, false);

        final MenuViewHolder holder = new MenuViewHolder(view, context);

        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.popup_window);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TextView dialogFoodName = myDialog.findViewById(R.id.dialog_name_text);
                ImageView dialogPic = myDialog.findViewById(R.id.pic_popup);
                dialogFoodName.setText(menuItems.get(holder.getAdapterPosition()).getTitle());
                dialogPic.setImageResource(menuItems.get(holder.getAdapterPosition()).getPicture());

                Button addToOrder = (Button) myDialog.findViewById(R.id.add_Item_button);

                Button removeFromOrder = myDialog.findViewById(R.id.remove_button);

                myDialog.show();

                addToOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        //final String uid = user.getUid();
                        String nameItem = dialogFoodName.getText().toString();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        Order obj = new Order();
                        HashMap<String, String> dataMap = obj.firebaseMap();

                        dataMap.put("Order", nameItem);


                        mDatabase.child("Reservation").child("Order").setValue(nameItem);

                        myDialog.dismiss();
                    }
                });

                removeFromOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDatabase = FirebaseDatabase.getInstance().getReference().child("Reservation").child("Order");
                        mDatabase.setValue("");
                        myDialog.dismiss();


                    }
                });
            }


        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {

        MenuItems menu = menuItems.get(position);
        holder.menuItemTitle.setText(menu.title);
        holder.menuItemPrice.setText(menu.price);
        holder.menuItemDescription.setText(menu.description);
        holder.menuItemPicture.setImageResource(menu.picture);

    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }


}
