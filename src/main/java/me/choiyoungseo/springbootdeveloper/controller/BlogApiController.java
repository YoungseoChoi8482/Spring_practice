package me.choiyoungseo.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.choiyoungseo.springbootdeveloper.domain.Article;
import me.choiyoungseo.springbootdeveloper.dto.AddArticleRequest;
import me.choiyoungseo.springbootdeveloper.dto.ArticleResponse;
import me.choiyoungseo.springbootdeveloper.dto.UpdateArticleRequest;
import me.choiyoungseo.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController    //Http response body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    //Http 메서드가 Post일 때 전달받은 URL과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    // @RequestBody로 요청본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request)
    {
        Article savedArticle = blogService.save(request);

        // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();


        return ResponseEntity.ok()
                .body(articles);
    }


    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article =  blogService.findById(id);


        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id,request);

        return ResponseEntity.ok()
                .body(updatedArticle);
        //이 코드의 의미는 "업데이트가 성공적으로 완료되었으며,
        // 업데이트된 데이터를 응답 본문(body)에 담아 클라이언트에게 반환한다"는 것!
    }



}
