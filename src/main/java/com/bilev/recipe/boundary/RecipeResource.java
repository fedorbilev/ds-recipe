package com.bilev.recipe.boundary;

import com.bilev.common.exception.ExceptionDto;
import com.bilev.recipe.dto.RecipeDto;
import com.bilev.recipe.service.RecipeService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

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
@Tag(name = "Recipe Resource")
public class RecipeResource {

    @Inject
    RecipeService service;

    @GET
    @Path("{id}")
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Successful response",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecipeDto.class))),
            @APIResponse(
                    responseCode = "400",
                    description = "Internal server error has occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))) })
    @Operation(summary = "Gets recipe by id")
    public RecipeDto getRecipe(@PathParam("id") final UUID id) {
        return service.getRecipe(id);
    }

    @GET
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Successful response",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecipeDto.class, type = SchemaType.ARRAY))),
            @APIResponse(
                    responseCode = "400",
                    description = "Internal server error has occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))) })
    @Operation(summary = "Gets recipes since date")
    public Collection<RecipeDto> getRecipes(@QueryParam("since") @NotNull final Date date) {
        return service.getRecipes(date);
    }

    @POST
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Successful response",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecipeDto.class))),
            @APIResponse(
                    responseCode = "400",
                    description = "Internal server error has occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))) })
    @Operation(summary = "Create or update recipe entity")
    public RecipeDto updateRecipe(@Valid @NotNull final RecipeDto dto) {
        return service.updateRecipe(dto);
    }
}
