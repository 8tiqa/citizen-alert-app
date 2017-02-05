package com.bese3.nauman.citizen.data;
import java.io.UnsupportedEncodingException;
import java.util.Map;    
import org.json.JSONException;
import org.json.JSONObject;    
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class CustomRequest extends Request<JSONObject> {

private Listener<JSONObject> listener;
private Map<String, String> params;

public CustomRequest(String url, Map<String, String> params,
        Listener<JSONObject> reponseListener, ErrorListener errorListener) {
    super(Method.GET, url, errorListener);
    this.listener = reponseListener;
    this.params = params;
}

public CustomRequest(int method, String url, Map<String, String> params,
        Listener<JSONObject> reponseListener, ErrorListener errorListener) {
    super(method, url, errorListener);
    this.listener = reponseListener;
    this.params = params;
}

protected Map<String, String> getParams()
        throws com.android.volley.AuthFailureError {
    return params;
};

@Override
protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
    try {
        String jsonString = new String(response.data,
                HttpHeaderParser.parseCharset(response.headers));
        return Response.success(new JSONObject(jsonString),
                HttpHeaderParser.parseCacheHeaders(response));
    } catch (UnsupportedEncodingException e) {
        return Response.error(new ParseError(e));
    } catch (JSONException je) {
        return Response.error(new ParseError(je));
    }
}

@Override
protected void deliverResponse(JSONObject response) {
    // TODO Auto-generated method stub
    listener.onResponse(response);
}
}