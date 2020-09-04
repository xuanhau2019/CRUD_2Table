package com.example.thuchanhck;

public class SP {
    private int MaSo;
    private String Ten;
    private String DVT;
    private String DonGia;
    private int MaSX;

    public SP(int maSo, String ten, String DVT, String donGia, int maSX) {
        MaSo = maSo;
        Ten = ten;
        this.DVT = DVT;
        DonGia = donGia;
        MaSX = maSX;
    }

    public SP() {
    }

    public int getMaSo() {
        return MaSo;
    }

    public void setMaSo(int maSo) {
        MaSo = maSo;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String donGia) {
        DonGia = donGia;
    }

    public int getMaSX() {
        return MaSX;
    }

    public void setMaSX(int maSX) {
        MaSX = maSX;
    }

    @Override
    public String toString() {
        return "SP{" +
                "MaSo=" + MaSo +
                ", Ten='" + Ten + '\'' +
                ", DVT='" + DVT + '\'' +
                ", DonGia='" + DonGia + '\'' +
                ", MaSX=" + MaSX +
                '}';
    }
}
