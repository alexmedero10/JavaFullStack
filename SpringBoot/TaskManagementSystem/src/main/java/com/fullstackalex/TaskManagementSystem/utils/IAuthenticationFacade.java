package com.fullstackalex.TaskManagementSystem.utils;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {

    Authentication getAuthentication();

}
