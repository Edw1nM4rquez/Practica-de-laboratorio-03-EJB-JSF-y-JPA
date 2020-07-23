package ec.edu.ups.ejb;

import ec.edu.ups.entidad.FacturaCabecera;
import ec.edu.ups.entidad.FacturaDetalle;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class FacturaCebeceraFacade extends AbstractFacade<FacturaCabecera> {

    @PersistenceContext(unitName = "DistribuidoraJSF")
    private EntityManager em;

    public FacturaCebeceraFacade(){
        super(FacturaCabecera.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    public void insertarFD(FacturaDetalle facturaDetalle){
        em.createNativeQuery("INSERT INTO FacturaDetalle (codigo,cantida,total,facturacabecera_numero,producto_codigo) VALUES(?,?,?,?,?)"
        ).setParameter(1,0).setParameter(2,facturaDetalle.getCantida()
        ).setParameter(3,facturaDetalle.getTotal()).setParameter(4,facturaDetalle.getFacturaCabecera().getNumero()
        ).setParameter(5,facturaDetalle.getProducto().getCodigo()).executeUpdate();
    }

    public FacturaCabecera obtenerUltimo(){
        Query nativeQuery = em.createNativeQuery("SELECT * FROM facturacabecera order by numero DESC ", FacturaCabecera.class);
        List result = nativeQuery.getResultList();
        return (FacturaCabecera) result.get(0);
    }

}
