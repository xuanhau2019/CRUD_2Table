package com.example.thuchanhck;

public class NSX {
    private int MaSX;
    private String Ten;
    private String DiaChi;

    public NSX(int maSX, String ten, String diaChi) {
        MaSX = maSX;
        Ten = ten;
        DiaChi = diaChi;
    }

    public NSX() {
    }

    public int getMaSX() {
        return MaSX;
    }

    public void setMaSX(int maSX) {
        MaSX = maSX;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    @Override
    public String toString() {
        return "NSX{" +
                "MaSX=" + MaSX +
                ", Ten='" + Ten + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                '}';
    }
}
