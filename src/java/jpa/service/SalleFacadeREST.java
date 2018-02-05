/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import jpa.Salle;

/**
 *
 * @author YB
 */
@Stateless
@Path("jpa.salle")
public class SalleFacadeREST extends AbstractFacade<Salle> {

    @PersistenceContext(unitName = "SalleServiceRESTfromDBPU")
    private EntityManager em;

    public SalleFacadeREST() {
        super(Salle.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Salle entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Salle entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Salle find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Salle> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Salle> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("type.jpql/{type}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Salle> findByType(@PathParam("type") String type) {
        Query cq = em.createQuery("Select s from Salle s where s.typesalle = :typeParam");
        cq.setParameter("typeParam", type);
        return cq.getResultList();
    }

    @GET
    @Path("searchByType")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Salle> searchByType(@QueryParam("type") String type) {
        Query cq = em.createNamedQuery("Salle.findByTypesalle");
        cq.setParameter("typesalle", type);
        return cq.getResultList();
    }

    @GET
    @Path("searchNativeByType")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Salle> searchNativeByType(@QueryParam("type") String type) {        
        Query cq = em.createNativeQuery("SELECT * FROM SALLE s WHERE s.typesalle = ?",Salle.class);
        cq.setParameter(1, type);
        return cq.getResultList();
    }

    @GET
    @Path("type.criteria/{type}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Salle> findByTypeC(@PathParam("type") String type) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();

        Root<Salle> salle = cq.from(Salle.class);
        cq.select(salle).where(cb.equal(salle.get("typesalle"), type));

        return em.createQuery(cq).getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
