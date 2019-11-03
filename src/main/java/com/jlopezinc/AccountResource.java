package com.jlopezinc;

import com.jlopezinc.database.AccountEntity;
import com.jlopezinc.model.Account;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


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
