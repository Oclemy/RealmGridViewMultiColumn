package com.tutorials.hp.realmgridviewmulticolumn;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Oclemy on 6/15/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //SETUP REALM GLOBALLY
        RealmConfiguration config=new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
    }
}
