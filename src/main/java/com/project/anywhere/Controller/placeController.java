package com.project.anywhere.Controller;

import com.project.anywhere.DTO.Place;
import com.project.anywhere.Errors.notconnectionError;
import com.sun.jdi.event.ExceptionEvent;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class placeController {
    @Value("${keycode}")
    public String key;
    @GetMapping("masil/getInfo")
    public JSONObject getLocation(Place place){
        double randPage = Math.random();
        int randP = (int)(randPage*14+1);
        System.out.println((int)(randPage*14+1));
        HttpURLConnection con = null;
        JSONObject jsonObject = null;
        try{

            URL url=new URL("https://dapi.kakao.com/v2/local/search/keyword.json?query="+place.getBigfilter()+"&radius="+place.getDis()+"&page="+randP+"&x="+place.getX()+"&y="+place.getY()+"&category_group_code="+place.getSmallfilter());
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "KakaoAK "+key);
            con.setDoOutput(true);
            con.getResponseCode();
            if(con.getResponseCode()==200){
                System.out.println(200);

            }
            else{
                throw new notconnectionError();
            }
        }
        catch (notconnectionError e){
            return new JSONObject().put("nope","no content");
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return null;
    }
}
