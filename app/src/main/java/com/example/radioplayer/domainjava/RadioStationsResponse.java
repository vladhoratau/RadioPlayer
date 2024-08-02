package com.example.radioplayer.domainjava;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Objects;

public class RadioStationsResponse {

    @SerializedName("data")
    private List<RadioStationData> data;

    @SerializedName("meta")
    private MetaData meta;

    // Constructor
    public RadioStationsResponse(List<RadioStationData> data, MetaData meta) {
        this.data = data;
        this.meta = meta;
    }

    // Getter for data
    public List<RadioStationData> getData() {
        return data;
    }

    // Setter for data
    public void setData(List<RadioStationData> data) {
        this.data = data;
    }

    // Getter for meta
    public MetaData getMeta() {
        return meta;
    }

    // Setter for meta
    public void setMeta(MetaData meta) {
        this.meta = meta;
    }

    // toString method
    @Override
    public String toString() {
        return "RadioStationsResponse{" +
                "data=" + data +
                ", meta=" + meta +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RadioStationsResponse that = (RadioStationsResponse) o;
        return Objects.equals(data, that.data) &&
                Objects.equals(meta, that.meta);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(data, meta);
    }
}

