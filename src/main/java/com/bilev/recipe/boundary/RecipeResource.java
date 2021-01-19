package com.bilev.recipe.boundary;

import com.bilev.recipe.dto.RecipeDto;
import com.bilev.recipe.service.RecipeService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
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

@Path("recipe")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecipeResource {

    @Inject
    RecipeService service;

    @GET
    @Path("{id}")
    public RecipeDto getRecipe(@PathParam("id") final UUID id) {
        return service.getRecipe(id);
    }

    @GET
    public Collection<RecipeDto> getRecipes(@QueryParam("since") @NotNull final Date date) {
        return service.getRecipes(date);
    }

    @POST
    public RecipeDto updateRecipe(@Valid @NotNull final RecipeDto dto) {
        return service.updateRecipe(dto);
    }
}
