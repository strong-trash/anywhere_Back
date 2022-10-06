package com.project.anywhere.Controller;

import com.project.anywhere.DTO.Article;
import com.project.anywhere.DTO.Articles;
import com.project.anywhere.DTO.Places;
import com.project.anywhere.Errors.notExists;
import com.project.anywhere.Repository.ArticleRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLEngineResult;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@RestController
public class ArticleController extends ArticleRepository {

    @PersistenceContext
    private EntityManager em;
    // 게시글 등록
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
    //게시글 삭제
    @Transactional
    @DeleteMapping("masil/{id}")
    public String deleteArticle(@PathVariable(name="id") int article_id){
        System.out.println(article_id);
        try {
            deleteArticle(article_id,em);
        }
        catch (notExists e){
            System.out.println("not Exists");
            return "NOT EXISTS";
        }
        return "OK";
    }
    //게시글 수정
    @Transactional
    @PostMapping("masil/{id}")
    public String getArticle(@RequestBody Article article,@PathVariable(name="id") int article_id){
        System.out.println(article.getContent());
        System.out.println(article_id);
        try {
            updateArticles(article, article_id, em);
        }
        catch (notExists e){
            System.out.println("Not Exists");
            return "NOT OK";
        }
        return "OK";
    }
    //게시글 열기
    @Transactional
    @GetMapping("masil/{id}")
    public String getArticle(){

        return "OK";
    }
    @Transactional
    @GetMapping("masil/all")
    public String getArticles(){
        ArrayList<Articles> articlesArrayList=getAllArticles(em);

        return "Test";
    }

}
