package com.mira.covidbystate.Model;

import java.io.Serializable;

public class State implements Serializable {
    private static final long id = 1L;

    private String txtState;
    private String txtCasesPositive;
    private String txtCasesNegative;
    private String txtCasesProbable;
    private String txtHospitalizations;
    private String txtDeaths;
    private String txtHospitalizedCurrently;
    private String txtOnVentCurrently;
    private String txtDate;

    public State()
    {

    }

    public String getTxtState() {
        return txtState;
    }

    public void setTxtState(String txtState) {
        this.txtState = txtState;
    }

    public String getTxtCasesPositive() {
        return txtCasesPositive;
    }

    public void setTxtCasesPositive(String txtCasesPositive) {
        this.txtCasesPositive = txtCasesPositive;
    }

    public String getTxtCasesNegative() {
        return txtCasesNegative;
    }

    public void setTxtCasesNegative(String txtCasesNegative) {
        this.txtCasesNegative = txtCasesNegative;
    }

    public String getTxtCasesProbable() {
        return txtCasesProbable;
    }

    public void setTxtCasesProbable(String txtCasesProbable) {
        this.txtCasesProbable = txtCasesProbable;
    }

    public String getTxtHospitalizations() {
        return txtHospitalizations;
    }

    public void setTxtHospitalizations(String txtHospitalizations) {
        this.txtHospitalizations = txtHospitalizations;
    }

    public String getTxtDeaths() {
        return txtDeaths;
    }

    public void setTxtDeaths(String txtDeaths) {
        this.txtDeaths = txtDeaths;
    }

    public String getTxtHospitalizedCurrently() {
        return txtHospitalizedCurrently;
    }

    public void setTxtHospitalizedCurrently(String txtHospitalizedCurrently) {
        this.txtHospitalizedCurrently = txtHospitalizedCurrently;
    }

    public String getTxtOnVentCurrently() {
        return txtOnVentCurrently;
    }

    public void setTxtOnVentCurrently(String txtOnVentCurrently) {
        this.txtOnVentCurrently = txtOnVentCurrently;
    }

    public String getTxtDate() {
        return txtDate;
    }

    public void setTxtDate(String txtDate) {
        this.txtDate = txtDate;
    }
}
