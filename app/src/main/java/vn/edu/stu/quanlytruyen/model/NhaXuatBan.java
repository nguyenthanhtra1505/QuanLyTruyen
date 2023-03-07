package vn.edu.stu.quanlytruyen.model;

import java.io.Serializable;
import java.util.Date;

public class NhaXuatBan implements Serializable {
    private int maNXB;
    private String tenNXB;
    public NhaXuatBan(int maNXB, String tenNXB) {
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
    }

    public NhaXuatBan() {
    }

    public int getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(int maNXB) {
        this.maNXB = maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    @Override
    public String toString() {
        return tenNXB;
    }
}
