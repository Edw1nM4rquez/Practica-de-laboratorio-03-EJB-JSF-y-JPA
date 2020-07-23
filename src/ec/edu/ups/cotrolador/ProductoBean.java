package ec.edu.ups.cotrolador;

import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidad.Producto;
import org.apache.commons.io.IOUtils;
import org.omnifaces.util.Messages;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.*;
import java.util.List;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ProductoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ProductoFacade ejbProductoFacade;
    private Producto producto;
    private Producto productoVisualizar;
    private UploadedFile image;
    private String destination;
    private List<Producto> productoList;

    public ProductoBean(){ }

    @PostConstruct
    public void init() {
        producto = new Producto();
        productoList = ejbProductoFacade.findAll();
    }

    public void crear(){
        if(ejbProductoFacade.find(producto.getCodigo())==null){
            if (image != null) {
                try {
                    producto.setImagen(IOUtils.toByteArray(image.getInputstream()));
                    producto.setEstado('A');
                    ejbProductoFacade.create(producto);
                    producto=new Producto();
                    productoList=ejbProductoFacade.findAll();
                    Messages.create("Info").detail("Producto Creado").add();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                Messages.create("Error!").error().detail("Agrege una imagen al producto").add();
            }
        }else {
            Messages.create("Error!").error().detail("Ya existe un producto con el mismo codigo").add();
        }
    }




    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public String visualizarProducto(Producto productoVis){
        this.productoVisualizar=productoVis;
        return "dasboardProductoVisualizar?faces-redirect=true";
    }
}
