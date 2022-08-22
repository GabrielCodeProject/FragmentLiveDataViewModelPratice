package com.app.fragmentlivedataviewmodelpratice.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.app.fragmentlivedataviewmodelpratice.CustomClickListener;
import com.app.fragmentlivedataviewmodelpratice.R;
import com.app.fragmentlivedataviewmodelpratice.dialog.operatorDialog;

public class FragmentA extends Fragment implements operatorDialog.OperatorDialogListener {

    public static final String TAG = "FragmentA";
    CustomClickListener btnClickListener;
    String btnNameArg;

    MainViewModel mMainViewModel;
    TextView tv;
    public static FragmentA newInstance() {

        Bundle args = new Bundle();

        FragmentA fragment = new FragmentA();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);


        btnClickListener = new CustomClickListener(getParentFragmentManager());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //receive argument from previous fragment
        if (getArguments() != null) {
            Log.d(TAG, "onCreateView: have args?");
            btnNameArg = getArguments().getString("btn_value");
            Log.d(TAG, "onCreateView: " + btnNameArg);
        }

        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv = view.findViewById(R.id.tv_argument_text);
        tv.setText(btnNameArg);
        mMainViewModel.getOperator().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ((TextView) view.findViewById(R.id.tv_argument_text)).setText(getString(R.string.saved, s));
            }
        });

        showOperator();
        Button back = view.findViewById(R.id.btn_back_main);
        back.setOnClickListener(btnClickListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null) {
            Log.d(TAG, "onCreateView: have args?");
            btnNameArg = getArguments().getString("btn_value");
            Log.d(TAG, "onCreateView: " + btnNameArg);
        }
    }

    public void showOperator() {
        Bundle args = new Bundle();
        args.putString("test", "YOLO");
        DialogFragment fragment = operatorDialog.newInstance();
        fragment.setArguments(args);
        fragment.show(getParentFragmentManager(), "operator");
    }

    @Override
    public void onEnterOperatorClick(DialogFragment dialogFragment) {
        //EditText edOperator = dialogFragment.getActivity().findViewById(R.id.ed_operator);
        mMainViewModel.saveNewOperator("edOperator.getText().toString()");
    }
}
