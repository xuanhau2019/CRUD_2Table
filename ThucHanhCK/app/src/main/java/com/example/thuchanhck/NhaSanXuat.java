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

public class NhaSanXuat extends AppCompatActivity {

    EditText edtMaSX,edtTenSX,edtDiaChi;
    Button btnSelect,btnSave,btnDelete,btnUpdate;
    DatabaseHelper databaseHelper;
    GridView gv_display_nsx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nha_san_xuat);

        edtMaSX = findViewById(R.id.edtMaSX);
        edtTenSX = findViewById(R.id.edtTenSX);
        edtDiaChi = findViewById(R.id.edtDiaChi);

        btnSelect = findViewById(R.id.btnSelect);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        gv_display_nsx = findViewById(R.id.gv_display_nsx);

        databaseHelper = new DatabaseHelper(this);

        Save();

        Select();

        Delete();

        Update();

        gv_display_nsx.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NSX nsx = databaseHelper.getAllNSXByID(i+1);
                edtMaSX.setText(nsx.getMaSX()+"");
                edtTenSX.setText(nsx.getTen());
                edtDiaChi.setText(nsx.getDiaChi());
            }
        });
    }

    private void Update() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(edtMaSX.getText().toString());
                if(databaseHelper.updateNSX(id,edtTenSX.getText().toString(),edtDiaChi.getText().toString()))
                    Toast.makeText(NhaSanXuat.this, "Update thanh cong", Toast.LENGTH_SHORT).show();
                Toast.makeText(NhaSanXuat.this, "Update chua thanh cong", Toast.LENGTH_SHORT).show();
                edtMaSX.setText("");
                edtTenSX.setText("");
                edtDiaChi.setText("");
            }
        });
    }

    private void Delete() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id =Integer.parseInt(edtMaSX.getText().toString());
                if(databaseHelper.deleteNSX(id) ==  true)
                    Toast.makeText(NhaSanXuat.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(NhaSanXuat.this, "Xoa that bai", Toast.LENGTH_SHORT).show();
                edtMaSX.setText("");
            }
        });
    }

    private void Save() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NSX nsx = new NSX(Integer.parseInt(edtMaSX.getText().toString()),edtTenSX.getText().toString(),edtDiaChi.getText().toString());
                if(databaseHelper.insertNhaSanXuat(nsx) > 0){
                    Toast.makeText(NhaSanXuat.this, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(NhaSanXuat.this, "Them Khong Thanh Cong", Toast.LENGTH_SHORT).show();
                }
                edtMaSX.setText("");
                edtTenSX.setText("");
                edtDiaChi.setText("");

                edtMaSX.setCursorVisible(true);
            }
        });
    }

    private void Select() {
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<NSX> nsxArrayList = new ArrayList<>();
                ArrayList<String> stringArrayList = new ArrayList<>();

                if(edtMaSX.getText().toString().equals(""))
                    nsxArrayList = databaseHelper.getAllNSX();
                else
                    nsxArrayList.add(databaseHelper.getAllNSXByID(Integer.parseInt(edtMaSX.getText().toString())));

                if(nsxArrayList.size()>0){
                    for(NSX nsx: nsxArrayList){
                        stringArrayList.add(nsx.getMaSX()+"\t" + nsx.getTen()+"\t"+nsx.getDiaChi());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(NhaSanXuat.this,android.R.layout.simple_list_item_1,stringArrayList);
                    gv_display_nsx.setAdapter(adapter);
                }else{
                    Toast.makeText(NhaSanXuat.this, "CSDL NULL", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}