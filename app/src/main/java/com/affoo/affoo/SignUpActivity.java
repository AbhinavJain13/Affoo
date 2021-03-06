package com.affoo.affoo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import GlobalData.GlobalData;
import Model.User;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    public  static String TAG = SafeParcelable.class.getSimpleName();
    private EditText email;
    private EditText password;
    private EditText retype_password;
    private View sign_up_button;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mWaitDialog;
    private DatabaseReference mDatabase;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initWaitDialog();
        mAuth = FirebaseAuth.getInstance();
        initFireBaseListener();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        email = (EditText)findViewById(R.id.customer_email);
        password = (EditText)findViewById(R.id.password);
        retype_password = (EditText)findViewById(R.id.retype_password);
        sign_up_button = findViewById(R.id.signup_button);
        sign_up_button.setOnClickListener(this);
    }

    private void initWaitDialog() {
        mWaitDialog = new ProgressDialog(this);
        mWaitDialog.setCancelable(false);
    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }

    private void initFireBaseListener() {

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    GlobalData.getGlobalInstance().setUserId(user.getUid());
                    writeNewUser(user.getUid(),"default user",user.getEmail());
                    openNavigationDrawer();

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    private void openNavigationDrawer() {
        Intent intent = new Intent(this,NavigationDrawer.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup_button :
                createUserAccountUsingFireBase();
                break;
        }
    }

    private void createUserAccountUsingFireBase() {
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        String re_pass = retype_password.getText().toString();
        if(validateForm()){
            mWaitDialog.show();
            createUserWithEmailAndPassword(mAuth,mail,pass);
        }else {
            retype_password.requestFocus();
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String mail = email.getText().toString();
        if (TextUtils.isEmpty(mail)) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }

        String pass = password.getText().toString();
        if (TextUtils.isEmpty(pass)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        String re_pass = retype_password.getText().toString();
        if (TextUtils.isEmpty(re_pass)) {
            retype_password.setError("Required.");
            valid = false;
        } else {
            retype_password.setError(null);
        }

        if(!pass.equals(re_pass)){
            valid = false;
        }

        return valid;
    }

    private void createUserWithEmailAndPassword(FirebaseAuth mAuth, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(SignUpActivity.this, "Signup success.", Toast.LENGTH_SHORT).show();
                        }
                        if(mWaitDialog.isShowing()) mWaitDialog.cancel();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
