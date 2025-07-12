package com.example.tx2_nguyentrunghieu_2022600419;

import androidx.annotation.NonNull;

public class KhachHang {
    String ten;
    int soLuongSach;
    boolean vip;

    public KhachHang() {
    }

    public KhachHang(String ten, int soLuongSach, boolean vip) {
        this.ten = ten;
        this.soLuongSach = soLuongSach;
        this.vip = vip;
    }

    public String getTen() {
        return ten;
    }

    public int getSoLuongSach() {
        return soLuongSach;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setSoLuongSach(int soLuongSach) {
        this.soLuongSach = soLuongSach;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public double thanhTien() {
        return this.soLuongSach * 5000;
    }

    @NonNull
    @Override
    public String toString() {
        return this.ten + " - " + this.soLuongSach + " - Khách " + (this.vip ? "Vip" : "Thường") + " - " + thanhTien();
    }
}
