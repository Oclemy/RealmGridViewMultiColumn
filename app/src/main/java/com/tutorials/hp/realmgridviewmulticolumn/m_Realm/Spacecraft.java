package com.tutorials.hp.realmgridviewmulticolumn.m_Realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Oclemy on 6/15/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class Spacecraft extends RealmObject {

    @PrimaryKey
    private String name;
    private String propellant,description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPropellant() {
        return propellant;
    }

    public void setPropellant(String propellant) {
        this.propellant = propellant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
