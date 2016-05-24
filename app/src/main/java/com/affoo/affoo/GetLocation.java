package com.affoo.affoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ramakant on 5/22/2016.
 */
public class GetLocation extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = GetLocation.class.getSimpleName();
    private TextView letsGetStarted;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_customer_location);
        letsGetStarted = (TextView)findViewById(R.id.lets_get_started);
        letsGetStarted.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.lets_get_started:
                Toast.makeText(this,"Click",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,NavigationDrawer.class);
                startActivity(intent);
                break;
        }
    }
}
