package com.kpi.mst.domain;

public class StatisticsDTO {

    double time;

    int numberMatrix;

    int sizeMatrix;

    String diphazone;

    public StatisticsDTO(double time, int numberMatrix, int sizeMatrix, String diphazone) {
        this.time = time;
        this.numberMatrix = numberMatrix;
        this.sizeMatrix = sizeMatrix;
        this.diphazone = diphazone;
    }

    public String getDiphazone() {
        return diphazone;
    }

    public void setDiphazone(String diphazone) {
        this.diphazone = diphazone;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getNumberMatrix() {
        return numberMatrix;
    }

    public void setNumberMatrix(int numberMatrix) {
        this.numberMatrix = numberMatrix;
    }

    public int getSizeMatrix() {
        return sizeMatrix;
    }

    public void setSizeMatrix(int sizeMatrix) {
        this.sizeMatrix = sizeMatrix;
    }
}
