package ec.edu.ups.ejb;

import ec.edu.ups.entidad.Pais;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PaisFacade extends AbstractFacade<Pais> {

    @PersistenceContext(unitName = "DistribuidoraJSF")
    private EntityManager entityManager;

    public PaisFacade(){
        super(Pais.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }


}
