package com.example.thuchanhck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class SanPham extends AppCompatActivity {
    EditText edtMaSo,edtTenSanPham,edtDonViTinh,edtDonGia,edtMaSX2;
    Button btnSelect2,btnSave2,btnDelete2,btnUpdate2;

    DatabaseHelper databaseHelper;
    GridView gv_display_sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);

        edtMaSo = findViewById(R.id.edtMaSo);
        edtTenSanPham = findViewById(R.id.edtTenSanPham);
        edtDonViTinh = findViewById(R.id.edtDonViTinh);
        edtDonGia = findViewById(R.id.edtDonGia);
        edtMaSX2 = findViewById(R.id.edtMaSX2);

        btnSelect2 = findViewById(R.id.btnSelect2);
        btnSave2 = findViewById(R.id.btnSave2);
        btnDelete2 = findViewById(R.id.btnDelete2);
        btnUpdate2 = findViewById(R.id.btnUpdate2);

        databaseHelper = new DatabaseHelper(this);
        gv_display_sp = findViewById(R.id.gv_display_sp);

        Save();
        Select();
        Delete();
        Update();

        gv_display_sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SP sp = databaseHelper.getAllSPByID(i+1);
                edtMaSo.setText(sp.getMaSo()+"");
                edtTenSanPham.setText(sp.getTen());
                edtDonViTinh.setText(sp.getDVT());
                edtDonGia.setText(sp.getDonGia());
                edtMaSX2.setText(sp.getMaSX()+"");
            }
        });
    }

    private void Update() {
        btnUpdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(edtMaSo.getText().toString());
                if(databaseHelper.updateSP(id,edtTenSanPham.getText().toString(),edtDonViTinh.getText().toString(),edtDonGia.getText().toString(), Integer.parseInt(edtMaSX2.getText().toString())))
                    Toast.makeText(SanPham.this, "Update thanh cong", Toast.LENGTH_SHORT).show();
                Toast.makeText(SanPham.this, "Update chua thanh cong", Toast.LENGTH_SHORT).show();
                edtMaSo.setText("");
                edtTenSanPham.setText("");
                edtDonViTinh.setText("");
                edtDonGia.setText("");
                edtMaSX2.setText("");
            }
        });
    }

    private void Delete() {
        btnDelete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id =Integer.parseInt(edtMaSo.getText().toString());
                if(databaseHelper.deleteNSX(id) ==  true)
                    Toast.makeText(SanPham.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SanPham.this, "Xoa that bai", Toast.LENGTH_SHORT).show();
                edtMaSo.setText("");
            }
        });
    }

    private void Select() {
        btnSelect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<SP> spArrayList = new ArrayList<>();
                ArrayList<String> stringArrayList = new ArrayList<>();

                if(edtMaSo.getText().toString().equals(""))
                    spArrayList = databaseHelper.getAllSP();
                else
                    spArrayList.add(databaseHelper.getAllSPByID(Integer.parseInt(edtMaSo.getText().toString())));

                if(spArrayList.size()>0){
                    for(SP sp: spArrayList){
                        stringArrayList.add(sp.getMaSo()+"\t" + sp.getTen()+"\t" + sp.getDVT()+"\t"+sp.getDonGia()+"\t"+sp.getMaSX());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(SanPham.this,android.R.layout.simple_list_item_1,stringArrayList);
                    gv_display_sp.setAdapter(adapter);
                }else{
                    Toast.makeText(SanPham.this, "CSDL NULL", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Save() {
        btnSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SP sp = new SP(Integer.parseInt(edtMaSo.getText().toString()),edtTenSanPham.getText().toString(),edtDonViTinh.getText().toString(),edtDonGia.getText().toString(),Integer.parseInt(edtMaSX2.getText().toString()));
                if(databaseHelper.insertSP(sp) > 0){
                    Toast.makeText(SanPham.this, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SanPham.this, "Them Khong Thanh Cong", Toast.LENGTH_SHORT).show();
                }
                edtMaSo.setText("");
                edtTenSanPham.setText("");
                edtDonViTinh.setText("");
                edtDonGia.setText("");
                edtMaSX2.setText("");

                edtMaSo.setCursorVisible(true);
            }
        });
    }
}