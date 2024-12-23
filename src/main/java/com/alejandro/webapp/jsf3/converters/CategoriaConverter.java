package com.alejandro.webapp.jsf3.converters;

import com.alejandro.webapp.jsf3.entities.Categoria;
import com.alejandro.webapp.jsf3.services.ProductoService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named("categoriaConverter")
public class CategoriaConverter implements Converter<Categoria> {

    @Inject
    private ProductoService service;

    @Override
    public Categoria getAsObject(FacesContext context, UIComponent component, String id) {
        if(id == null){
            return null;
        }
        var categoriaOptional = service.porIdCategoria(Long.valueOf(id));
        return categoriaOptional.orElse(null);

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Categoria categoria) {
        if (categoria == null || categoria.getId() == null) {
            return "0";
        }
        return categoria.getId().toString();
    }
}
