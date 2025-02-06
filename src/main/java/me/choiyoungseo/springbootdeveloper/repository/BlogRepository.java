package me.choiyoungseo.springbootdeveloper.repository;

import me.choiyoungseo.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
