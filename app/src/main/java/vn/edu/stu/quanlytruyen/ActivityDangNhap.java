package vn.edu.stu.quanlytruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityDangNhap extends AppCompatActivity {
    EditText tendn;
    EditText pass;
    Button btndangnhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        tendn = findViewById(R.id.editt_input);
        pass = findViewById(R.id.editt_input2);
        btndangnhap = findViewById(R.id.buttondangnhap);
        btndangnhap.setOnClickListener(v -> {
            if (tendn.getText().toString().equals("thanhtra") && pass.getText().toString().equals("123")) {
                    Intent intent = new Intent(ActivityDangNhap.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Nhập sai vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
        });
    }
}