package com.bilev.recipe.boundary;

import com.bilev.recipe.dto.IngredientDto;
import com.bilev.recipe.service.IngredientService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Path("ingredient")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IngredientResource {

    @Inject
    IngredientService service;

    @GET
    public Collection<IngredientDto> getIngredients(@QueryParam("since") @NotNull final Date date) {
        return service.getIngredients(date);
    }

    @POST
    public IngredientDto updateIngredient(@Valid @NotNull final IngredientDto dto) {
        return service.updateIngredient(dto);
    }

    @GET
    @Path("{id}")
    public IngredientDto getIngredient(@PathParam("id") final UUID id) {
        return service.getIngredient(id);
    }

    @DELETE
    @Path("{id}")
    public void deleteIngredient(@PathParam("id") final UUID id) {
        service.removeIngredient(id);
    }
}
