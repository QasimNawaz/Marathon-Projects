package com.example.qasimnawaz.campusrecruitmentsystem.Modeules;

/**
 * Created by Qasim Nawaz on 1/20/2017.
 */

public class CompanyModule {

    private String uuid;
    private String cmpName;
    private String smpEstablish;
    private String cmpEmail;
    private String cmpContact;
    private String cmpHR;
    private String usrName;

    public CompanyModule() {
    }

    public CompanyModule(String uuid, String cmpName, String smpEstablish, String cmpEmail, String cmpContact, String cmpHR, String usrName) {
        this.uuid = uuid;
        this.cmpName = cmpName;
        this.smpEstablish = smpEstablish;
        this.cmpEmail = cmpEmail;
        this.cmpContact = cmpContact;
        this.cmpHR = cmpHR;
        this.usrName = usrName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCmpName() {
        return cmpName;
    }

    public void setCmpName(String cmpName) {
        this.cmpName = cmpName;
    }

    public String getSmpEstablish() {
        return smpEstablish;
    }

    public void setSmpEstablish(String smpEstablish) {
        this.smpEstablish = smpEstablish;
    }

    public String getCmpEmail() {
        return cmpEmail;
    }

    public void setCmpEmail(String cmpEmail) {
        this.cmpEmail = cmpEmail;
    }

    public String getCmpContact() {
        return cmpContact;
    }

    public void setCmpContact(String cmpContact) {
        this.cmpContact = cmpContact;
    }

    public String getCmpHR() {
        return cmpHR;
    }

    public void setCmpHR(String cmpHR) {
        this.cmpHR = cmpHR;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }
}
