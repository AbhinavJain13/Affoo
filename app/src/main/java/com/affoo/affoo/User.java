package com.affoo.affoo;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Ramakant on 6/13/2016.
 */
@IgnoreExtraProperties
public class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
