package com.tutorials.hp.realmgridviewmulticolumn;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.tutorials.hp.realmgridviewmulticolumn.m_Realm.RealmHelper;
import com.tutorials.hp.realmgridviewmulticolumn.m_Realm.Spacecraft;
import com.tutorials.hp.realmgridviewmulticolumn.m_UI.CustomAdapter;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    RealmChangeListener realmChangeListener;
    GridView gv;
    EditText nameEditTxt,propEditTxt,descEditTxt;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        gv= (GridView) findViewById(R.id.gv);

        //SETUP REALM
        realm=Realm.getDefaultInstance();


        //RETRIEVE
        final RealmHelper helper=new RealmHelper(realm);
        helper.retrieveFromDB();

        //SETUP ADAPTER
        adapter=new CustomAdapter(this,helper.justRefresh());
        gv.setAdapter(adapter);

        //DETECT DATA CHANGES
       realmChangeListener=new RealmChangeListener() {
           @Override
           public void onChange() {
               adapter=new CustomAdapter(MainActivity.this,helper.justRefresh());
               gv.setAdapter(adapter);
           }
       };

        //ADD IT TO REALM
        realm.addChangeListener(realmChangeListener);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaInputDialog();
            }
        });
    }

    //SAVE
    private void displaInputDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Save to Ream DB");
        d.setContentView(R.layout.input_dialog);

        //EDITTEXTS
        nameEditTxt= (EditText) d.findViewById(R.id.nameEditText);
        propEditTxt= (EditText) d.findViewById(R.id.propellantEditText);
        descEditTxt= (EditText) d.findViewById(R.id.descEditText);
        Button saveBtn= (Button) d.findViewById(R.id.saveBtn);

        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //GET DATA
               String name=nameEditTxt.getText().toString();
                String propellant=propEditTxt.getText().toString();
                String desc=descEditTxt.getText().toString();

                if(name != null && name.length()>0)
                {

                    Spacecraft s=new Spacecraft();
                    s.setName(name);
                    s.setPropellant(propellant);
                    s.setDescription(desc);

                    RealmHelper helper=new RealmHelper(realm);
                    if(helper.save(s))
                    {
                        nameEditTxt.setText("");
                        propEditTxt.setText("");
                        descEditTxt.setText("");

                    }else {
                        Toast.makeText(MainActivity.this,"Not accepted Input",Toast.LENGTH_SHORT).show();

                    }



                }else {
                    Toast.makeText(MainActivity.this,"Name must not be empty",Toast.LENGTH_SHORT).show();
                }
            }
        });

        d.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }
}











