package com.bilev.recipe.boundary;

import com.bilev.common.exception.ExceptionDto;
import com.bilev.recipe.dto.IngredientDto;
import com.bilev.recipe.service.IngredientService;
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
@Tag(name = "Ingredient Resource")
public class IngredientResource {

    @Inject
    IngredientService service;

    @GET
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Successful response",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IngredientDto.class, type = SchemaType.ARRAY))),
            @APIResponse(
                    responseCode = "400",
                    description = "Internal server error has occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))) })
    @Operation(summary = "Gets ingredients since date")
    public Collection<IngredientDto> getIngredients(@QueryParam("since") @NotNull final Date date) {
        return service.getIngredients(date);
    }

    @POST
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Successful response",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IngredientDto.class))),
            @APIResponse(
                    responseCode = "400",
                    description = "Internal server error has occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))) })
    @Operation(summary = "Create or update ingredients entity")
    public IngredientDto updateIngredient(@Valid @NotNull final IngredientDto dto) {
        return service.updateIngredient(dto);
    }

    @GET
    @Path("{id}")
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Successful response",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IngredientDto.class))),
            @APIResponse(
                    responseCode = "400",
                    description = "Internal server error has occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))) })
    @Operation(summary = "Gets ingredient by id")
    public IngredientDto getIngredient(@PathParam("id") final UUID id) {
        return service.getIngredient(id);
    }

    @DELETE
    @Path("{id}")
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Successful response"),
            @APIResponse(
                    responseCode = "400",
                    description = "Internal server error has occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))) })
    @Operation(summary = "Delete ingredient by id")
    public void deleteIngredient(@PathParam("id") final UUID id) {
        service.removeIngredient(id);
    }
}
