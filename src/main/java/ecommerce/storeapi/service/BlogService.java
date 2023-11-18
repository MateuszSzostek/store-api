package ecommerce.storeapi.service;

import ecommerce.storeapi.connection.*;
import ecommerce.storeapi.model.Post;
import ecommerce.storeapi.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public AddPostResponse addPost(AddPostRequest request) {
        Post post = blogRepository.save(request.getPost());
        return AddPostResponse.builder().post(post).build();
    }

    public UpdatePostResponse updatePost(UpdatePostRequest request) {
        Post postToUpdate = blogRepository.findById(request.getPost().getId()).orElseThrow();

        Post postFromRequest = request.getPost();

        postToUpdate.setAuthor(postFromRequest.getAuthor());
        postToUpdate.setContent(postFromRequest.getContent());
        postToUpdate.setPicture(postFromRequest.getPicture());
        postToUpdate.setTitle(postFromRequest.getTitle());
        postToUpdate.setCreatedAt(postFromRequest.getCreatedAt());
        postToUpdate.setUrl(postFromRequest.getUrl());

        return UpdatePostResponse.builder().post(postToUpdate).build();
    }

    public GetPostResponse getPost(String id) {
        Post post = blogRepository.findById(id).orElseThrow();
        return GetPostResponse.builder().post(post).build();
    }

    public DeletePostResponse deletePost(DeletePostRequest request) {
        Post postToDelete = blogRepository.findById(request.getId()).orElseThrow();
        blogRepository.delete(postToDelete);
        return DeletePostResponse.builder().postDeleted(true).build();
    }
}
