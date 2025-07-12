package com.example.bai32;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText txtA, txtB, txtC;
    Button btnTiepTuc, btnGiaiPT, btnThoat;
    TextView txtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtA = findViewById(R.id.txtA);
        txtB = findViewById(R.id.txtB);
        txtC = findViewById(R.id.txtC);
        txtKetQua = findViewById(R.id.txtKetQua);

        btnTiepTuc = findViewById(R.id.btnTiepTuc);
        btnGiaiPT = findViewById(R.id.btnGiaiPT);
        btnThoat = findViewById(R.id.btnThoat);

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtA.setText("");
                txtB.setText("");
                txtC.setText("");
            }
        });

        btnGiaiPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double valueA, valueB, valueC;
                try {
                    valueA = Double.parseDouble(txtA.getText().toString());
                    valueB = Double.parseDouble(txtB.getText().toString());
                    valueC = Double.parseDouble(txtC.getText().toString());
                } catch (Exception e){
                    txtKetQua.setText("Vui lòng nhập số a, b, c");
                    return;
                }
                txtKetQua.setText(ptb2(valueA, valueB, valueC));
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private String ptb2(Double a, Double b, Double c) {
        String result = "";

        Double x1, x2;
        if (a == 0) {
            result = "Không phải phương trình bậc 2";
        } else {
            Double delta = Math.pow(b, 2) - 4 * a * c;
            if (delta < 0) {
                result = "Phương trình vô nghiệm";
            }
            if (delta == 0) {
                x1 = -b / (2 * a);
                result = "Phương trình có nghiệm kép x1 = x2 = " + x1;
            }

            if (delta > 0) {
                x1 = (-b - Math.sqrt(delta)) / (2 * a);
                x2 = (-b + Math.sqrt(delta)) / (2 * a);
                result = "Phương trình có 2 nghiệm x1 " + x1 + ", x2 = " + x2;
            }
        }


        return result;
    }

}