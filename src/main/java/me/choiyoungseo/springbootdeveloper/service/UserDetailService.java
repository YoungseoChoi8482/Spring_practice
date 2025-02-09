package me.choiyoungseo.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.choiyoungseo.springbootdeveloper.domain.User;
import me.choiyoungseo.springbootdeveloper.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
// 스프링 시큐리티에서 사용자 정보를 가져오는 인터 페이스
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }
}
