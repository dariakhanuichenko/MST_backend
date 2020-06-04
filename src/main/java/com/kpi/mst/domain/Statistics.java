package com.kpi.mst.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "statistics")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    Long id;

    @NotNull
    @Column(name = "time", nullable = false, unique = false)
    double time;

    @NotNull
    @Column(name = "number_matrix", nullable = false, unique = false)
    int numberMatrix;

    @NotNull
    @Column(name = "size_matrix", nullable = false, unique = false)
    int sizeMatrix;

    @NotNull
    @Column(name = "additional", nullable = false, unique = false)
    boolean additional;

    @NotNull
    @Size(max = 255)
    @Column(name = "diaphazone",  length = 255)
    String diaphazone;


    public Statistics() {
    }

    public Statistics(@NotNull double time, @NotNull int numberMatrix, @NotNull int sizeMatrix, @NotNull boolean additional, @NotNull String diaphazone) {
        this.time = time;
        this.numberMatrix = numberMatrix;
        this.sizeMatrix = sizeMatrix;
        this.additional = additional;
        this.diaphazone = diaphazone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isAdditional() {
        return additional;
    }

    public void setAdditional(boolean additional) {
        this.additional = additional;
    }

    public String getDiaphazone() {
        return diaphazone;
    }

    public void setDiaphazone(String diaphazone) {
        this.diaphazone = diaphazone;
    }
}
