package com.app.fragmentlivedataviewmodelpratice.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.fragmentlivedataviewmodelpratice.CustomClickListener;
import com.app.fragmentlivedataviewmodelpratice.R;

public class MainFragment extends Fragment {

    static final String TAG = "MainFragment";
    private MainViewModel mViewModel;
    CustomClickListener btnClickListener;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnClickListener = new CustomClickListener(getParentFragmentManager());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        Button btnFragA = view.findViewById(R.id.btn_frag_a);
        Button btnFragB = view.findViewById(R.id.btn_frag_b);
        setOnClick(btnFragA);
        setOnClick(btnFragB);

        Button btnFragC = view.findViewById(R.id.btn_frag_c);
        btnFragC.setOnClickListener(btnClickListener);
        Button btnFragD = view.findViewById(R.id.btn_frag_d);
        btnFragD.setOnClickListener(btnClickListener);
    }

    void setOnClick(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pass argument to the new fragment
                Bundle arg = new Bundle();
                arg.putString("btn_value", btn.getText().toString());
                Fragment fragment = FragmentA.newInstance();
                fragment.setArguments(arg);

                Log.d(TAG, "onClick: Fragment " + btn.getText() + " view tag " + view.getTag());
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(FragmentA.TAG)
                        .commit();
            }
        });
    }

}