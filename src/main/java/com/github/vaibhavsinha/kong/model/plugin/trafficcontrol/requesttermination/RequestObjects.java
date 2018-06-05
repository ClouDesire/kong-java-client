package com.github.vaibhavsinha.kong.model.plugin.trafficcontrol.requesttermination;

import lombok.Data;

@Data
public class RequestObjects
{
    String headers;

    String querystring;

    String body;
}
