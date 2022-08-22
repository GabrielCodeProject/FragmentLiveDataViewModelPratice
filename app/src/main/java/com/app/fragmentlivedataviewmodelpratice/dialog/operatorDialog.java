package com.app.fragmentlivedataviewmodelpratice.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.app.fragmentlivedataviewmodelpratice.R;
import com.app.fragmentlivedataviewmodelpratice.ui.main.FragmentA;
import com.app.fragmentlivedataviewmodelpratice.ui.main.MainViewModel;

public class OperatorDialog extends DialogFragment {

    private static final String TAG = "operatorDialog";

    public static OperatorDialog newInstance() {
        Bundle args = new Bundle();
        OperatorDialog fragment = new OperatorDialog();
        fragment.setArguments(args);
        fragment.setCancelable(false);
        return fragment;
    }

    public interface OperatorDialogListener {
        void onEnterOperatorClick(DialogFragment dialogFragment);
    }

    public OperatorDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
//        setTargetFragment();
//        try {
//            if (context instanceof FragmentActivity) {
//                listener = (OperatorDialogListener) activity.getFragmentManager().findFragmentByTag(FragmentA.TAG);
//            }
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString() + " must implement OperatorDialogListener");
//        }
        try {
            listener = (OperatorDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OperatorListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            Log.d(TAG, "onCreateDialog: have args? " + getArguments().getString("test"));
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_operator, null);
        EditText ed = view.findViewById(R.id.ed_operator);
        builder.setView(view);
        builder.setTitle("Enter operator name")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onClick: operator name save " + ed.getText());
                        Toast.makeText(getContext(), ed.getText(), Toast.LENGTH_LONG).show();
                        listener.onEnterOperatorClick(OperatorDialog.this);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }
}
