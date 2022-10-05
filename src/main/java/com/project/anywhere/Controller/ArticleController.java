package com.project.anywhere.Controller;

import com.project.anywhere.DTO.Article;
import com.project.anywhere.Repository.ArticleRepository;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLEngineResult;

@RestController
public class ArticleController {
    @PostMapping("masil")
    public String writeArticle(@RequestBody Article article){
        try {
            ArticleRepository articleRepository = new ArticleRepository();
            articleRepository.writeArticle(article);
        }
        catch(Exception e){
            return "{error:402}";
        }
        return SSLEngineResult.Status.OK.toString();
    }
    @DeleteMapping("masil/{id}")
    public SSLEngineResult.Status deleteArticle(@PathVariable(name="id") int article_id){
        System.out.println(article_id);
        return SSLEngineResult.Status.OK;
    }
}
