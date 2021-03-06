package com.example.android.newsappabnd;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tetianakolesnik on 01/03/2018.
 */

public class NewsUtil {

    public static final String LOG_TAG = NewsUtil.class.getName();
    private NewsUtil() {}

    public static List<NewsStory> fetchNewsData (String urlString, Context context){

        String jsonReply = "";
        URL url = createURL(urlString);
        try {
            jsonReply = makeHTTPrequest(url);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        List<NewsStory> news = extractNewsFromJson(jsonReply, context);
        return news;
    }

    private static URL createURL(String mUrl) {
        URL url;
        try {
            url = new URL(mUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return url;
    }

    private static String makeHTTPrequest(URL url) throws IOException {
        String jsonReply = "";
        if (url == null) {
            return jsonReply;
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setRequestMethod("GET");
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                jsonReply = readFromInput(inputStream);
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonReply;
    }

    private static List<NewsStory> extractNewsFromJson(String jsonReply, Context context) {
        ArrayList<NewsStory> news = new ArrayList<>();
        String author;

        try {
            JSONObject jsonObject = new JSONObject(jsonReply);
            JSONObject rootJsonObject = jsonObject.getJSONObject(context.getResources().getString(R.string.json_response_object));
            JSONArray resultsJsonArray = rootJsonObject.getJSONArray(context.getResources().getString(R.string.json_response_results_array));
            for (int i = 0; i < resultsJsonArray.length(); i++){
                JSONObject newsItem = resultsJsonArray.getJSONObject(i);
                String webTitle = newsItem.getString(context.getResources().getString(R.string.json_response_webTitle));
                String webUrl = newsItem.getString(context.getResources().getString(R.string.json_response_webUrl));
                String sectionName = newsItem.getString(context.getResources().getString(R.string.json_response_sectionName));
                String webPublicationDate = newsItem.getString(context.getResources().getString(R.string.json_response_webPublicationDate));
                try {
                    JSONArray tagsArray = newsItem.getJSONArray(context.getResources().getString(R.string.json_response_tagsArray));
                    JSONObject tags = tagsArray.getJSONObject(0);
                    author = tags.getString(context.getResources().getString(R.string.json_response_tagsWebTitle));
                } catch (JSONException e) {
                    author = "";
                }
                news.add(new NewsStory(webTitle, sectionName, webPublicationDate, author, webUrl));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return news;
    }

    private static String readFromInput(InputStream inputStream) throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        }
        return stringBuilder.toString();
    }

}

