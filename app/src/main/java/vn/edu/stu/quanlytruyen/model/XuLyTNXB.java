package vn.edu.stu.quanlytruyen.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class XuLyTNXB implements Serializable {
    private static List<NhaXuatBan> dsnxb=new ArrayList<>();
    private static List<Truyen> dstruyen=new ArrayList<>();

    static {
        dsnxb.add(new NhaXuatBan(01,"Kim Đồng"));
        dsnxb.add(new NhaXuatBan(02,"Hà Nội"));
        dsnxb.add(new NhaXuatBan(03,"Tổng hợp TP.HCM"));
    }

    public static List<NhaXuatBan> getDsnxb() {
        return dsnxb;
    }
    public static void setDsnxb(List<NhaXuatBan> dsnxb) {XuLyTNXB.dsnxb = dsnxb;}
    public static List<Truyen> getDstruyen() {
        return dstruyen;
    }
    public static void setDstruyen(List<Truyen> dstruyen) {
        XuLyTNXB.dstruyen = dstruyen;
    }
}
