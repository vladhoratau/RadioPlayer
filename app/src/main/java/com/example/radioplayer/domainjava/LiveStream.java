package com.example.radioplayer.domainjava;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

public class LiveStream {

    @SerializedName("streamSource")
    private StreamSource streamSource;

    @SerializedName("bitRate")
    private BitRate bitRate;

    // Constructor
    public LiveStream(StreamSource streamSource, BitRate bitRate) {
        this.streamSource = streamSource;
        this.bitRate = bitRate;
    }

    // Getter for streamSource
    public StreamSource getStreamSource() {
        return streamSource;
    }

    // Setter for streamSource
    public void setStreamSource(StreamSource streamSource) {
        this.streamSource = streamSource;
    }

    // Getter for bitRate
    public BitRate getBitRate() {
        return bitRate;
    }

    // Setter for bitRate
    public void setBitRate(BitRate bitRate) {
        this.bitRate = bitRate;
    }

    // toString method
    @Override
    public String toString() {
        return "LiveStream{" +
                "streamSource=" + streamSource +
                ", bitRate=" + bitRate +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LiveStream that = (LiveStream) o;
        return Objects.equals(streamSource, that.streamSource) &&
                Objects.equals(bitRate, that.bitRate);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(streamSource, bitRate);
    }
}
