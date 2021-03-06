package ec.edu.ups.ejb;

import ec.edu.ups.entidad.FacturaDetalle;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FacturaDetalleFacade extends AbstractFacade<FacturaDetalle> {
    @PersistenceContext(unitName = "DistribuidoraJSF")
    private EntityManager em;

    public FacturaDetalleFacade(){
        super(FacturaDetalle.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
