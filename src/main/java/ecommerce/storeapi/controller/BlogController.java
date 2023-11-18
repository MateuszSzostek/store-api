package ecommerce.storeapi.controller;

import ecommerce.storeapi.connection.*;
import ecommerce.storeapi.model.Post;
import ecommerce.storeapi.model.Product;
import ecommerce.storeapi.repository.BlogRepository;
import ecommerce.storeapi.service.BlogService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogRepository blogRepository;
    private final BlogService blogService;

    public BlogController(BlogRepository blogRepository, BlogService blogService) {
        this.blogRepository = blogRepository;
        this.blogService = blogService;
    }

    @PostMapping("/add-post")
    public ResponseEntity<AddPostResponse> addPost(@RequestBody AddPostRequest request) {
        return ResponseEntity.ok(blogService.addPost(request));
    }

    @PostMapping("/update-post")
    public ResponseEntity<UpdatePostResponse> updatePost(@RequestBody UpdatePostRequest request) {
        return ResponseEntity.ok(blogService.updatePost(request));
    }

    @GetMapping("/get-post")
    public ResponseEntity<GetPostResponse> getPost(@RequestParam String id) {
        return ResponseEntity.ok(blogService.getPost(id));
    }

    @DeleteMapping("/delete-post")
    public ResponseEntity<DeletePostResponse> deletePost(@RequestBody DeletePostRequest request) {
        return ResponseEntity.ok(blogService.deletePost(request));
    }

    @QueryMapping
    public Iterable<Post> posts(){
        return this.blogRepository.findAll();
    }

    @QueryMapping
    public Post postById(@Argument String id){
        return this.blogRepository.findById(id).orElseThrow();
    }
}
