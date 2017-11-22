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

public class NotDuzenle extends AppCompatActivity {

    VeriTabani veriTabani;
    EditText notduzenle;
    TextView idgor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_duzenle);
        veriTabani = new VeriTabani(this);
        notduzenle = (EditText)findViewById(R.id.et_notduzenle);
        idgor = (TextView)findViewById(R.id.tv_idgor);

        String idcek= getIntent().getStringExtra("IDCEK");
        idgor.setText(idcek.toString());

        EtNotGoster();
    }

    public void EtNotGoster() {
        SQLiteDatabase dbOku = veriTabani.NotCek();
        Cursor c = dbOku.rawQuery("Select * From  "+ veriTabani.TABLO_ADI +" Where ID = "+ idgor.getText().toString() +"", null);
        StringBuilder sb = new StringBuilder();
        while (c.moveToNext()) {
            sb.append(c.getString(1));
        }
        notduzenle.setText(sb.toString());
    }

    public void BtNotlarGit(View v) {
        Intent git = new Intent(NotDuzenle.this, NotListele.class);
        startActivity(git);
    }

    public void BtNotSil(View v) {
        Integer notsil = veriTabani.NotSil(idgor.getText().toString());
        if (notsil > 0) { Toast.makeText(NotDuzenle.this, "Not Silindi.", Toast.LENGTH_SHORT).show(); }
        else { Toast.makeText(NotDuzenle.this, "Silme Başarısız!", Toast.LENGTH_SHORT).show(); }
    }

    public void BtNotGuncelle (View v) {
        boolean kontrol = veriTabani.NotGuncelle(idgor.getText().toString(), notduzenle.getText().toString());
        if (kontrol == true)
        { Toast.makeText(NotDuzenle.this, "Not Güncellendi.", Toast.LENGTH_SHORT).show(); }
        else { Toast.makeText(NotDuzenle.this, "Güncelleme Başarısız!", Toast.LENGTH_SHORT).show(); }
    }
}
