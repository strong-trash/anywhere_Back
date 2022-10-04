package com.project.anywhere.Repository;

import com.project.anywhere.DTO.Article;
import com.project.anywhere.DTO.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController

public class SaveArticle {
    @Autowired
    private EntityManagerFactory factory;
    @PostMapping("masil")
    public String writeArticle(@RequestBody Article article){
        if(factory==null){
            System.out.println("fuck");
        }
        EntityManager em = factory.createEntityManager();

        System.out.println(article.getContent());
        return "good";
    }
//    @PostMapping("masil")
//    public String writeArticle(HttpServletRequest httpServletRequest){
//        try {
//            if (httpServletRequest != null) {
//                StringBuilder sb = new StringBuilder();
//                BufferedReader bu = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()));
//                String buffer;
//                while((buffer=bu.readLine())!=null){
//                    sb.append(buffer);
//                    System.out.println(buffer);
//                }
//            }
//        }
//        catch (IOException e){
//            return "{status_code:503}";
//        }
//        return "good";
//    }
}
