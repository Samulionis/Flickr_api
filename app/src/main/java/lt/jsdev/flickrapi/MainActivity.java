package lt.jsdev.flickrapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String mFlickrApi = "&api_key=a097556aa68517b72971f37e1ef64ddb";
    private String mFlickrTag = "&tags=Lithuania";
    private String mFlickrPerPage = "&per_page=10";
    private String mFlickrFormat = "&format=json";
    private String mFlickrCallBack = "&nojsoncallback=1";

    private FlickrApi mFlickrApiOb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String callUrl = "https://api.flickr.com/services/rest/?method=flickr.photos.search"
                + mFlickrApi
                + mFlickrTag
                + mFlickrPerPage
                + mFlickrFormat
                + mFlickrCallBack;

        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(callUrl)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {

                        String json = response.body().string();
                        if (response.isSuccessful()) {
                            Log.v(TAG, json);
                            mFlickrApiOb = getInformation(json);
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception cought: ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "Exception cought: ", e);
                    }
                }
            });

        } else {
            Toast.makeText(this,R.string.networkUnavailable, Toast.LENGTH_LONG).show();
        }
    }

    private FlickrApi getInformation(String json) throws JSONException {
            JSONObject flickrData = new JSONObject(json);
            JSONObject photos = flickrData.getJSONObject("photos");
            JSONArray another = photos.getJSONArray("photo");

            ArrayList<FlickrApi> flickrArray = new ArrayList<FlickrApi>();

            for(int i = 0; i < another.length(); i++) {
                FlickrApi temp = new FlickrApi();

                JSONObject obj = another.getJSONObject(i);

                temp.setFlickrFarmId(obj.getInt("farm"));
                temp.setFlickrServerId(obj.getString("server"));
                temp.setFlckrPhotoId(obj.getString("id"));
                temp.setFlckrSecret(obj.getString("secret"));
                temp.setFlckrPhotoTitle(obj.getString("title"));

                flickrArray.add(temp);
            }

            createCallArrayUrls(flickrArray);
           // for(FlickrApi x: flickrArray) {
           //     Log.i(TAG, "FROM JSON: " + x.getFlckrPhotoTitle());
           // }


        return new FlickrApi();
    }

    private void createCallArrayUrls(ArrayList<FlickrApi> flickrArray) {




    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}
