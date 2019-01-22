package com.example.elahi.aplicacionened.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class permiso {
    public permiso(boolean acceso) {
        this.acceso = acceso;
    }
    @SerializedName("acceso")
    @Expose
    private boolean acceso;

    public boolean isAcceso() {
        return acceso;
    }

    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }


}
