package com.example.thuchanhck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int temp = item.getItemId();
        switch (temp){
            case R.id.mnSanPham:
                Intent intent = new Intent(MainActivity.this,SanPham.class);
                startActivity(intent);
                break;
            case R.id.mnNhaSanXuat:
                Intent intent2 = new Intent(MainActivity.this,NhaSanXuat.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}