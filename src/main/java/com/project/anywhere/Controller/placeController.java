package com.project.anywhere.Controller;

import com.project.anywhere.DTO.Place;
import com.project.anywhere.DTO.Places;
import com.project.anywhere.Errors.notconnectionError;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

@RestController
public class placeController {
    @PersistenceContext
    private EntityManager em;
    @Value("${keycode}")
    public String key;
    @GetMapping("masil/getInfo")
    public String getLocation( Place place){
        double randPage = Math.random();
        int randP = (int)(randPage*44+1);

        HttpURLConnection con = null;
        System.out.println(key);
        try{
            URL url=null;
            ArrayList<String> arr=new ArrayList<>();
            arr.add("MT1");
            arr.add("CS2");
            arr.add("CT1");
            arr.add("AT4");
            arr.add("FD6");
            arr.add("HP8");
            arr.add("PM9");
            if(place.getBigfilter().equals("ANY")){
                int randBig = (int)(Math.random()*6);
                place.setBigfilter(arr.get(randBig));
            }
            if(place.getSmallfilter()==null){
                System.out.println("small is null");
                url = new URL("https://dapi.kakao.com/v2/local/search/category.json?"+"&radius="+place.getDis()+"&page="+randP+"&x="+place.getX()+"&y="+place.getY()+"&category_group_code="+place.getBigfilter());
            }
            else{
                String SmallFilter = URLEncoder.encode(place.getSmallfilter(),"utf-8");
                url=new URL("https://dapi.kakao.com/v2/local/search/keyword.json?query="+SmallFilter+"&radius="+place.getDis()+"&page="+randP+"&x="+place.getX()+"&y="+place.getY()+"&category_group_code="+place.getBigfilter());
            }
            System.out.println("A");
            System.out.println(url.getPath()+" "+url);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type","charset=utf-8");
            con.setRequestProperty("Authorization", "KakaoAK "+key);
            con.setDoOutput(true);
            System.out.println("A");
            System.out.println(con.toString());
            if(con.getResponseCode()==200){
                System.out.println(200);
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line="";
                while((line=br.readLine())!=null){
                    sb.append(line);
                }
                JSONObject reponseJson = new JSONObject(sb.toString());

                JSONArray responseArray = new JSONArray(reponseJson.getJSONArray("documents"));
                int randomO = (int)(randPage*responseArray.length());
                JSONObject jsonObject = responseArray.getJSONObject(randomO);
                System.out.println(jsonObject.toString());
                JSONObject returnjson=new JSONObject();
                /*
                {
                    "x":float,
                    "y":float,
                    "place_name":string,
                    "info":string,
                    "url":string,
                    "addr":string,
                    “article_links”: [{id:long,title:string,date},{ }]
                }

                 */
                returnjson.put("x",jsonObject.get("x"));
                returnjson.put("y",jsonObject.get("y"));
                returnjson.put("place_name",jsonObject.get("place_name"));
                returnjson.put("info",
                        jsonObject.get("category_name")+"\n"
                        +jsonObject.get("road_address_name")+"\n"
                        +jsonObject.get("phone")+"\n"
                        +"현재 위치로 부터 거리: "+jsonObject.get("distance")
                );
                returnjson.put("url",jsonObject.get("place_url"));
                returnjson.put("addr",jsonObject.get("road_address_name"));
                Places places = em.find(Places.class,jsonObject.get("road_address_name"));
                System.out.println(places);
                if(places==null){
                    returnjson.put("article_links","[]");
                }
                else{
                    sb.setLength(0);
                    places.getArticles().stream().forEach(a->{
                        sb.append("{id:"+a.getArticle_id()+",title:"+a.getTitle()+"},");
                    });
                    sb.deleteCharAt(sb.length()-1);
                    returnjson.put("article_links","["+sb.toString()+"]");
                }
                em.close();
                System.out.println(returnjson.toString());
                return returnjson.toString();
            }
            else{
                throw new notconnectionError();
            }
        }
        catch (notconnectionError e){
            System.out.println(e.getStackTrace());
            System.out.println(e.toString());
        }
        catch(Exception e){
            System.out.println(e.getStackTrace());
            System.out.println(e.toString());
        }
        em.close();
        return "{status,404}";
    }
}
