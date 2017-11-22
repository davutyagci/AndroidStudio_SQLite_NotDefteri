package com.theghost.notdefteri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class YeniNotEkle extends AppCompatActivity {

    VeriTabani veriTabani;
    public EditText notyaz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_not_ekle);
        veriTabani = new VeriTabani(this);
        notyaz = (EditText)findViewById(R.id.et_notyaz);
    }

    public void BtNotKayit(View v){
        boolean kontrol = veriTabani.NotKayit(notyaz.getText().toString());
        if (kontrol == true)
        { Toast.makeText(YeniNotEkle.this, "Not Kaydedildi.", Toast.LENGTH_SHORT).show(); }
        else { Toast.makeText(YeniNotEkle.this, "Kayıt Başarısız!", Toast.LENGTH_SHORT).show(); }
    }

    public void BnNotlarGit(View v) {
        Intent git = new Intent(YeniNotEkle.this, NotListele.class);
        startActivity(git);
    }
}
