package com.github.vaibhavsinha.kong.model.admin.service;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Route
{
    private String id;

    private List<String> hosts;

    private List<String> paths;

    @SerializedName("strip_path")
    private boolean stripPath;
}
