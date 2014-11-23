package org.rinsoz.climbtracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

public class Route {
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_DATE = "date";
    private static final String JSON_SOLVED = "solved";

    private UUID _uuid;
    private String _title;
    private String _color;
    private String _quotation;
    private String _creator;
    private String _hint;
    private Date _creationDate;
    private boolean _inSecond;
    private RouteProgress _progress;
    private Date _lastTryDate;
    private String _personalComment;

    public Route() {
        _uuid = UUID.randomUUID();
        _creationDate = new Date();
    }

    public Route(JSONObject json) throws JSONException {
        _uuid = UUID.fromString(json.getString(JSON_ID));
        _title = json.getString(JSON_TITLE);
        _inSecond = json.getBoolean(JSON_SOLVED);
        _creationDate = new Date(json.getLong(JSON_DATE));
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, _uuid.toString());
        json.put(JSON_TITLE, _title);
        json.put(JSON_DATE, _creationDate.getTime());
        json.put(JSON_SOLVED, _inSecond);
        return json;
    }

    @Override
    public String toString() {
        return _title;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public UUID getId() {
        return _uuid;
    }

    public Date getCreationDate() {
        return _creationDate;
    }

    public void setCreationDate(Date creationDate) {
        _creationDate = creationDate;
    }


    public String getHint() {
        return _hint;
    }

    public String getCreator() {
        return _creator;
    }

    public String getPersonalComment() {
        return _personalComment;
    }

    public boolean inSecond() {
        return _inSecond;
    }
}
