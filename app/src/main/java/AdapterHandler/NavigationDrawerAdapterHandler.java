package AdapterHandler;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.affoo.affoo.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import Interfaces.DrawerItemClickListner;

/**
 * Created by Ramakant on 6/5/2016.
 */
public class NavigationDrawerAdapterHandler implements DrawerItemClickListner {

    private FirebaseAuth mAuth;
    @Override
    public void drawerItemClick(Context context, int position) {
        if(position == 5){
            mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            Intent intent = new Intent(context.getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
        Toast.makeText(context,Integer.toString(position),Toast.LENGTH_LONG).show();
    }
}
