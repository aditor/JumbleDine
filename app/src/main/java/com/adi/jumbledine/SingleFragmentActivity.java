package com.adi.jumbledine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Adi on 2016-12-27.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {

    //blank method that is overridden in other activities
    protected abstract Fragment createFragment();

    //NOTE: fragmentmanager walks the FRAGMENT up to where the ACTIVITY is in its life cycle

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        //this fragmentmanager handles a LIST of fragments and adding their views to activity list hierarchy
        FragmentManager fm = getSupportFragmentManager();
        //If you add multiple fragments to an activity, each would have separate containers and IDs
        //that is why the below line finds fragment using LAYOUT container ID - it has only one fragment in it
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            //transactions add/remove etc. transactions to the LIST
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

}
