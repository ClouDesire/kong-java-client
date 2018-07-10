package com.github.vaibhavsinha.kong.model.plugin.logging.statsd;

import lombok.Data;

@Data
public class StatsdConfig
{
    String host;
    String port;
}
