package com.github.vaibhavsinha.kong.model.plugin.logging.statsd;

import lombok.Data;

import java.util.List;

@Data
public class StatsdConfig
{
    String host;
    String port;
    List<Metric> metrics;
    String prefix;
}
