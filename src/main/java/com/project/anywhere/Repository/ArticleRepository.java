package com.project.anywhere.Repository;

import com.project.anywhere.DTO.Article;
import com.project.anywhere.DTO.Articles;
import com.project.anywhere.DTO.Places;
import com.project.anywhere.Errors.notExists;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
@Repository
public class ArticleRepository {
    //삽입
    public  String writeArticle( Article article,EntityManager em){

        try{
            Places places=em.find(Places.class,article.getAddr());
            Articles articles=null;
            if(places==null){
                places=new Places();
                places.setPlace_id(article.getAddr());
            }
            articles = new Articles();
            articles.setAll(article, Optional.of(places));
            places.getArticles().add(articles);

            em.persist(articles);
            em.persist(places);
            em.flush();
        }
        catch(Exception e){
            System.out.println(e.toString());
            return "bad";
        }
        em.close();
        return "good";
    }
    //삭제
    public void deleteArticle(long article_id,EntityManager em) throws notExists {
        Articles articles =null;
        articles = em.find(Articles.class,article_id);
        if(articles==null){
            throw new notExists();
        }
        else{
            em.remove(articles);
            em.flush();
        }
        return;
    }
    //수정
    public void updateArticles(Article article, long article_id, EntityManager em){
        

    }
    //조회1
    public List<Articles> getArticles(String Addr, EntityManager em){
        Places places = em.find(Places.class,Addr);
        if(places==null){
            return null;
        }
        return places.getArticles();
    }
}
