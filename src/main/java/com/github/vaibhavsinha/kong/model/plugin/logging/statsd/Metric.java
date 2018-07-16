package com.github.vaibhavsinha.kong.model.plugin.logging.statsd;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Metric
{
    String name;

    @SerializedName("stat_type")
    String statType;

    @SerializedName("sample_rate")
    Integer sampleRate;

    @SerializedName("customer_identifier")
    String customerIdentifier;
}
