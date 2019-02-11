package sf.alomari.wagstack;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//Build the chosen Url and get the response using okhttp library to establish a connection

public class NetworkUtils {


    static OkHttpClient  client = new OkHttpClient();

    public static   String run (String url) throws IOException

    {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }


}
