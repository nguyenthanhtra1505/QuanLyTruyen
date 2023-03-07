package vn.edu.stu.quanlytruyen.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import vn.edu.stu.quanlytruyen.R;
import vn.edu.stu.quanlytruyen.data.TruyenDataSource;
import vn.edu.stu.quanlytruyen.model.Truyen;

public class listViewAdapterTruyen extends ArrayAdapter<Truyen> {
    Activity context;
    int resource;
    private List<Truyen> dstruyen;
    private TruyenDataSource datasource;

    public listViewAdapterTruyen(@NonNull Activity context, int resource, @NonNull List<Truyen> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.dstruyen = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View viewTruyen = inflater.inflate(this.resource, null);
        TextView matruyen = viewTruyen.findViewById(R.id.tr_matruyen);
        TextView tentruyen = viewTruyen.findViewById(R.id.tr_tentruyen);
        TextView tennxb = viewTruyen.findViewById(R.id.tr_tennxb);
        ImageView imgTruyen = viewTruyen.findViewById(R.id.img_itemdstruyen);
        Truyen truyen = (Truyen) getItem(position);
        byte[] bt = Base64.decode(truyen.getHinhTruyen(), 0);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bt, 0, bt.length, null);
        matruyen.setText(truyen.getMaTruyen() + "");
        tentruyen.setText(truyen.getTenTruyen());
        tennxb.setText(truyen.getNhaXuatBan().toString());
        imgTruyen.setImageBitmap(bitmap);
        TextView btnXoa = viewTruyen.findViewById(R.id.btnXoaTruyen);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyXoa(position);
            }
        });
        return viewTruyen;
    }

    public void xuLyXoa(int position) {
        datasource = new TruyenDataSource(context);
        datasource.open();
        Truyen truyen = this.dstruyen.get(position);
        datasource.deleteTruyen(truyen);
        this.dstruyen.remove(position);
        this.notifyDataSetChanged();
    }
}
