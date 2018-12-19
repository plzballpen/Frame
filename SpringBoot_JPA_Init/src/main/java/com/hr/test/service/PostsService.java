package com.hr.test.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.hr.test.domain.posts.PostsRepository;
import com.hr.test.dto.posts.PostsMainResponseDto;
import com.hr.test.dto.posts.PostsSaveRequestDto;

@AllArgsConstructor
@Service
public class PostsService {
	private PostsRepository postsRepository;
	
	@Transactional
    public Long save(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }
	
	@Transactional
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }
}
