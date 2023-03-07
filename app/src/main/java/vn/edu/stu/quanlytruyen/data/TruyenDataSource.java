package vn.edu.stu.quanlytruyen.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.stu.quanlytruyen.model.NhaXuatBan;
import vn.edu.stu.quanlytruyen.model.Truyen;
import vn.edu.stu.quanlytruyen.util.SQLiteHelper;

public class TruyenDataSource {
    // Các trường database.
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {SQLiteHelper.COLUMN_maTruyen,
            SQLiteHelper.COLUMN_tenTruyen,
            SQLiteHelper.COLUMN_HinhAnh,
            SQLiteHelper.COLUMN_tomTat,
            SQLiteHelper.COLUMN_giaTruyen,
            SQLiteHelper.COLUMN_idNXB
           };

    public static final String KEY_ID = "matruyen";
    public TruyenDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }
    private String Tag;

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Truyen createTruyen(String ten, String hinhanh, String tomtat,int gia,String idnxb ) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_tenTruyen, ten);
        values.put(SQLiteHelper.COLUMN_idNXB, idnxb);
        values.put(SQLiteHelper.COLUMN_HinhAnh,hinhanh );
        values.put(SQLiteHelper.COLUMN_tomTat, tomtat);
        values.put(SQLiteHelper.COLUMN_giaTruyen, gia);

        long insertId = database.insert(SQLiteHelper.TABLE_Truyen, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_Truyen,
                allColumns, SQLiteHelper.COLUMN_maTruyen + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Truyen truyen = cursorToTruyen(cursor);
        cursor.close();
        return truyen;
    }



    public List<Truyen> getAllTruyens() {
        List<Truyen> truyens = new ArrayList<Truyen>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_Truyen,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Truyen truyen = cursorToTruyen(cursor);
            truyens.add(truyen);
            cursor.moveToNext();
        }
        // Nhớ đóng con trỏ lại nhé.
        cursor.close();
        return truyens;
    }

    public boolean getTruyens(String maXNB) {
        List<Truyen> truyen=getAllTruyens();
        for (Truyen truyen1 : truyen) {
            if( truyen1.getNhaXuatBan().equals(maXNB)){
                return true;
            }
        }
        return false;
    }
    public boolean setTruyenPhongBan(String maXNB,String caimoi) {
        List<Truyen> truyen=getAllTruyens();
        for (Truyen truyen1 : truyen) {
            if( truyen1.getNhaXuatBan().equals(maXNB)){
                truyen1.setNhaXuatBan(caimoi);
                updateDataList(Long.valueOf(truyen1.getMaTruyen()),truyen1.getTenTruyen(),
                        truyen1.getHinhTruyen(),truyen1.getTomTat(),truyen1.getGiaTruyen(),truyen1.getNhaXuatBan());
            }
        }
        return true;
    }

    private Truyen cursorToTruyen(Cursor cursor) {
        Truyen truyen = new Truyen();
        truyen.setMaTruyen(cursor.getInt(0));
        truyen.setTenTruyen(cursor.getString(1));
        truyen.setHinhTruyen(cursor.getString(2));
        truyen.setTomTat(cursor.getString(3));
        truyen.setGiaTruyen(cursor.getInt(4));
        truyen.setNhaXuatBan(cursor.getString(5));
        return truyen;
    }
    public void deleteTruyen(Truyen truyen) {
        long id = truyen.getMaTruyen();
        Log.e("SQLite", "Person entry deleted with id: " + id);
        database.delete(SQLiteHelper.TABLE_Truyen, SQLiteHelper.COLUMN_maTruyen
                + " = " + id, null);
    }
    /*
    public void updateDataList(Truyen truyen){
        database = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_tenTruyen, truyen.getTenTruyen());
        values.put(SQLiteHelper.COLUMN_HinhAnh, truyen.getHinhTruyen());
        values.put(SQLiteHelper.COLUMN_tomTat, truyen.getTomTat());
        values.put(SQLiteHelper.COLUMN_giaTruyen,truyen.getGiaTruyen());
        values.put(SQLiteHelper.COLUMN_idNXB, truyen.getNhaXuatBan());
        long result = database.update(SQLiteHelper.TABLE_Truyen, values,
                KEY_ID + "=?", new String[]{String.valueOf(truyen.getMaTruyen())});
        database.close(); // Closing database connection
        Log.d(Tag, "Cập nhật thành công " + result);
    }*/
    public void updateDataList(Long ma,String ten,String hinhanh,String tomtat,int gia,String idnxb){
        database = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_tenTruyen, ten);
        values.put(SQLiteHelper.COLUMN_HinhAnh, hinhanh);
        values.put(SQLiteHelper.COLUMN_tomTat, tomtat);
        values.put(SQLiteHelper.COLUMN_giaTruyen,gia);
        values.put(SQLiteHelper.COLUMN_idNXB, idnxb);
        long result = database.update(SQLiteHelper.TABLE_Truyen,values,
                KEY_ID + "=?", new String[]{String.valueOf(ma)});
        database.close(); // Closing database connection
    }

}
