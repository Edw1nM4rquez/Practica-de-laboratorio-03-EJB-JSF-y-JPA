package ec.edu.ups.cotrolador;

import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.entidad.Categoria;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class CategoriaBean implements Serializable {

    private  static final  long serialVersionUID = 1L;
    @EJB
    private CategoriaFacade ejbCategoriaFacade;
    private List<Categoria> categoriaList;
    private Categoria categoria;

    public CategoriaBean(){ }

    @PostConstruct
    public void init() {
        categoria=new Categoria();
        categoriaList = ejbCategoriaFacade.findAll();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public void crear(){
        this.categoria.setNombre(categoria.getNombre().toUpperCase());
        ejbCategoriaFacade.create(categoria);
        categoriaList=ejbCategoriaFacade.findAll();
    }


}
