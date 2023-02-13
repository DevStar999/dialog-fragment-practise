package com.example.dialogfragmentpractise;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    private static final String TAG = "MyDialogFragment";
    public OnInputListener mListener;

    //widgets
    private AppCompatEditText mInput;
    private AppCompatTextView mActionOk, mActionCancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_my_custom, container, false);

        mActionCancel = view.findViewById(R.id.action_cancel);
        mActionOk = view.findViewById(R.id.action_ok);
        mInput = view.findViewById(R.id.input);

        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                dismiss();
            }
        });


        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input");

                String input = mInput.getText().toString();

                //"Best Practice" but it takes longer
                mListener.sendInput(input);

                dismiss();
            }
        });

        return view;
    }

    @Override
    public int getTheme() {
        return R.style.CustomDialogTheme;
    }

    public interface OnInputListener {
        void sendInput(String input);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnInputListener) {
            mListener = (OnInputListener) context;
        } else {
            throw new RuntimeException(context + " must implement OnInputListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}