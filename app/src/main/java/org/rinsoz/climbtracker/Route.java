package org.rinsoz.climbtracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

public class Route {
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_COLOR = "color";
    private static final String JSON_GRADE = "grade";
    private static final String JSON_CREATOR = "creator";
    private static final String JSON_HINT = "hint";
    private static final String JSON_CREATION_DATE = "creation_date";
    private static final String JSON_IN_SECOND = "in_second";
    private static final String JSON_PROGRESS = "progress";
    private static final String JSON_LAST_TRY = "last_try";
    private static final String JSON_PERSONAL_COMMENT = "personal_comment";

    private UUID _uuid;
    private String _title;
    private int _color;
    private String _grade;
    private String _creator;
    private String _hint;
    private Date _creationDate;
    private boolean _inSecond;
    private RouteProgress _progress;
    private Date _lastTryDate;
    private String _personalComment;

    public Route() {
        _uuid = UUID.randomUUID();
        _title = "";
        _color = 0;
        _grade = "";
        _creator = "";
        _hint = "";
        _creationDate = new Date();
        _inSecond = false;
        _progress = RouteProgress.NOT_TRY;
        _lastTryDate = new Date();
        _personalComment = "";
    }

    public Route(JSONObject json) throws JSONException {
        _uuid = UUID.fromString(json.getString(JSON_ID));
        _title = json.getString(JSON_TITLE);
        _color = json.getInt(JSON_COLOR);
        _grade = json.getString(JSON_GRADE);
        _creator = json.getString(JSON_CREATOR);
        _hint = json.getString(JSON_HINT);
        _creationDate = new Date(json.getLong(JSON_CREATION_DATE));
        _inSecond = json.getBoolean(JSON_IN_SECOND);
        _progress = RouteProgress.fromValue(json.getString(JSON_PROGRESS));
        _lastTryDate = new Date(json.getLong(JSON_LAST_TRY));
        _personalComment = json.getString(JSON_PERSONAL_COMMENT);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, _uuid.toString());
        json.put(JSON_TITLE, _title);
        json.put(JSON_TITLE, _title);
        json.put(JSON_COLOR, _color);
        json.put(JSON_GRADE, _grade);
        json.put(JSON_CREATOR, _creator);
        json.put(JSON_HINT, _hint);
        json.put(JSON_CREATION_DATE, _creationDate.getTime());
        json.put(JSON_IN_SECOND, _inSecond);
        json.put(JSON_PROGRESS, _progress.toString());
        json.put(JSON_LAST_TRY, _lastTryDate.getTime());
        json.put(JSON_PERSONAL_COMMENT, _personalComment);
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

    public void setColor(int color) {
        _color = color;
    }

    public void setGrade(String grade) {
        _grade = grade;
    }

    public void setCreator(String creator) {
        _creator = creator;
    }

    public void setHint(String hint) {
        _hint = hint;
    }

    public void setCreationDate(Date creationDate) {
        _creationDate = creationDate;
    }

    public void setInSecond(boolean inSecond) {
        _inSecond = inSecond;
    }

    public void setProgress(RouteProgress progress) {
        _progress = progress;
    }

    public void setLastTryDate(Date lastTryDate) {
        _lastTryDate = lastTryDate;
    }

    public void setPersonalComment(String personalComment) {
        _personalComment = personalComment;
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
