package com.example.tx1_nguyentrunghieu_2022600419;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    EditText etTen, etSoLuong;
    TextView tvThanhTien;
    Button btnTinhTien, btnNhapTiep, btnThongKe;
    CheckBox cbVIP;
    ListView lvThongKe;

    ArrayList<String> thongKeList = new ArrayList<>();
    ArrayAdapter adapter = null;

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

        etTen = findViewById(R.id.etTen);
        etSoLuong = findViewById(R.id.etSoLuong);
        tvThanhTien = findViewById(R.id.tvThanhTien);

        btnTinhTien = findViewById(R.id.btnTinhTien);
        btnNhapTiep = findViewById(R.id.btnNhapTiep);
        btnThongKe = findViewById(R.id.btnThongKe);
        cbVIP = findViewById(R.id.cbVIP);
        lvThongKe = findViewById(R.id.lvThongKe);

        fakeData();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, thongKeList);
        lvThongKe.setAdapter(adapter);

        btnTinhTien.setOnClickListener(view -> {
            String ten = etTen.getText().toString();
            String soLuong = etSoLuong.getText().toString();

            if (ten.isEmpty() || soLuong.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            int thanhTien = Integer.parseInt(soLuong) * 50000;
            tvThanhTien.setText(String.valueOf(thanhTien));
        });

        btnNhapTiep.setOnClickListener(view -> {
            String ten = etTen.getText().toString();
            String soLuong = etSoLuong.getText().toString();
            String thanhTien = tvThanhTien.getText().toString();
            boolean isVIP = cbVIP.isChecked();
            String Loai = isVIP ? "Khách VIP" : "Khách thường";
            if (ten.isEmpty() || soLuong.isEmpty() || thanhTien.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            thongKeList.add(ten + " - " + soLuong + " - " + Loai + " - " + thanhTien);
            adapter.notifyDataSetChanged();

            etTen.setText("");
            etSoLuong.setText("");
            tvThanhTien.setText("");
            cbVIP.setChecked(false);

        });

        btnThongKe.setOnClickListener(view -> {

            int sumKhachThuong = 0;
            int sumKhachVip = 0;

            for(String khach : thongKeList) {
                if(khach.contains("VIP"))
                    sumKhachVip += Integer.parseInt(khach.split(" - ")[3]);
                else
                    sumKhachThuong += Integer.parseInt(khach.split(" - ")[3]);
            }

            String result = "Khách thường - " + sumKhachThuong + "\nKhách VIP - " + sumKhachVip;


            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        });
    }

    public void fakeData() {
        thongKeList.add("Nguyễn Văn A - 1 - Khách VIP - 50000");
        thongKeList.add("Nguyễn Văn B - 2 - Khách Thường - 100000");
        thongKeList.add("Nguyễn Văn C - 3 - Khách VIP - 150000");
    }
}


