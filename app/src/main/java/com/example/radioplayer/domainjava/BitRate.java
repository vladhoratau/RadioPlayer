package com.example.radioplayer.domainjava;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

public class BitRate {

    @SerializedName("target")
    private Integer target;

    // Constructor
    public BitRate(Integer target) {
        this.target = target;
    }

    // Getter
    public Integer getTarget() {
        return target;
    }

    // Setter
    public void setTarget(Integer target) {
        this.target = target;
    }

    // toString method
    @NonNull
    @Override
    public String toString() {
        return "BitRate{" +
                "target=" + target +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitRate bitRate = (BitRate) o;
        return Objects.equals(target, bitRate.target);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(target);
    }
}