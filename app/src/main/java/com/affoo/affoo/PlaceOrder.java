package com.affoo.affoo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Order;

public class PlaceOrder extends AppCompatActivity {

    public static String TAG = PlaceOrder.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mWaitDialog;
    private DatabaseReference mDatabase;

    private EditText cusName;
    private EditText cusMobile;
    private EditText cusEmail;
    private EditText cusAddress;
    private Button btPlaceOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        initializeViews();
        initWaitDialog();

        //mAuth = FirebaseAuth.getInstance();
        //initFireBaseListener();
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    private void initializeViews() {
        cusName = (EditText) findViewById(R.id.customer_name);
        cusMobile = (EditText) findViewById(R.id.customer_mobile);
        cusEmail = (EditText) findViewById(R.id.customer_email);
        cusAddress = (EditText) findViewById(R.id.customer_address);
        btPlaceOrder = (Button) findViewById(R.id.place_order);
        btPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
    }

    private void placeOrder() {
        Order order = new Order();
        boolean valid = true;
        order.setCusName(cusName.getText().toString());
        order.setCusMobile(cusMobile.getText().toString());
        order.setCusEmail(cusEmail.getText().toString());
        order.setCusAddress(cusAddress.getText().toString());

        uploadOrder(order);
    }

    private void uploadOrder(Order order) {
        //mDatabase.child("users").child(userId).setValue(user);
    }

    private void initWaitDialog() {
        mWaitDialog = new ProgressDialog(this);
        mWaitDialog.setCancelable(false);
    }
}
