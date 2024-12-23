package com.alejandro.webapp.jsf3.repositories;

import com.alejandro.webapp.jsf3.entities.Producto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RequestScoped
public class ProductoRepositoryImpl implements ProductoRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Producto> listar() {
        return em.createQuery("SELECT p FROM Producto p LEFT OUTER JOIN FETCH p.categoria", Producto.class).getResultList();
    }

    @Override
    public Producto porId(Long id) {
//        return em.find(Producto.class, id);
        return em.createQuery("SELECT p FROM Producto p LEFT OUTER JOIN FETCH p.categoria WHERE p.id=:id", Producto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void guardar(Producto producto) {
        if (producto.getId() != null && producto.getId() > 0) {
            em.merge(producto);
        } else {
            em.persist(producto);
        }
    }

    @Override
    public void eliminar(Long id) {
        em.remove(porId(id));
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        return em.createQuery("SELECT p FROM Producto p LEFT OUTER JOIN FETCH p.categoria WHERE p.nombre LIKE :nombre", Producto.class)
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList();
    }
}
