package com.github.vaibhavsinha.kong.api.admin;

import com.github.vaibhavsinha.kong.model.admin.plugin.Plugin;
import com.github.vaibhavsinha.kong.model.admin.service.Route;
import com.github.vaibhavsinha.kong.model.admin.service.RouteList;
import com.github.vaibhavsinha.kong.model.admin.service.Service;

public interface ServiceService
{
    Service createService(Service request);

    Service getService(String nameOrId);

    Plugin createServicePlugin(String service, Plugin request);

    RouteList getServiceRoutes(String nameOrId);

    Route createServiceRoute(String service, Route request);
}
