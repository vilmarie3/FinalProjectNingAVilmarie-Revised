package com.example.android.finalproject_ningavimarie;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        st=(TextView)findViewById(R.id.start);
        st.setOnClickListener(this);
        //System.out.println(Data.getA());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start:
                startActivity(new Intent(this,Reservation.class));
        }

    }
}