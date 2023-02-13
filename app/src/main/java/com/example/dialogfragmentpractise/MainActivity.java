package com.example.dialogfragmentpractise;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

public class MainActivity extends AppCompatActivity implements MyDialogFragment.OnInputListener {
    private static final String TAG = "MainActivity";

    //widgets
    private AppCompatButton mOpenDialog;
    public AppCompatTextView mInputDisplay;

    //vars
    public String mInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOpenDialog = findViewById(R.id.open_dialog);
        mInputDisplay = findViewById(R.id.input_display);

        mOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: opening dialog.");

                MyDialogFragment dialog = new MyDialogFragment();
                dialog.show(getSupportFragmentManager(), "MyDialogFragment");
            }
        });
    }

    @Override
    public void sendInput(String input) {
        Log.d(TAG, "sendInput: got the input: " + input);

        mInput = input;

        setInputToTextView();
    }

    private void setInputToTextView(){
        mInputDisplay.setText(mInput);
    }
}