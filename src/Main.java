import DAO.BusDAO;
import DAO.Factory;
import logic.Bus;
import logic.Route;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import java.util.Collection;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;



import java.sql.Driver;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Olga Pavlova on 10/22/2015.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws SQLException {
        {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for (Object key : metadataMap.keySet()) {
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
                final String entityName = classMetadata.getEntityName();
                //final Query query = session.createQuery("from " + entityName);

                //TODO works OK:
                Bus bus;
                //TODO should suggest smth by <ctrl-space> here: b.id = :<ctrl-space>
                final Query query =
                        session.createQuery("from Bus b where b.id = : HereCtrlSpace" + entityName);

                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
        }

    /*Collection routes = Factory.getInstance().getRouteDAO().getAllRoutes();
    Iterator iterator = routes.iterator();
    System.out.println("========Все маршруты=========");
    while (iterator.hasNext()) {
        Route route = (Route) iterator.next();
        System.out.println("Маршрут : " + route.getName() + "  Номер маршрута : " + route.getNumber());
        Collection busses = Factory.getInstance().getBusDAO().getBussesByRoute(route);
        Iterator iterator2 = busses.iterator();
        while (iterator2.hasNext()) {
            Bus bus = (Bus) iterator2.next();
            System.out.println("Автобус № " + bus.getNumber());

        }
    }*/

        java.util.Collection busses = Factory.getInstance().getBusDAO().getAllBusses();
        Iterator iterator = busses.iterator();
        System.out.println("========Все автобусы=========");
        while (iterator.hasNext()) {
            Bus bus = (Bus) iterator.next();
            //Collection drivers = Factory.getInstance().getDriverDAO().getDriversByBus(bus);
            //Iterator iterator2 = drivers.iterator();
            System.out.println("Автобус № " + bus.getNumber());
        /*while (iterator2.hasNext()) {
            Driver driver = (Driver) iterator2.next();
            System.out.println("Имя : " + bus.getNumber() + "   Фамилия: " + driver.getSurname());

        }*/
        }
    }
}
