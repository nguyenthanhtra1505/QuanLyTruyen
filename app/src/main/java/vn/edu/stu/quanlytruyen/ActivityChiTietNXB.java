package vn.edu.stu.quanlytruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import vn.edu.stu.quanlytruyen.model.NhaXuatBan;
import vn.edu.stu.quanlytruyen.model.Truyen;

public class ActivityChiTietNXB extends AppCompatActivity {
    TextView tvTen,tvMa;
    NhaXuatBan nhaXuatBan=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nxb);
        addControls();
        Intent intent = getIntent();
        nhaXuatBan= (NhaXuatBan) intent.getSerializableExtra("NXB");
        tvMa.setText(nhaXuatBan.getMaNXB()+"");
        tvTen.setText(nhaXuatBan.getTenNXB());

    }
    private void addControls() {
        tvMa = findViewById(R.id.manxb_chitiet);
        tvTen=findViewById(R.id.tenNXB_chitiet);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent i = new Intent(ActivityChiTietNXB.this, ActivityAbout.class);
                startActivity(i);
                return true;
            case R.id.item2:
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}