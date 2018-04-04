package com.github.vaibhavsinha.kong.api.admin;

import com.github.vaibhavsinha.kong.model.admin.service.Service;

public interface ServiceService
{
    Service createService(Service request);
}
