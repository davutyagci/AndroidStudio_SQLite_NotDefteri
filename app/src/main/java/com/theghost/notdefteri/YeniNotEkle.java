package com.theghost.notdefteri;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class YeniNotEkle extends AppCompatActivity {

    VeriTabani veriTabani;
    public EditText notyaz;
    TextView bugununtarihi;
    Calendar BugununTarihiCal;
    int bugununyılı, bugunungunu, bugununayı;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_not_ekle);
        veriTabani = new VeriTabani(this);
        notyaz = (EditText)findViewById(R.id.et_notyaz);
        bugununtarihi = (TextView)findViewById(R.id.tv_bugununtarihi);

        BugununTarihi();
    }
    public void BugununTarihi() {
        BugununTarihiCal = Calendar.getInstance();
        bugununyılı = BugununTarihiCal.get(Calendar.YEAR);
        bugununayı = BugununTarihiCal.get(Calendar.MONTH)+1;
        bugunungunu = BugununTarihiCal.get(Calendar.DAY_OF_MONTH);
        BugununTarihiCal.set(Calendar.MILLISECOND,0);
        bugununtarihi.setText(bugunungunu + "/" + bugununayı + "/" + bugununyılı); }

    @Override
    public void onBackPressed() {
        if (notyaz.getText().toString().trim().equals("")) {
            Intent git = new Intent(YeniNotEkle.this, NotListele.class);
            startActivity(git);
        }
        else {
            veriTabani.NotKayit(notyaz.getText().toString(), bugununtarihi.getText().toString());
            Intent git = new Intent(YeniNotEkle.this, NotListele.class);
            startActivity(git);
        }
    }

    public void BnNotlarGit(View v) {
        if (notyaz.getText().toString().trim().equals("")) {
            Intent git = new Intent(YeniNotEkle.this, NotListele.class);
            startActivity(git);
        }
        else {
            veriTabani.NotKayit(notyaz.getText().toString(), bugununtarihi.getText().toString());
            Intent git = new Intent(YeniNotEkle.this, NotListele.class);
            startActivity(git);
        }
    }
}
