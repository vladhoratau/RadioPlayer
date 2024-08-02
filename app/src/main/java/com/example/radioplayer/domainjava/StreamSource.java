package com.example.radioplayer.domainjava;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

public class StreamSource {

    @SerializedName("url")
    private String url;

    @SerializedName("mimeValue")
    private String mimeValue;

    // Constructor
    public StreamSource(String url, String mimeValue) {
        this.url = url;
        this.mimeValue = mimeValue;
    }

    // Getter for url
    public String getUrl() {
        return url;
    }

    // Setter for url
    public void setUrl(String url) {
        this.url = url;
    }

    // Getter for mimeValue
    public String getMimeValue() {
        return mimeValue;
    }

    // Setter for mimeValue
    public void setMimeValue(String mimeValue) {
        this.mimeValue = mimeValue;
    }

    // toString method
    @Override
    public String toString() {
        return "StreamSource{" +
                "url='" + url + '\'' +
                ", mimeValue='" + mimeValue + '\'' +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreamSource that = (StreamSource) o;
        return Objects.equals(url, that.url) &&
                Objects.equals(mimeValue, that.mimeValue);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(url, mimeValue);
    }
}
