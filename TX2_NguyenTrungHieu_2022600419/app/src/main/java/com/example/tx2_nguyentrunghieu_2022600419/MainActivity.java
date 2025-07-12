package com.example.tx2_nguyentrunghieu_2022600419;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    final String TAG = "mytag";
    List<KhachHang> ListKhachHang = new ArrayList<>();
    EditText etTen, etSoLuong;
    CheckBox cbVIP;
    TextView tvThanhTien;
    ListView lvThongKe;
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
        cbVIP = findViewById(R.id.cbVIP);
        tvThanhTien = findViewById(R.id.tvThanhTien);
        lvThongKe = findViewById(R.id.lvThongKe);

//      fake data
        ListKhachHang.add(new KhachHang("Hieu", 1, true));
        ListKhachHang.add(new KhachHang("Hieu1", 20, false));
        ListKhachHang.add(new KhachHang("Hieu2", 13, true));
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ListKhachHang);
        lvThongKe.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //handle action bar item click
        int id = item.getItemId();
        if(id == R.id.mnu_nhaptiep) {
            nhapTiep();
        } else if(id == R.id.mnu_thongke) {
            thongKe();
        } else if(id == R.id.mnu_luutru) {
            luuTru();
        } else if(id == R.id.mnu_dong) {
            dong();
        }

        return super.onOptionsItemSelected(item);

    }

    public void nhapTiep() {
        String ten = etTen.getText().toString();
        int soLuong = Integer.parseInt(etSoLuong.getText().toString());
        boolean vip = cbVIP.isChecked();

        KhachHang kh = new KhachHang(ten, soLuong, vip);
        ListKhachHang.add(kh);
        adapter.notifyDataSetChanged();

        tvThanhTien.setText(kh.thanhTien() + "");
        etTen.setText("");
        etSoLuong.setText("");
        cbVIP.setChecked(false);
        etTen.setSelected(true);
        etSoLuong.setSelected(true);
    }

    public void thongKe() {
        String text = "";
        for(KhachHang kh : ListKhachHang) {
            text += kh.toString() + "\n";
        }

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void luuTru() {
        String sdcard = this.getExternalFilesDir(null).getAbsolutePath() + "/myFile.txt";
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(sdcard));
            for(KhachHang kh : ListKhachHang) {
                writer.write(kh.toString() + "\n");
            }
            writer.close();
            Toast.makeText(this, "Ghi thành công", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Ghi thất bại", Toast.LENGTH_SHORT).show();
            Log.e(TAG, e.toString());
            throw new RuntimeException(e);
        }
    }

    public void dong() {
        finish();
    }
}