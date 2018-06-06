package com.github.vaibhavsinha.kong.model.plugin.transformations;

import lombok.Data;

@Data
public class RequestParts
{
    String headers;

    String querystring;

    String body;
}
