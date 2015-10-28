package DAO;

import logic.Bus;
import logic.Driver;
import logic.Route;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Olga Pavlova on 10/22/2015.
 */
public interface RouteDAO {
    public void addRoute(Bus bus) throws SQLException;
    public void updateRoute(Long bus_id, Bus bus) throws SQLException;
    public Bus getRouteById(Long bus_id) throws SQLException;
    public Collection getAllRoutes() throws SQLException;
    public void deleteRoute(Bus bus) throws SQLException;
    public Collection getRoutesByDriver(Driver driver) throws SQLException;
    public Collection getRoutesByBus(Route route) throws SQLException;
}
