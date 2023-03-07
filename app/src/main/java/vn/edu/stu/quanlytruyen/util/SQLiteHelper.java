package vn.edu.stu.quanlytruyen.util;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SQLiteHelper extends SQLiteOpenHelper{
    public static final String TABLE_NhaXuatBan = "nhaxuatban";
    public static final String COLUMN_maNXB = "manxb";
    public static final String COLUMN_tenNXB = "tennxb";

    public static final String TABLE_Truyen = "truyen";
    public static final String COLUMN_maTruyen = "matruyen";
    public static final String COLUMN_tenTruyen = "tentruyen";
    public static final String COLUMN_HinhAnh = "hinhanh";
    public static final String COLUMN_tomTat = "tomtat";
    public static final String COLUMN_giaTruyen = "giatruyen";
    public static final String COLUMN_idNXB = "manxb";

    private static final String DATABASE_NAME = "truyen.db";
    private static final int DATABASE_VERSION = 1;

    // Câu lệnh khởi tạo Database.
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NhaXuatBan + "( " + COLUMN_maNXB
            + " integer primary key autoincrement, " + COLUMN_tenNXB
            + " text not null);";

    private static final String CREATE = "create table "
            +  TABLE_Truyen + "( "
            +  COLUMN_maTruyen + " integer primary key autoincrement, "
            + COLUMN_tenTruyen + " text not null, "
            + COLUMN_HinhAnh +" text not null, "
            + COLUMN_tomTat +" text not null ,"
            + COLUMN_giaTruyen +" integer not null, "
            + COLUMN_idNXB + " text not null);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE);
        database.execSQL(DATABASE_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NhaXuatBan);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Truyen);
        onCreate(db);
    }
}
