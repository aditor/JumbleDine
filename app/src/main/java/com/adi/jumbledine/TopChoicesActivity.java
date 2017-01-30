package com.adi.jumbledine;

import android.support.v4.app.Fragment;

/**
 * Created by Adi on 2017-01-29.
 */
public class TopChoicesActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return TopChoicesFragment.newInstance();
    }


}
