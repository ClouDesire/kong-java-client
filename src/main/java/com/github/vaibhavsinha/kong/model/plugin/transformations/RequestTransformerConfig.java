package com.github.vaibhavsinha.kong.model.plugin.transformations;

import lombok.Data;

@Data
public class RequestTransformerConfig
{
    RequestParts remove;

    RequestParts rename;

    RequestParts append;

    RequestParts add;
}
