package com.example.Model;

public class Lens {

    private String theMake;
    private double maxAperture;
    private int focalLens;

    private LensManager lenses = LensManager.getInstance();

    public Lens(String theMake, double maxAperture, int focalLens) {
        this.theMake = theMake;
        this.maxAperture = maxAperture;
        this.focalLens = focalLens;
    }

    public String getTheMake() {
        return theMake;
    }

    public void setTheMake(String theMake) {
        this.theMake = theMake;
    }

    public double getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(double maxAperture) {
        this.maxAperture = maxAperture;
    }

    public int getFocalLens() {
        return focalLens;
    }

    public void setFocalLens(int focalLens) {
        this.focalLens =  focalLens;
    }

    @Override
    public String toString() {
        return theMake + " " + focalLens +
                "mm " + "F" + maxAperture;
    }


}
