package com.project.anywhere.Controller;

import com.project.anywhere.DTO.Place;
import com.project.anywhere.Errors.notconnectionError;
import com.sun.jdi.event.ExceptionEvent;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
public class placeController {
    @Value("${keycode}")
    public String key;
    @GetMapping("masil/getInfo")
    public String getLocation(Place place){
        double randPage = Math.random();
        int randP = (int)(randPage*14+1);
        System.out.println((int)(randPage*14+1));
        HttpURLConnection con = null;
        JSONObject jsonObject = null;
        System.out.println(key);
        try{
            String BigFilter = URLEncoder.encode(place.getBigfilter(),"utf-8");
            URL url=new URL("https://dapi.kakao.com/v2/local/search/keyword.json?query="+BigFilter+"&radius="+place.getDis()+"&page="+randP+"&x="+place.getX()+"&y="+place.getY()+"&category_group_code="+place.getSmallfilter());

            System.out.println(url.getPath()+" "+url.toString());
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type","charset=utf-8");
            con.setRequestProperty("Authorization", "KakaoAK "+key);
            con.setDoOutput(true);
            System.out.println(con.toString());
            if(con.getResponseCode()==200){
                System.out.println(200);
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line="";
                while((line=br.readLine())!=null){
                    System.out.println(line);
                    sb.append(line);
                }
                System.out.println(sb.toString());
                JSONObject reponseJson = new JSONObject(sb.toString());

                JSONArray responseArray = new JSONArray(reponseJson.getJSONArray("documents"));
                int randomO = (int)(randPage*15);
                return responseArray.getJSONObject(randomO).toString();
            }
            else{
                throw new notconnectionError();
            }
        }
        catch (notconnectionError e){
            return "{\"nope\",\"no content\"}";
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return null;
    }
}
