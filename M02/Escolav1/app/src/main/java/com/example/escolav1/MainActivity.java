package com.example.escolav1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etndR1, etndR2;
    TextView tvAvg, tvSit;
    ImageView imgSit;
    LinearLayout layResults;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etndR1 = findViewById(R.id.etndR1);
        etndR2 = findViewById(R.id.etndR2);
        tvAvg = findViewById(R.id.tvAvg);
        tvSit = findViewById(R.id.tvSit);
        imgSit = findViewById(R.id.ivwSit);
        layResults = findViewById(R.id.layResults);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        layResults.setVisibility(View.INVISIBLE);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @SuppressLint("DefaultLocale")
    public void calculate(View view) {

        boolean ok = true;

        if (etndR1.getText().toString().trim().isEmpty()) {
            ok = false;
            etndR1.setError(getString(R.string.strMsgError));
        }
        if (etndR2.getText().toString().trim().isEmpty()) {
            ok = false;
            etndR2.setError(getString(R.string.strMsgError));
        }
        if (ok) {
            imm.hideSoftInputFromWindow(etndR1.getWindowToken(), 0);
            layResults.setVisibility(View.VISIBLE);

            // calculating average
            float r1 = Float.parseFloat(etndR1.getText().toString());
            float r2 = Float.parseFloat(etndR2.getText().toString());
            float avg = (r1 + r2) / 2;

            // average result
            tvAvg.setText(String.format("%.1f", avg));

            // status
            if (avg < 5) {
                // reproved
                tvSit.setText(getString(R.string.strSitRep));
                tvSit.setTextColor(Color.parseColor("#7E1010"));
                Toast.makeText(getApplicationContext(), R.string.strMsgRep, Toast.LENGTH_SHORT).show();
                imgSit.setImageResource(R.drawable.emojireprovado);
            } else if (avg < 7) {
                // retake test
                tvSit.setText(getString(R.string.strSitRt));
                tvSit.setTextColor(Color.parseColor("#21219C"));
                Toast.makeText(getApplicationContext(), R.string.strMsgRt, Toast.LENGTH_SHORT).show();
                imgSit.setImageResource(R.drawable.emojirecuperacao);
            } else {
                // approved
                tvSit.setText(getString(R.string.strSitAp));
                tvSit.setTextColor(Color.parseColor("#0E801B"));
                Toast.makeText(getApplicationContext(), R.string.strMsgAp, Toast.LENGTH_SHORT).show();
                imgSit.setImageResource(R.drawable.emojiaprovado);
            }
        }
    }
}