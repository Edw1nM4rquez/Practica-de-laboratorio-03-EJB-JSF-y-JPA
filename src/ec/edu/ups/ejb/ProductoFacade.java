package ec.edu.ups.ejb;
import ec.edu.ups.entidad.Producto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {
    @PersistenceContext(unitName = "DistribuidoraJSF")
    private EntityManager entityManager;

    public ProductoFacade(){
        super(Producto.class);

    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
