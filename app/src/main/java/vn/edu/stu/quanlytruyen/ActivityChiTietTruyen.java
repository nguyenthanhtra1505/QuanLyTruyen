package vn.edu.stu.quanlytruyen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import vn.edu.stu.quanlytruyen.Adapter.listViewAdapterTruyen;
import vn.edu.stu.quanlytruyen.model.Truyen;

public class ActivityChiTietTruyen extends AppCompatActivity {
    TextView tvTen,tvMa,tvNXB,tvTomTat,tvGia;
    ImageView imgTruyen;
    Truyen truyen=null;
    FloatingActionButton fabThemTruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttchitiet_itemmain);
        addControls();
        Intent intent = getIntent();
        truyen= (Truyen) intent.getSerializableExtra("TRUYEN");
        tvMa.setText(truyen.getMaTruyen()+"");
        tvTen.setText(truyen.getTenTruyen());
        tvNXB.setText(truyen.getNhaXuatBan());
        tvTomTat.setText(truyen.getTomTat());
        tvGia.setText(truyen.getGiaTruyen()+"");
        byte[] encodeByte = Base64.decode(truyen.getHinhTruyen(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        imgTruyen.setImageBitmap(bitmap);
        addEnvents();
    }
    private void addControls() {
        tvMa = findViewById(R.id.ma_chitiet);
        tvTen=findViewById(R.id.ten_chitiet);
        tvGia=findViewById(R.id.giatruyen_chitiet);
        tvTomTat=findViewById(R.id.ndtomtat_chitiet);
        tvNXB=findViewById(R.id.nxb_chitiet);
        imgTruyen=findViewById(R.id.img_chitiet);
        fabThemTruyen = findViewById(R.id.fabthemtruyen);
    }
    private void addEnvents() {
        fabThemTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityChiTietTruyen.this, ActivityXuLyTruyen.class);
                startActivity(intent);
            }
        });
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
                Intent i = new Intent(ActivityChiTietTruyen.this, ActivityAbout.class);
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
