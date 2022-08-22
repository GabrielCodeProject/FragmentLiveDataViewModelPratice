package com.app.fragmentlivedataviewmodelpratice;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;

public class CustomClickListener implements View.OnClickListener {
    private static final String TAG = "CustomListener";

    FragmentManager fragmentManager;

    public CustomClickListener(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: CustomListenerCall view tag " + view.getTag());
        switch (view.getTag().toString()) {
            case "btn_frag_c":
                Log.d(TAG, "onClick: btn C clicker");
                break;
            case "btn_frag_d":
                Log.d(TAG, "onClick: btn D clicker");
                break;
            case "Back":
                Log.d(TAG, "onClick: back clicker");
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    Log.d(TAG, "onClick: backstack count " + fragmentManager.getBackStackEntryCount());
                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(i);
                        String tag = backStackEntry.getName();
                        Log.d(TAG, "onClick: backStack entry tag " + tag);
                    }
                    Button btn = view.findViewById(R.id.btn_back_main);
                    Log.d(TAG, "onClick: btn name " + btn.getText());
                    fragmentManager.popBackStack();
                }
                break;
            default:
                Log.d(TAG, "onClick: something click");
        }
    }
}
