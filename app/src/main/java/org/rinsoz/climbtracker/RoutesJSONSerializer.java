package org.rinsoz.climbtracker;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class RoutesJSONSerializer {

    private Context _context;
    private String _filename;

    public RoutesJSONSerializer(Context c, String f) {
        _context = c;
        _filename = f;
    }

    public ArrayList<Route> loadCrimes() throws IOException, JSONException {
        ArrayList<Route> routes = new ArrayList<>();
        BufferedReader reader = null;
        try {
            // open and read the file into a StringBuilder
            InputStream in = _context.openFileInput(_filename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                // line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            // parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // build the array of routes from JSONObjects
            for (int i = 0; i < array.length(); i++) {
                routes.add(new Route(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // we will ignore this one, since it happens when we start fresh
        } finally {
            if (reader != null)
                reader.close();
        }
        return routes;
    }

    public void saveCrimes(ArrayList<Route> routes) throws JSONException, IOException {
        String json_encoded = getJSON(routes);
        // write the file to disk
        Writer writer = null;
        try {
            OutputStream out = _context.openFileOutput(_filename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(json_encoded);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public String getJSON(ArrayList<Route> routes) throws JSONException {
        // build an array in JSON
        JSONArray array = new JSONArray();
        for (Route r : routes)
            array.put(r.toJSON());
        return array.toString();
    }
}
