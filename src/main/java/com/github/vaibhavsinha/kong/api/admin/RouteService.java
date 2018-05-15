package com.github.vaibhavsinha.kong.api.admin;

import com.github.vaibhavsinha.kong.model.admin.service.RouteList;

public interface RouteService
{
    void deleteRoute(String nameOrId);

    RouteList listRoutes();
}
