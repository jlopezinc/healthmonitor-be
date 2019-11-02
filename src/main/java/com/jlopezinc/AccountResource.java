package com.jlopezinc;

import com.jlopezinc.database.AccountEntity;
import com.jlopezinc.model.Account;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
    public Account get(){
        final AccountEntity byExternal = AccountEntity.findByExternal(getUuid());
        Account account = new Account();
        account.setExternalId(byExternal.externalId);
        return account;
    }

    @POST
    public Response create(){
        final String uuid = getUuid();
        AccountEntity ac = new AccountEntity();
        ac.externalId = uuid;
        AccountEntity.persist(ac);
        return Response.created(null).build();
    }
}
