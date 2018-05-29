package com.github.vaibhavsinha.kong.api.admin;

import com.github.vaibhavsinha.kong.model.admin.plugin.Plugin;
import com.github.vaibhavsinha.kong.model.admin.plugin.PluginList;
import com.github.vaibhavsinha.kong.model.admin.service.Route;
import com.github.vaibhavsinha.kong.model.admin.service.RouteList;
import com.github.vaibhavsinha.kong.model.admin.service.Service;
import com.github.vaibhavsinha.kong.model.admin.service.ServiceList;

public interface ServiceService
{
    Service createService(Service request);

    Service getService(String nameOrId);

    PluginList getServicePlugins(String nameOrId);

    Plugin createServicePlugin(String service, Plugin request);

    RouteList getServiceRoutes(String nameOrId);

    Route createServiceRoute(String service, Route request);

    void deleteService(String nameOrId);

    ServiceList listServices();
}
