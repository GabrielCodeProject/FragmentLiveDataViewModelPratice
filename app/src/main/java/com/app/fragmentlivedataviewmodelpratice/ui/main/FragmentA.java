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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.app.fragmentlivedataviewmodelpratice.CustomClickListener;
import com.app.fragmentlivedataviewmodelpratice.R;
import com.app.fragmentlivedataviewmodelpratice.dialog.OperatorDialog;

public class FragmentA extends Fragment implements OperatorDialog.OperatorDialogListener {

    public static final String TAG = "FragmentA";
    CustomClickListener btnClickListener;
    String btnNameArg;
    String argOperator;
    String argNaruto;
    Boolean argIsWorking;

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
            argOperator = getArguments().getString("operator");
            argNaruto = getArguments().getString("naruto");
            argIsWorking = getArguments().getBoolean("is_working");
            Log.d(TAG, "onCreateView: " + btnNameArg + " " + argOperator + " " + " " + argNaruto + " isWorking" + argIsWorking);
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
            Log.d(TAG, "onResume: have args?");
            String test = getArguments().getString("yolo");
            Log.d(TAG, "onResume: " + test);
        }
    }

    public void showOperator() {
        Bundle args = new Bundle();
        args.putString("test", "YOLO");
        DialogFragment fragment = OperatorDialog.newInstance();
        fragment.setArguments(args);
        fragment.setTargetFragment(this,0);
        fragment.show(getParentFragmentManager(), "operator");
    }

    @Override
    public void onEnterOperatorClick(DialogFragment dialogFragment) {
//        Fragment fragment = dialogFragment.getChildFragmentManager().findFragmentByTag(OperatorDialog.TAG);
        EditText edOperator = dialogFragment.getDialog().findViewById(R.id.ed_operator);
        Log.d(TAG, "onEnterOperatorClick: " + edOperator.getText());
        mMainViewModel.saveNewOperator(edOperator.getText().toString());
    }
}
