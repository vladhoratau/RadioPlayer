package com.example.radioplayer.domainjava;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

public class MetaData {

    @SerializedName("nesting")
    private Boolean nesting;

    @SerializedName("paginated")
    private Boolean paginated;

    @SerializedName("dataType")
    private String dataType;

    @SerializedName("count")
    private Integer count;

    @SerializedName("fromCache")
    private Boolean fromCache;

    @SerializedName("cacheExpiresAt")
    private Long cacheExpiresAt;

    // Constructor
    public MetaData(Boolean nesting, Boolean paginated, String dataType, Integer count, Boolean fromCache, Long cacheExpiresAt) {
        this.nesting = nesting;
        this.paginated = paginated;
        this.dataType = dataType;
        this.count = count;
        this.fromCache = fromCache;
        this.cacheExpiresAt = cacheExpiresAt;
    }

    // Getter for nesting
    public Boolean getNesting() {
        return nesting;
    }

    // Setter for nesting
    public void setNesting(Boolean nesting) {
        this.nesting = nesting;
    }

    // Getter for paginated
    public Boolean getPaginated() {
        return paginated;
    }

    // Setter for paginated
    public void setPaginated(Boolean paginated) {
        this.paginated = paginated;
    }

    // Getter for dataType
    public String getDataType() {
        return dataType;
    }

    // Setter for dataType
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    // Getter for count
    public Integer getCount() {
        return count;
    }

    // Setter for count
    public void setCount(Integer count) {
        this.count = count;
    }

    // Getter for fromCache
    public Boolean getFromCache() {
        return fromCache;
    }

    // Setter for fromCache
    public void setFromCache(Boolean fromCache) {
        this.fromCache = fromCache;
    }

    // Getter for cacheExpiresAt
    public Long getCacheExpiresAt() {
        return cacheExpiresAt;
    }

    // Setter for cacheExpiresAt
    public void setCacheExpiresAt(Long cacheExpiresAt) {
        this.cacheExpiresAt = cacheExpiresAt;
    }

    // toString method
    @Override
    public String toString() {
        return "MetaData{" +
                "nesting=" + nesting +
                ", paginated=" + paginated +
                ", dataType='" + dataType + '\'' +
                ", count=" + count +
                ", fromCache=" + fromCache +
                ", cacheExpiresAt=" + cacheExpiresAt +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetaData metaData = (MetaData) o;
        return Objects.equals(nesting, metaData.nesting) &&
                Objects.equals(paginated, metaData.paginated) &&
                Objects.equals(dataType, metaData.dataType) &&
                Objects.equals(count, metaData.count) &&
                Objects.equals(fromCache, metaData.fromCache) &&
                Objects.equals(cacheExpiresAt, metaData.cacheExpiresAt);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(nesting, paginated, dataType, count, fromCache, cacheExpiresAt);
    }
}

