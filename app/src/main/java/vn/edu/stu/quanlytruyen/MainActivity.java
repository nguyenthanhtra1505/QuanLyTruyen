package vn.edu.stu.quanlytruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btndstruyen,btndstacgia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

    }
    private void addControls(){
        btndstruyen=findViewById(R.id.btndstruyen);
        btndstacgia=findViewById(R.id.btndsnxb);
    }
    private void addEvents(){
        btndstruyen.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,ActivityDSTruyen.class);
            startActivity(intent);
        });
        btndstacgia.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,ActivityDSNXB.class);
            startActivity(intent);
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
                Intent i = new Intent(MainActivity.this, ActivityAbout.class);
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