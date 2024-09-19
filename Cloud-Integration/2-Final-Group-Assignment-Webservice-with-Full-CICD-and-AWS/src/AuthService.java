package com.jromeo.webservice.auth.service;

import com.jromeo.webservice.auth.dto.LoginDTO;
import com.jromeo.webservice.auth.dto.RegisterDTO;

/**
 * Author: Johan Romeo
 */

public interface AuthService {
    String login(LoginDTO dto);
    String register(RegisterDTO dto);
}
