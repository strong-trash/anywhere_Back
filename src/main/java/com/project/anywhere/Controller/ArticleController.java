package com.project.anywhere.Controller;

import com.project.anywhere.DTO.Article;
import com.project.anywhere.Errors.notExists;
import com.project.anywhere.Repository.ArticleRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLEngineResult;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class ArticleController extends ArticleRepository {

    @PersistenceContext
    private EntityManager em;
    @Transactional
    @PostMapping("masil")
    public String writeArticle(@RequestBody Article article){
        try {
            writeArticle(article,em);
        }
        catch(Exception e){
            return "{error:402}";
        }
        return SSLEngineResult.Status.OK.toString();
    }
    @Transactional
    @DeleteMapping("masil/{id}")
    public String deleteArticle(@PathVariable(name="id") int article_id){
        System.out.println(article_id);
        try {
            deleteArticle(article_id,em);
        }
        catch (notExists e){
            System.out.println("not Exists");
            return "NOT OKAY";
        }
        return "OK";
    }
    @Transactional
    @GetMapping("masil/{id}")
    public String getArticle(@PathVariable int article_id){
        return SSLEngineResult.Status.OK.toString();
    }
    @Transactional
    @GetMapping("masil/all")
    public String getArticles(){
        return "Test";
    }
}
