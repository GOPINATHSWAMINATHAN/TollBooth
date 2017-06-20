package com.gopinath.tollbooth;

/**
 * Created by gopinath on 28/03/17.
 */

public class SaveData {
    String source, destination, vehicletype, vehicleno, tripTypes;int totalamount;
    String phoneno;

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    public String getTripTypes() {
        return tripTypes;
    }

    public void setTripTypes(String tripTypes) {
        this.tripTypes = tripTypes;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    SaveData() {

    }

    public SaveData(String source, String destination, String vehicletype, String vehicleno, String tripTypes, String phoneno,int totalamount) {
        this.source = source;
        this.destination = destination;
        this.vehicletype = vehicletype;
        this.vehicleno = vehicleno;
        this.tripTypes = tripTypes;
        this.phoneno = phoneno;
        this.totalamount=totalamount;
    }


}
