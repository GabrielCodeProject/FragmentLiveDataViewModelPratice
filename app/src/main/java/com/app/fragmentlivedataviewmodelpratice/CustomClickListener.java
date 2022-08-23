package com.app.fragmentlivedataviewmodelpratice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.fragmentlivedataviewmodelpratice.ui.main.FragmentA;

public class CustomClickListener implements View.OnClickListener {
    private static final String TAG = "CustomListener";

    FragmentManager fragmentManager;

    public CustomClickListener(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: CustomListenerCall view tag " + view.getTag());

        switch (view.getId()) {
            case R.id.btn_frag_a:
                Button btn = view.findViewById(R.id.btn_frag_a);
                Log.d(TAG, "onClick: btn A clicker " + btn.getText());
                Fragment fragment = new FragmentA();
                Bundle args = new Bundle();
                args.putString("btn_value", view.getTag().toString() + " " + btn.getText());
                fragment.setArguments(args);
                setTransaction(FragmentA.TAG, fragment);
                break;
            case R.id.btn_frag_b:
                btn = view.findViewById(R.id.btn_frag_b);
                Log.d(TAG, "onClick: btn B clicker " + btn.getText());
                fragment = new FragmentA();
                args = new Bundle();
                args.putString("btn_value", view.getTag().toString() + " " + btn.getText());
                args.putBoolean("is_working", true);
                fragment.setArguments(args);
                setTransaction(FragmentA.TAG, fragment);
                break;
            case R.id.btn_frag_c:
                btn = view.findViewById(R.id.btn_frag_c);
                Log.d(TAG, "onClick: btn C clicker " + btn.getText());
                fragment = new FragmentA();
                args = new Bundle();
                args.putString("btn_value", view.getTag().toString() + " " + btn.getText());
                args.putString("operator", "Gabriel");
                fragment.setArguments(args);
                setTransaction(FragmentA.TAG, fragment);
                break;
            case R.id.btn_frag_d:
                btn = view.findViewById(R.id.btn_frag_d);
                Log.d(TAG, "onClick: btn D clicker " + btn.getText());
                fragment = new FragmentA();
                args = new Bundle();
                args.putString("btn_value", view.getTag().toString() + " " + btn.getText());
                args.putString("naruto", "uzumaki");
                fragment.setArguments(args);
                setTransaction(FragmentA.TAG, fragment);
                break;
            case R.id.btn_back_main:
                Log.d(TAG, "onClick: back clicker");
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    Log.d(TAG, "onClick: backstack count " + fragmentManager.getBackStackEntryCount());
                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(i);
                        String tag = backStackEntry.getName();
                        Log.d(TAG, "onClick: backStack entry tag " + tag);
                    }
                    btn = view.findViewById(R.id.btn_back_main);
                    Log.d(TAG, "onClick: btn name " + btn.getText());
                    fragmentManager.popBackStack();
                }
                break;
            default:
                Log.d(TAG, "onClick: something click");

        }

//        switch (view.getTag().toString()) {
//            case "btn_frag_c":
//                Log.d(TAG, "onClick: btn C clicker");
//                break;
//            case "btn_frag_d":
//                Log.d(TAG, "onClick: btn D clicker");
//                break;
//            case "Back":
//                Log.d(TAG, "onClick: back clicker");
//                if (fragmentManager.getBackStackEntryCount() > 0) {
//                    Log.d(TAG, "onClick: backstack count " + fragmentManager.getBackStackEntryCount());
//                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
//                        FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(i);
//                        String tag = backStackEntry.getName();
//                        Log.d(TAG, "onClick: backStack entry tag " + tag);
//                    }
//                    Button btn = view.findViewById(R.id.btn_back_main);
//                    Log.d(TAG, "onClick: btn name " + btn.getText());
//                    fragmentManager.popBackStack();
//                }
//                break;
//            default:
//                Log.d(TAG, "onClick: something click");
//        }
    }

    private void setTransaction(String tag, Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.addToBackStack(tag);
        ft.replace(R.id.container, fragment);
        ft.commit();
    }
}
