package com.github.vaibhavsinha.kong.model.admin.service;

import com.github.vaibhavsinha.kong.model.common.AbstractEntityList;
import lombok.Data;

import java.util.List;

@Data
public class ServiceList extends AbstractEntityList {
    Long total;
    String next;
    List<Service> data;
}
