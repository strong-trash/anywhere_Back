package com.project.anywhere.Repository;

import com.project.anywhere.DTO.Article;
import com.project.anywhere.DTO.Articles;
import com.project.anywhere.DTO.Places;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
@Repository
public class ArticleRepository {
    @PersistenceContext
    private EntityManager em;
    @Transactional
    public  String writeArticle( Article article){

        try{
            Places places=em.find(Places.class,article.getAddr());
            Articles articles=null;
            if(places==null){
                places=new Places();
                places.setPlace_id(article.getAddr());
            }
            articles.setAll(article, Optional.of(places));
            places.getArticles().add(articles);
            em.persist(articles);
            em.persist(places);
            em.flush();
        }
        catch(Exception e){
            System.out.println(e.toString());

        }
        em.close();
        return "good";
    }
}
