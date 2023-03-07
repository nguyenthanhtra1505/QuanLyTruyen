package vn.edu.stu.quanlytruyen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import vn.edu.stu.quanlytruyen.Adapter.listViewAdapterNXB;
import vn.edu.stu.quanlytruyen.Adapter.listViewAdapterTruyen;
import vn.edu.stu.quanlytruyen.data.NhaXuatBanDataSource;
import vn.edu.stu.quanlytruyen.data.TruyenDataSource;
import vn.edu.stu.quanlytruyen.model.NhaXuatBan;
import vn.edu.stu.quanlytruyen.model.Truyen;
import vn.edu.stu.quanlytruyen.model.XuLyTNXB;

public class ActivityXuLyTruyen extends AppCompatActivity {
    public static final int REQUEST_CODE_IMAGE = 1;
    public static final int REQUEST_CODE_Storage = 0;
     EditText matruyen, tentruyen, giatruyen, tomtat;
    ImageView imgtruyen;
    Spinner spinnerNXB;
    Button btnthemtruyen, btnimg;
    String imgstring,img;
    ListView lvTruyen;
    Truyen truyen=null;
    Bitmap imgbm;
    private NhaXuatBanDataSource datasourceNXB;
    private TruyenDataSource datasourceTruyen;
    ArrayAdapter<Truyen> adapterTruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xu_ly_truyen);
        datasourceNXB = new NhaXuatBanDataSource(this);
        datasourceNXB.open();
        datasourceTruyen = new TruyenDataSource(this);
        datasourceTruyen.open();
        addControls();
        addEvents();
    }

    private void addControls() {
        matruyen = findViewById(R.id.edit_matruyen);
        tentruyen = findViewById(R.id.edit_tentruyen);
        giatruyen = findViewById(R.id.edit_giaTruyen);
        tomtat = findViewById(R.id.edit_tomtat);
        imgtruyen = findViewById(R.id.img_them);
        btnimg = findViewById(R.id.btnimg);
        btnthemtruyen = findViewById(R.id.btnThemTruyen);
        spinnerNXB = findViewById(R.id.spinnerNXB);
        List<NhaXuatBan> values = datasourceNXB.getAllNhaXuatBans();
        ArrayAdapter<NhaXuatBan> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, values);
        spinnerNXB.setAdapter(adapter);
        lvTruyen=findViewById(R.id.listViewTruyen);
        Intent intent = getIntent();
        if(intent.hasExtra("TRUYEN")) {
            truyen = (Truyen) intent.getSerializableExtra("TRUYEN");
            matruyen.setText(truyen.getMaTruyen() + "");
            tentruyen.setText(truyen.getTenTruyen());
            //spinnerNXB.setSelection(truyen.getNhaXuatBan().);
            tomtat.setText(truyen.getTomTat());
            giatruyen.setText(truyen.getGiaTruyen() + "");
            img=truyen.getHinhTruyen();
            byte[] encodeByte = Base64.decode(truyen.getHinhTruyen(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            imgtruyen.setImageBitmap(bitmap);
            btnthemtruyen.setText("Cập Nhật");
        }

    }

    private void addEvents() {
        btnimg.setOnClickListener(v -> {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_Storage);
        });
        btnthemtruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                if(intent.hasExtra("TRUYEN")){
                        Truyen truyen=new Truyen();
                    Long ma=Long.valueOf(matruyen.getText().toString());
                    String ten = tentruyen.getText().toString();
                    String tomTat = tomtat.getText().toString();
                    int gia = Integer.parseInt(giatruyen.getText().toString());
                    NhaXuatBan nhaXuatBan= (NhaXuatBan) spinnerNXB.getSelectedItem();
                    String maNXB= nhaXuatBan.getTenNXB();
                    Long maTruyen= Long.valueOf(ma);
                    if(imgstring==null){
                        datasourceTruyen.updateDataList(maTruyen,ten,img,tomTat,gia,maNXB);
                    }else {
                        datasourceTruyen.updateDataList(maTruyen,ten,imgstring,tomTat,gia,maNXB);
                    }
                    Intent i = new Intent(ActivityXuLyTruyen.this,ActivityDSTruyen.class);
                    startActivity(i);
                }else {
                    String ten = tentruyen.getText().toString();
                    String tomTat = tomtat.getText().toString();
                    int gia = Integer.parseInt(giatruyen.getText().toString());
                    NhaXuatBan nhaXuatBan= (NhaXuatBan) spinnerNXB.getSelectedItem();
                    String maNXB= nhaXuatBan.getTenNXB();
                    datasourceTruyen.createTruyen(ten,imgstring,tomTat,gia,maNXB);
                    Intent i = new Intent(ActivityXuLyTruyen.this,ActivityDSTruyen.class);
                    startActivity(i);
                }

            }
        });


}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_Storage && grantResults.length >= 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectImage();
            } else {
                Toast.makeText(this, "xảy ra lỗi", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK) {
            Uri image = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(image);
                Bitmap bm = BitmapFactory.decodeStream(inputStream);
                ByteArrayOutputStream byt = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, byt);
                byte[] bytes = byt.toByteArray();
                imgstring = Base64.encodeToString(bytes, 0, bytes.length, 0);
                imgtruyen.setImageBitmap(bm);
                Log.d("msg", image + "");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
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
                Intent i = new Intent(ActivityXuLyTruyen.this, ActivityAbout.class);
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