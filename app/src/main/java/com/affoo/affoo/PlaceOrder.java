package com.affoo.affoo;

import android.app.ProgressDialog;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import GlobalData.GlobalData;
import Model.Order;

public class PlaceOrder extends AppCompatActivity {

    public static String TAG = PlaceOrder.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mWaitDialog;
    private DatabaseReference mDatabase;
    private String userId = null;

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
        userId = GlobalData.getGlobalInstance().getUserId();
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
        order.setOrderStatus(GlobalData.orderStatus.CURRENT.toString());
        order.setOrderTime(Long.toString(System.currentTimeMillis()));
        order.setOrderAmount("1000");
        HashMap items = new HashMap();
        items.put("papaya","500");
        items.put("apple","500");
        order.setOrderItems(items);
        order.setUserId(userId);

        uploadOrder(order);
    }

    private void uploadOrder(Order order) {
       // mDatabase.child("orders").child(userId).setValue(order);
        if(userId != null){
            //Map<String, Object> postValues = order.toMap();

            String key = mDatabase.child("orders").push().getKey();

            //Map<String, Object> childUpdates = new HashMap<>();

            //childUpdates.put("/orders/" + key, postValues);
            //childUpdates.put("/user-orders/" + userId + "/" + key, postValues);

            //mDatabase.updateChildren(childUpdates);

            mDatabase.child("orders").child(key).setValue(order);
            mDatabase.child("user_orders").child(userId).child(key).setValue(order, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if(databaseError != null){
                        Toast.makeText(getApplication(),databaseError.toString(),Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplication(),"order placed successfully",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            });

        }else {
            Toast.makeText(this,"Invalid User",Toast.LENGTH_LONG).show();
        }

    }

    private void initWaitDialog() {
        mWaitDialog = new ProgressDialog(this);
        mWaitDialog.setCancelable(false);
    }
}
