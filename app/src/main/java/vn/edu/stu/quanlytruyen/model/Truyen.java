package vn.edu.stu.quanlytruyen.model;

import java.io.Serializable;

public class Truyen implements Serializable {
    private int maTruyen;
    private String tenTruyen;
    private String hinhTruyen;
    private String tomTat;
    private int giaTruyen;
    private String nhaXuatBan;
    public Truyen() {
    }


    public Truyen(int maTruyen, String tenTruyen, String hinhTruyen, String tomTat, int giaTruyen, String nhaXuatBan) {
        this.maTruyen = maTruyen;
        this.tenTruyen = tenTruyen;
        this.hinhTruyen = hinhTruyen;
        this.tomTat = tomTat;
        this.giaTruyen = giaTruyen;
        this.nhaXuatBan = nhaXuatBan;
    }

    public int getMaTruyen() {
        return maTruyen;
    }

    public void setMaTruyen(int maTruyen) {
        this.maTruyen = maTruyen;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getHinhTruyen() {
        return hinhTruyen;
    }

    public void setHinhTruyen(String hinhTruyen) {
        this.hinhTruyen = hinhTruyen;
    }

    public String getTomTat() {
        return tomTat;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
    }

    public int getGiaTruyen() {
        return giaTruyen;
    }

    public void setGiaTruyen(int giaTruyen) {
        this.giaTruyen = giaTruyen;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }
}
