package com.jlopezinc;

import com.jlopezinc.database.AccountEntity;
import com.jlopezinc.model.Account;

import java.util.Date;

import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;


@Path("v1/account/")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AccountResource extends ResourceSupport {

    @GET
    @Transactional
    public Account get(){
        final AccountEntity byExternal = AccountEntity.findByExternal(getUuid());
        if (byExternal == null){
            throw new NotFoundException();
        }
        byExternal.lastLoginDate = new Date();
        Account account = new Account();
        account.setExternalId(byExternal.externalId);
        return account;
    }

    @POST
    @Transactional
    public Response create(){
        final String uuid = getUuid();
        AccountEntity ac = new AccountEntity();
        ac.externalId = uuid;
        Date now = new Date();
        ac.lastLoginDate = now;
        ac.createdOn = now;
        AccountEntity.persist(ac);
        return Response.created(null).build();
    }
}
