package org.rinsoz.climbtracker;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class RouteStorage {
    private static final String TAG = "RouteStorage";
    private static final String FILENAME = "routes.json";

    private ArrayList<Route> _routes;
    private RoutesJSONSerializer _serializer;

    private static RouteStorage __routeStorage;

    private RouteStorage(Context appContext) {
        _serializer = new RoutesJSONSerializer(appContext, FILENAME);
        try {
            _routes = _serializer.loadCrimes();
        } catch (Exception e) {
            _routes = new ArrayList<>();
            Log.e(TAG, "Error loading crimes: ", e);
        }
    }

    public static RouteStorage get(Context c) {
        if (__routeStorage == null) {
            __routeStorage = new RouteStorage(c.getApplicationContext());
        }
        return __routeStorage;
    }

    public Route getRoute(UUID id) {
        for (Route c : _routes) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

    public void addRoute(Route c) {
        _routes.add(c);
        save();
    }

    public ArrayList<Route> getRouteList() {
        return _routes;
    }

    public boolean save() {
        try {
            _serializer.saveCrimes(_routes);
            Log.d(TAG, "crimes saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving crimes: " + e);
            return false;
        }
    }

    public  boolean export(){
        try {
            String json = _serializer.getJSON(_routes);
            Log.d(TAG, "crimes saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving crimes: " + e);
            return false;
        }
    }

    public void deleteRoute(Route route) {
        _routes.remove(route);
        save();
    }
}
