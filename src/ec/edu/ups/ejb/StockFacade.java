package ec.edu.ups.ejb;

import ec.edu.ups.entidad.Stock;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class StockFacade extends AbstractFacade<Stock>{
    @PersistenceContext(unitName = "DistribuidoraJSF")
    private EntityManager entityManager;

    public StockFacade() { super(Stock.class); }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
