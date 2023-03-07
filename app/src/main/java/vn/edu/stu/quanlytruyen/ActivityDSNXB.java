package vn.edu.stu.quanlytruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.stu.quanlytruyen.Adapter.listViewAdapterNXB;
import vn.edu.stu.quanlytruyen.data.NhaXuatBanDataSource;
import vn.edu.stu.quanlytruyen.data.TruyenDataSource;
import vn.edu.stu.quanlytruyen.model.NhaXuatBan;
import vn.edu.stu.quanlytruyen.model.Truyen;

public class ActivityDSNXB extends AppCompatActivity {
    private EditText editmanxb, edittennxb;
    private Button btnThem, btnSua;
    private ListView lvNXB;
    ArrayAdapter<NhaXuatBan> adapterNXB;
    NhaXuatBan nhaXuatBan=null;
    private NhaXuatBanDataSource datasource;
    private TruyenDataSource dataSourceTruyen;
    int vitri=0;
    TextView btnhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsnxb);
        datasource = new NhaXuatBanDataSource(this);
        datasource.open();
        dataSourceTruyen=new TruyenDataSource(this);
        dataSourceTruyen.open();
        addControls();
        addEvents();
    }

    private void addControls() {
        btnhome=findViewById(R.id.home);
        lvNXB = findViewById(R.id.listViewNXB);
        editmanxb = findViewById(R.id.edit_manxb);
        edittennxb = findViewById(R.id.edit_tennxb);
        btnThem = findViewById(R.id.btnThemNXB);
        btnSua = findViewById(R.id.btnsuanxb);
        List<NhaXuatBan> values = datasource.getAllNhaXuatBans();
        adapterNXB = new listViewAdapterNXB(
                ActivityDSNXB.this,
                R.layout.activity_itemds,
                values
        );

        lvNXB.setAdapter(adapterNXB);
        //ma = findViewById(R.id.manxb);
    }

    private void addEvents() {
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDSNXB.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edittennxb.getText().toString().isEmpty()) {
                    String tenNXB = edittennxb.getText().toString();
                    ArrayAdapter<NhaXuatBan> adapter = (ArrayAdapter<NhaXuatBan>) lvNXB.getAdapter();
                    NhaXuatBan nhaXuatBan = null;
                    nhaXuatBan = datasource.createPhongBan(tenNXB);
                    adapter.add(nhaXuatBan);
                    edittennxb.setText("");
                    editmanxb.setText("");
                    editmanxb.requestFocus();
                } else {
                    Toast.makeText(ActivityDSNXB.this, "vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        lvNXB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vitri=i;
                if (i>= 0 && i < lvNXB.getCount()) {
                    NhaXuatBan nhaXuatBan=null;
                    nhaXuatBan=adapterNXB.getItem(i);
                    editmanxb.setText(nhaXuatBan.getMaNXB() + "");
                    edittennxb.setText(nhaXuatBan.getTenNXB());
                    editmanxb.requestFocus();
                }

            }
        });
        lvNXB.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivityDSNXB.this, ActivityChiTietNXB.class);
                NhaXuatBan nxb = adapterNXB.getItem(i);
                intent.putExtra("NXB", nxb);
                startActivity(intent);
                return true;
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhaXuatBan nhaXuatBan=new NhaXuatBan();
                nhaXuatBan=adapterNXB.getItem(vitri);
                int maNXB = Integer.parseInt(editmanxb.getText().toString());
                String tenNXB = edittennxb.getText().toString();
                dataSourceTruyen.setTruyenPhongBan(nhaXuatBan.getTenNXB(),tenNXB);
                nhaXuatBan.setMaNXB(maNXB);
                nhaXuatBan.setTenNXB(tenNXB);
                adapterNXB.notifyDataSetChanged();
                datasource.updateDataList(nhaXuatBan);


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
                Intent i = new Intent(ActivityDSNXB.this, ActivityAbout.class);
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