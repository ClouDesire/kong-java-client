package com.github.vaibhavsinha.kong.model.plugin.transformations;

import com.github.vaibhavsinha.kong.model.plugin.trafficcontrol.requesttermination.RequestObjects;
import lombok.Data;

@Data
public class RequestTransformerConfig
{
    RequestObjects remove;

    RequestObjects rename;

    RequestObjects append;

    RequestObjects add;
}
