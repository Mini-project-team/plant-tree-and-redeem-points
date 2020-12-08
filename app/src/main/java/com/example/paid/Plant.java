package com.example.paid;

public class Plant {
    String MailID, PlantName, PlantPictures;
    int Coins;

    public Plant(){

    }

    public String getPlantName() {
        return PlantName;
    }

    public void setPlantName(String plantName) {
        PlantName = plantName;
    }

    public String getMailID() {
        return MailID;
    }

    public void setMailID(String mailID) {
        MailID = mailID;
    }

    public String getPlantPictures() {
        return PlantPictures;
    }

    public void setPlantPictures(String plantPictures) {
        PlantPictures = plantPictures;
    }

    public int getCoins() {
        return Coins;
    }

    public void setCoins(int coins) {
        Coins = coins;
    }

    public Plant(String Mail, String name, String num, int coin){
        MailID = Mail;
        PlantName = name;
        PlantPictures = num;
        Coins = coin;
    }
}
