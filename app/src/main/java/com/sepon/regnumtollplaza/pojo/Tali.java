package com.sepon.regnumtollplaza.pojo;

public class Tali {

    String taliName;
    String taliCount;
    String taliCountedTaka;



    public Tali( String taliName, String taliCount, String taliCountedTaka) {

        this.taliName = taliName;
        this.taliCount = taliCount;
        this.taliCountedTaka = taliCountedTaka;
    }


    public String getTaliName() {
        return taliName;
    }

    public void setTaliName(String taliName) {
        this.taliName = taliName;
    }

    public String getTaliCount() {
        return taliCount;
    }

    public void setTaliCount(String taliCount) {
        this.taliCount = taliCount;
    }

    public String getTaliCountedTaka() {
        return taliCountedTaka;
    }

    public void setTaliCountedTaka(String taliCountedTaka) {
        this.taliCountedTaka = taliCountedTaka;
    }
}
