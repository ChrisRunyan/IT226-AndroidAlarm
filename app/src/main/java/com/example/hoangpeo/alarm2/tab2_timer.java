package com.example.hoangpeo.alarm2;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by hoang peo on 4/24/2017.
 */

public class tab2_timer extends Fragment {
    EditText editText;
    Button button;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);

        editText = (EditText)rootView.findViewById(R.id.editText);
        button = (Button)rootView.findViewById(R.id.button);
        textView = (TextView)rootView.findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String text = editText.getText().toString();
                if(! text.equalsIgnoreCase("")) {
                    int seconds = Integer.valueOf(text);
                    CountDownTimer countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
                        @Override
                        public void onTick(long millis) {
                            textView.setText("seconds: " + (int) (millis / 1000));

                        }

                        @Override
                        public void onFinish() {
                            textView.setText("Finished");
                        }
                    }.start();
                }
            }
        } );
        return rootView;
    }
}
