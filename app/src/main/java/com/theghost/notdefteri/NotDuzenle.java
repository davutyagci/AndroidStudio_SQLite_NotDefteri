package com.theghost.notdefteri;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class NotDuzenle extends AppCompatActivity {

    VeriTabani veriTabani;
    EditText notduzenle;
    TextView idgor, tarihduzenle;
    Calendar TarihDuzenleCal;
    int bugununyılı, bugunungunu, bugununayı;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_duzenle);
        veriTabani = new VeriTabani(this);
        notduzenle = (EditText)findViewById(R.id.et_notduzenle);
        idgor = (TextView)findViewById(R.id.tv_idgor);
        tarihduzenle = (TextView)findViewById(R.id.tv_tarihduzenle);

        String idcek = getIntent().getStringExtra("IDCEK");
        idgor.setText(idcek.toString());

        EtNotGoster();
        TarihDuzenle();
    }

    public void EtNotGoster() {
        SQLiteDatabase dbOku = veriTabani.NotCek();
        Cursor c = dbOku.rawQuery("Select * From  "+ veriTabani.TABLO_ADI +" Where ID = "+ idgor.getText().toString() +"", null);
        StringBuilder sbNot = new StringBuilder();
        while (c.moveToNext()) {
            sbNot.append(c.getString(1));

        }
        notduzenle.setText(sbNot.toString());
    }

    public void TarihDuzenle() {
        TarihDuzenleCal = Calendar.getInstance();
        bugununyılı = TarihDuzenleCal.get(Calendar.YEAR);
        bugununayı = TarihDuzenleCal.get(Calendar.MONTH)+1;
        bugunungunu = TarihDuzenleCal.get(Calendar.DAY_OF_MONTH);
        TarihDuzenleCal.set(Calendar.MILLISECOND,0);
        tarihduzenle.setText(bugunungunu + "/" + bugununayı + "/" + bugununyılı); }

    @Override
    public void onBackPressed() {
        if (notduzenle.getText().toString().trim().equals("")) {
            veriTabani.NotSil(idgor.getText().toString());
        }
        else
        { veriTabani.NotGuncelle(idgor.getText().toString(), notduzenle.getText().toString(), tarihduzenle.getText().toString()); }

        Intent git = new Intent(NotDuzenle.this, NotListele.class);
        startActivity(git);
    }

    public void BtNotlarGit(View v) {
        if (notduzenle.getText().toString().trim().equals("")) {
            veriTabani.NotSil(idgor.getText().toString());
        }
        else
        { veriTabani.NotGuncelle(idgor.getText().toString(), notduzenle.getText().toString(), tarihduzenle.getText().toString()); }

        Intent git = new Intent(NotDuzenle.this, NotListele.class);
        startActivity(git);
    }

    public void BtNotSil(View v) {
        veriTabani.NotSil(idgor.getText().toString());

        Intent git = new Intent(NotDuzenle.this, NotListele.class);
        startActivity(git);
    }
}
