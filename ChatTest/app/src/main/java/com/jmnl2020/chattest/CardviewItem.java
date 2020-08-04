package com.jmnl2020.chattest;

public class CardviewItem {

    String cvTitle;
    String cvName;
    String cvMsg;

    String cvImgfile;

    public CardviewItem() {
    }

    public CardviewItem(String cvTitle, String cvName, String cvMsg, String cvImgfile) {
        this.cvTitle = cvTitle;
        this.cvName = cvName;
        this.cvMsg = cvMsg;
        this.cvImgfile = cvImgfile;
    }

    //getter & setter


    public String getCvTitle() {
        return cvTitle;
    }

    public void setCvTitle(String cvTitle) {
        this.cvTitle = cvTitle;
    }

    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
    }

    public String getCvMsg() {
        return cvMsg;
    }

    public void setCvMsg(String cvMsg) {
        this.cvMsg = cvMsg;
    }

    public String getCvImgfile() {
        return cvImgfile;
    }

    public void setCvImgfile(String cvImgfile) {
        this.cvImgfile = cvImgfile;
    }
}
