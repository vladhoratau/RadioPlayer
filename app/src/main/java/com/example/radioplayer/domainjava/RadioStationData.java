package com.example.radioplayer.domainjava;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Objects;

public class RadioStationData {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("liveStreams")
    private List<LiveStream> liveStreams;

    @SerializedName("country")
    private String country;

    @SerializedName("rpuid")
    private String rpuid;

    // Constructor
    public RadioStationData(String name, String description, List<LiveStream> liveStreams, String country, String rpuid) {
        this.name = name;
        this.description = description;
        this.liveStreams = liveStreams;
        this.country = country;
        this.rpuid = rpuid;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for liveStreams
    public List<LiveStream> getLiveStreams() {
        return liveStreams;
    }

    // Setter for liveStreams
    public void setLiveStreams(List<LiveStream> liveStreams) {
        this.liveStreams = liveStreams;
    }

    // Getter for country
    public String getCountry() {
        return country;
    }

    // Setter for country
    public void setCountry(String country) {
        this.country = country;
    }

    // Getter for rpuid
    public String getRpuid() {
        return rpuid;
    }

    // Setter for rpuid
    public void setRpuid(String rpuid) {
        this.rpuid = rpuid;
    }

    // toString method
    @Override
    public String toString() {
        return "RadioStationData{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", liveStreams=" + liveStreams +
                ", country='" + country + '\'' +
                ", rpuid='" + rpuid + '\'' +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RadioStationData that = (RadioStationData) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(liveStreams, that.liveStreams) &&
                Objects.equals(country, that.country) &&
                Objects.equals(rpuid, that.rpuid);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(name, description, liveStreams, country, rpuid);
    }
}

