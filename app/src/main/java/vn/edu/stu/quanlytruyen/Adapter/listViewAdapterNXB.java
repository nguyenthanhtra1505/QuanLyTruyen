package vn.edu.stu.quanlytruyen.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import vn.edu.stu.quanlytruyen.R;
import vn.edu.stu.quanlytruyen.data.NhaXuatBanDataSource;
import vn.edu.stu.quanlytruyen.data.TruyenDataSource;
import vn.edu.stu.quanlytruyen.model.NhaXuatBan;
import vn.edu.stu.quanlytruyen.model.Truyen;

public class listViewAdapterNXB extends ArrayAdapter<NhaXuatBan> {
    List<NhaXuatBan> dsNXB;
    Activity context;
    private NhaXuatBanDataSource datasource;
    private TruyenDataSource datasourceTruyen;
    int resource;
    public listViewAdapterNXB(@NonNull Activity context, int resource, @NonNull List<NhaXuatBan> objects){
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.dsNXB = objects;
    }
    @NonNull
    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        View viewNXB;
        Button btnXoa;
        if(convertview==null){
            viewNXB=View.inflate(parent.getContext(), R.layout.activity_itemds,null);
        }else viewNXB=convertview;
        NhaXuatBan nhaXuatBan= (NhaXuatBan) getItem(position);
        ((TextView)viewNXB.findViewById(R.id.item_manxb)).setText(nhaXuatBan.getMaNXB()+"");
        ((TextView)viewNXB.findViewById(R.id.item_tennxb)).setText(nhaXuatBan.getTenNXB());
        btnXoa = viewNXB.findViewById(R.id.btntxoanxb);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Bán có muốn xóa nhà xuất bản này!");
                alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        xuLyXoa(position);
                    }

                });
                alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //không làm gì
                    }
                });
                alertDialogBuilder.show();
            }

        });
        return viewNXB;
    }
    private void xuLyXoa(int position) {
        datasource = new NhaXuatBanDataSource(context);
        datasource.open();
        NhaXuatBan nhaXuatBan=this.dsNXB.get(position);
        boolean co=true;
        datasourceTruyen = new TruyenDataSource(context);
        datasourceTruyen.open();
        List<Truyen> truyens=datasourceTruyen.getAllTruyens();

        if(datasourceTruyen.getTruyens(nhaXuatBan.getTenNXB())){
            AlertDialog.Builder DialogBuilder = new AlertDialog.Builder(context);
            DialogBuilder.setMessage("Xóa Không thành công truyện đã tồn tại");
            DialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }

            });
            DialogBuilder.show();
            co=false;
        }
        if(co){
            datasource.deleteNhaXuatBan(nhaXuatBan);
            this.dsNXB.remove(position);
            this.notifyDataSetChanged();
        }
    }

}
