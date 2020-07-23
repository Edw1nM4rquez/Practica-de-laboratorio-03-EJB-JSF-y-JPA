package ec.edu.ups.ejb;

import ec.edu.ups.entidad.Provincia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProvinciaFacade extends AbstractFacade<Provincia> {

    @PersistenceContext(unitName = "DistribuidoraJSF")
    private EntityManager entityManager;

    public ProvinciaFacade(){
        super(Provincia.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
