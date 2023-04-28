package com.jlopezinc;

import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;

abstract class ResourceSupport {
    @Inject
    JsonWebToken jwt;

    String getUuid (){
        String uuid = jwt.getClaim("sub");
        if (uuid == null){
            throw new BadRequestException();
        }
        return uuid;
    }
}
