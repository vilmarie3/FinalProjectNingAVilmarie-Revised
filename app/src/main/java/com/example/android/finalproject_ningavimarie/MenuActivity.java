package com.example.android.finalproject_ningavimarie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private List<MenuItems> menuItems;

    private RadioButton radioTimeButtonTen;
    private RadioButton radioTimeButtonThirty;
    private Button confirmMenuButton;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initialData();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MenuAdapter(menuItems, this));

        //radioOptionGroup = findViewById(R.id.order_food_radio_grp);
        //final String selectedRadioValue = ((RadioButton) findViewById(radioOptionGroup.getCheckedRadioButtonId())).getText().toString();

        confirmMenuButton = findViewById(R.id.confirm_button);
        radioTimeButtonTen = findViewById(R.id.ten_minutes_radioB);
        radioTimeButtonThirty = findViewById(R.id.thirty_minutes_radioB);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //String uid = user.getUid();

        confirmMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioTimeButtonTen.isChecked()) {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Order obj = new Order();
                    HashMap<String, String> dataMap = obj.firebaseMap();
                    dataMap.put("Time Food", "Your food will be ready 10 minutes into the reservation");

                    mDatabase.child("Reservation").child("Time Food").setValue("Your food will be ready 10 minutes into the reservation");

                } else {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Order obj = new Order();
                    HashMap<String, String> dataMap = obj.firebaseMap();
                    dataMap.put("Time Food", "Your food will be ready 30 minutes into the reservation");

                    mDatabase.child("Reservation").child("Time Food").setValue("Your food will be ready 30 minutes into the reservation");

                }

                Intent intent = new Intent(MenuActivity.this, CheckOut.class);
                startActivity(intent);


            }
        });


    }

    private void initialData() {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItems(R.string.spinach_artichoke_dip_title, R.string.spinach_and_artichoke_dip_description, R.drawable.spinach_artichoke_dip, R.string.$5));
        menuItems.add(new MenuItems(R.string.queso_dip_title, R.string.queso_dip_description, R.drawable.queso_dip, R.string.$5));
        menuItems.add(new MenuItems(R.string.fried_cheese_title, R.string.fried_cheese_description, R.drawable.queso_frito, R.string.$4));
        menuItems.add(new MenuItems(R.string.beer_cheese_spread_title, R.string.beer_cheese_spread_description, R.drawable.beer_cheese_spread, R.string.$6));
        menuItems.add(new MenuItems(R.string.mozzarella_sticks_title, R.string.mozzarella_sticks_description, R.drawable.mozzarella_sticks, R.string.$6));
        menuItems.add(new MenuItems(R.string.bacon_wrapped_shrimp_title, R.string.bacon_wrapped_shrimp_description, R.drawable.bacon_wrapped_shrimp, R.string.$8));
        menuItems.add(new MenuItems(R.string.garlic_bread_title, R.string.no_description, R.drawable.garlic_bread, R.string.$5));
        menuItems.add(new MenuItems(R.string.cheesy_garlic_bread_title, R.string.no_description, R.drawable.cheesy_garlic_bread, R.string.$6));
        menuItems.add(new MenuItems(R.string.blue_cheese_biscuits_title, R.string.blue_cheese_biscuits_description, R.drawable.blue_cheese_biscuit, R.string.$6));
        menuItems.add(new MenuItems(R.string.burger_one_title, R.string.burger_one_description, R.drawable.classic_burger, R.string.$10));
        menuItems.add(new MenuItems(R.string.burger_two_title, R.string.burger_two_description, R.drawable.guinness_burger, R.string.$15));
        menuItems.add(new MenuItems(R.string.burger_three_title, R.string.burger_three_description, R.drawable.salami_sun_dried_tomato, R.string.$14));
        menuItems.add(new MenuItems(R.string.burger_four_title, R.string.burger_four_description, R.drawable.bbq_pepperjack_jalapenos, R.string.$14));
        menuItems.add(new MenuItems(R.string.burger_five_title, R.string.burger_five_description, R.drawable.cheddar_avocado_sprouts, R.string.$12));
        menuItems.add(new MenuItems(R.string.burger_six_title, R.string.burger_six_description, R.drawable.roasted_pepper_manchego, R.string.$14));
        menuItems.add(new MenuItems(R.string.burger_seven_title, R.string.burger_seven_description, R.drawable.onion_dip_potato_chip, R.string.$14));
        menuItems.add(new MenuItems(R.string.burger_eight_title, R.string.burger_eight_description, R.drawable.brown_rice_beans, R.string.$13));
        menuItems.add(new MenuItems(R.string.chocolate_milkshake_title, R.string.no_description, R.drawable.chocolate_milkshake, R.string.$5));
        menuItems.add(new MenuItems(R.string.vanilla_milkshake_title, R.string.no_description, R.drawable.vanilla_milkshake, R.string.$5));
        menuItems.add(new MenuItems(R.string.strawberry_milkshake_title, R.string.no_description, R.drawable.strtawberry_milkshake, R.string.$5));
        menuItems.add(new MenuItems(R.string.cookie_milkshake, R.string.no_description, R.drawable.cookies_cream_milkshake, R.string.$5));
        menuItems.add(new MenuItems(R.string.chocolate_cake_title, R.string.no_description, R.drawable.chocolate_lava_cake, R.string.$6));
        menuItems.add(new MenuItems(R.string.tiramisu_title, R.string.no_description, R.drawable.tiramisu, R.string.$7));
        menuItems.add(new MenuItems(R.string.kahlua_cheesecake_title, R.string.no_description, R.drawable.kahlua_cheesecake, R.string.$7));
        menuItems.add(new MenuItems(R.string.classic_cheesecake_title, R.string.no_description, R.drawable.classic_cheesecake, R.string.$5));
        menuItems.add(new MenuItems(R.string.chocolate_cheesecake_title, R.string.no_description, R.drawable.chocolate_cheesecake, R.string.$6));
        menuItems.add(new MenuItems(R.string.marble_cheesecake_title, R.string.no_description, R.drawable.marble_cheesecake, R.string.$6));
        menuItems.add(new MenuItems(R.string.guava_cheese_title, R.string.no_description, R.drawable.guava_paste_and_cheese, R.string.$5));
        menuItems.add(new MenuItems(R.string.water_title, R.string.no_description, R.drawable.flat_water, R.string.$1));
        menuItems.add(new MenuItems(R.string.sparkling_water_title, R.string.no_description, R.drawable.sparkling_water, R.string.$2));
        menuItems.add(new MenuItems(R.string.tea_title, R.string.no_description, R.drawable.tea, R.string.$2));
        menuItems.add(new MenuItems(R.string.coffee_title, R.string.no_description, R.drawable.coffee, R.string.$2));
        menuItems.add(new MenuItems(R.string.cappucino_title, R.string.no_description, R.drawable.cappuccino, R.string.$3));
        menuItems.add(new MenuItems(R.string.coke_title, R.string.no_description, R.drawable.coke, R.string.$2));
        menuItems.add(new MenuItems(R.string.diet_coke_title, R.string.no_description, R.drawable.diet_coke, R.string.$2));
        menuItems.add(new MenuItems(R.string.lemonade_title, R.string.no_description, R.drawable.lemonade, R.string.$3));


    }

}
