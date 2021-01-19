package com.bilev.recipe.boundary;

import com.bilev.recipe.dto.TagDto;
import com.bilev.recipe.service.TagService;

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

@Path("tag")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagResource {

    @Inject
    TagService service;

    @GET
    public Collection<TagDto> getTags(@QueryParam("since") @NotNull final Date date) {
        return service.getTags(date);
    }

    @POST
    public TagDto updateTag(@Valid @NotNull final TagDto dto) {
        return service.updateTag(dto);
    }

    @GET
    @Path("{id}")
    public TagDto getTag(@PathParam("id") final UUID id) {
        return service.getTag(id);
    }

    @DELETE
    @Path("{id}")
    public void deleteTag(@PathParam("id") final UUID id) {
        service.removeTag(id);
    }
}
