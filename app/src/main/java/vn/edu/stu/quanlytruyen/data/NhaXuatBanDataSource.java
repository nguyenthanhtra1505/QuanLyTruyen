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
import vn.edu.stu.quanlytruyen.util.SQLiteHelper;

public class NhaXuatBanDataSource {
    // Các trường database.
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {SQLiteHelper.COLUMN_maNXB,
            SQLiteHelper.COLUMN_tenNXB};
    public static final String KEY_ID = "manxb";
    private String Tag;

    public NhaXuatBanDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public NhaXuatBan createPhongBan(String pName) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_tenNXB, pName);
        long insertId = database.insert(SQLiteHelper.TABLE_NhaXuatBan, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_NhaXuatBan,
                allColumns, SQLiteHelper.COLUMN_maNXB + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        NhaXuatBan nhaXuatBan = cursorToNhaXuatBan(cursor);
        cursor.close();
        return nhaXuatBan;
    }


    public void deleteNhaXuatBan(NhaXuatBan nxb) {
        long id = nxb.getMaNXB();
        Log.e("SQLite", "Person entry deleted with id: " + id);
        database.delete(SQLiteHelper.TABLE_NhaXuatBan, SQLiteHelper.COLUMN_maNXB
                + " = " + id, null);
    }

    public List<NhaXuatBan> getAllNhaXuatBans() {
        List<NhaXuatBan> nhaXuatBans = new ArrayList<NhaXuatBan>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_NhaXuatBan,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NhaXuatBan nhaXuatBan = cursorToNhaXuatBan(cursor);
            nhaXuatBans.add(nhaXuatBan);
            cursor.moveToNext();
        }
        // Nhớ đóng con trỏ lại nhé.
        cursor.close();
        return nhaXuatBans;
    }
    public void updateDataList(NhaXuatBan nhaXuatBan){
        database = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_tenNXB, nhaXuatBan.getTenNXB());
        long result = database.update(SQLiteHelper.TABLE_NhaXuatBan, values,
                KEY_ID + "=?", new String[]{String.valueOf(nhaXuatBan.getMaNXB())});
        database.close(); // Closing database connection
        Log.d(Tag, "Cập nhật thành công " + result);
 /*       if(result == -1){
            return false;
        }else {
            return true;
        }*/
    }
    private NhaXuatBan cursorToNhaXuatBan(Cursor cursor) {
        NhaXuatBan nhaXuatBan = new NhaXuatBan();
        nhaXuatBan.setMaNXB(cursor.getInt(0));
        nhaXuatBan.setTenNXB(cursor.getString(1));
        return nhaXuatBan;
    }
}
