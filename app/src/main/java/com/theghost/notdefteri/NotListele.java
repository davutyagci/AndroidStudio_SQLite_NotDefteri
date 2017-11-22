package com.theghost.notdefteri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NotListele extends AppCompatActivity {

    VeriTabani veriTabani;
    ListView notListele, idListele;
    ArrayList<String> NotIdListele, NotListele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_listele);
        veriTabani = new VeriTabani(this);
        notListele = (ListView)findViewById(R.id.lv_notListele);
        idListele = (ListView)findViewById(R.id.lv_idListele);

        NotIdListele = veriTabani.NotIdListele();
        NotListele = veriTabani.NotListele();

        Not_Id_ListeleAdapter Not_Id_ListeleAdapter = new Not_Id_ListeleAdapter();
        idListele.setAdapter(Not_Id_ListeleAdapter);

        idListele.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent git = new Intent(NotListele.this, NotDuzenle.class);
                git.putExtra("IDCEK", idListele.getItemAtPosition(position).toString());
                startActivity(git);
            }
        });
    }

    class Not_Id_ListeleAdapter extends BaseAdapter {

        @Override
        public int getCount() { return NotIdListele.size(); }

        @Override
        public Object getItem(int position) { return NotIdListele.get(position); }

        @Override
        public long getItemId(int position) { return 0; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.custom_listview, null);
            TextView tv_idListele = (TextView)convertView.findViewById(R.id.tv_idListele);
            TextView tv_notListele = (TextView)convertView.findViewById(R.id.tv_notListele);

            tv_idListele.setText(NotIdListele.get(position));
            tv_notListele.setText(NotListele.get(position));

            return convertView;
        }
    }

    public void LvNotListele() {
        ArrayList<String> al = veriTabani.NotListele();
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);
        notListele.setAdapter(ad);
    }

    public void LvNotIdListele() {
        ArrayList<String> al = veriTabani.NotIdListele();
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);
        idListele.setAdapter(ad);
    }

    public void BtYeniNotGit(View v) {
        Intent git = new Intent(NotListele.this, YeniNotEkle.class);
        startActivity(git);
    }
}
