package AdapterHandler;

import android.content.Context;
import android.widget.Toast;

import Interfaces.DrawerItemClickListner;

/**
 * Created by Ramakant on 6/5/2016.
 */
public class NavigationDrawerAdapterHandler implements DrawerItemClickListner {
    @Override
    public void drawerItemClick(Context context, int position) {
        Toast.makeText(context,Integer.toString(position),Toast.LENGTH_LONG).show();
    }
}
