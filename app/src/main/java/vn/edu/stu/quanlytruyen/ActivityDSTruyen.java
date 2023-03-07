package vn.edu.stu.quanlytruyen;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import vn.edu.stu.quanlytruyen.Adapter.listViewAdapterNXB;
import vn.edu.stu.quanlytruyen.Adapter.listViewAdapterTruyen;
import vn.edu.stu.quanlytruyen.data.TruyenDataSource;
import vn.edu.stu.quanlytruyen.model.NhaXuatBan;
import vn.edu.stu.quanlytruyen.model.Truyen;
import vn.edu.stu.quanlytruyen.model.XuLyTNXB;

public class ActivityDSTruyen extends AppCompatActivity {
    private TruyenDataSource datasourceTruyen;
    ListView lvTruyen;
    FloatingActionButton fabThemTruyen;
    ArrayAdapter<Truyen> adapterTruyen;
    TextView btnhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dstruyen);
        datasourceTruyen = new TruyenDataSource(this);
        datasourceTruyen.open();
        addControls();
        addEnvents();
    }

    private void addControls() {
        btnhome=findViewById(R.id.home);
        fabThemTruyen = findViewById(R.id.fabthemtruyen);
        lvTruyen = findViewById(R.id.listViewTruyen);
        List<Truyen> values = datasourceTruyen.getAllTruyens();
        adapterTruyen = new listViewAdapterTruyen(
                ActivityDSTruyen.this,
                R.layout.activity_itemdstruyen,
                values
        );
        lvTruyen.setAdapter(adapterTruyen);
    }

    private void addEnvents() {
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDSTruyen.this, MainActivity.class);
                startActivity(intent);
            }
        });
        fabThemTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDSTruyen.this, ActivityXuLyTruyen.class);
                startActivity(intent);
            }
        });
        lvTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivityDSTruyen.this, ActivityChiTietTruyen.class);
                Truyen truyen = adapterTruyen.getItem(i);
                intent.putExtra("TRUYEN", truyen);
                startActivity(intent);
            }
        });
        lvTruyen.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivityDSTruyen.this, ActivityXuLyTruyen.class);
                Truyen truyen = adapterTruyen.getItem(i);
                intent.putExtra("TRUYEN", truyen);
                startActivity(intent);
                return true;
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
                Intent i = new Intent(ActivityDSTruyen.this, ActivityAbout.class);
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