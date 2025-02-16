package me.choiyoungseo.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.choiyoungseo.springbootdeveloper.domain.Article;
import me.choiyoungseo.springbootdeveloper.dto.ArticleListViewResponse;
import me.choiyoungseo.springbootdeveloper.dto.ArticleViewResponse;
import me.choiyoungseo.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model)
    {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();

        model.addAttribute("articles", articles); // 블로그 글 리스트 저장

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        }
        else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }


        return "newArticle";
    }
}
