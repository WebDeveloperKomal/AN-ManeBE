//package com.example.controller;
//
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.example.entity.Blogs;
//import com.example.entity.LoginUserDetails;
//import com.example.entity.TeamMembers;
//import com.example.repo.BlogsRepository;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/auth")
//public class BlogController {
//	
//	@Autowired
//	private BlogsRepository blogsRepository;
//	
//	
//	/*-----------------------add-new-blog-----------------------*/
//	@PostMapping("/save-blogs")
//	public ResponseEntity<String> saveBlog(@RequestParam("name") String name,
//			@RequestPart(value = "image1", required = false) MultipartFile image1,
//			@RequestPart(value = "image2", required = false) MultipartFile image2,
//			@RequestPart(value = "image3", required = false) MultipartFile image3,
//			@RequestPart(value = "image4", required = false) MultipartFile image4,
//			@RequestPart(value = "image5", required = false) MultipartFile image5,
//			Authentication authentication) {
//		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//		try {
//			Blogs blog = new Blogs();
//			blog.setName(name);
//			
//			// Check and set images only if they are not null
//			if (image1 != null) {
//				blog.setImage1(image1.getBytes());
//			}
//			if (image2 != null) {
//				blog.setImage2(image2.getBytes());
//			}
//			if (image3 != null) {
//				blog.setImage3(image3.getBytes());
//			}
//			if (image4 != null) {
//				blog.setImage4(image4.getBytes());
//			}
//			if (image5 != null) {
//				blog.setImage5(image5.getBytes());
//			}
//
//			blogsRepository.save(blog);
//
//			return new ResponseEntity<>("Blog saved successfully", HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>("Failed to save blog", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	
//	/*-----------------------working-but-showing-only-one-image-in-database-----------------------*/
//	/*-----------------------get-all-images-----------------------*/
//	@GetMapping("/get-images/{id}")
//	public ResponseEntity<List<byte[]>> getImagesById(@PathVariable int id) {
//	    Optional<Blogs> blogs = blogsRepository.findById(id);
//
//	    if (blogs.isPresent()) {
//	        Blogs blog = blogs.get();
//
//	        List<byte[]> images = new ArrayList<>();
//	       
//	        if (blog.getImage1() != null) {
//	            images.add(blog.getImage1());
//	        }
//	        if (blog.getImage2() != null) {
//	            images.add(blog.getImage2());
//	        }
//	        if (blog.getImage3() != null) {
//	            images.add(blog.getImage3());
//	        }
//	        if (blog.getImage4() != null) {
//	            images.add(blog.getImage4());
//	        }
//	        if (blog.getImage5() != null) {
//	            images.add(blog.getImage5());
//	        }
//	        if (!images.isEmpty()) {
//	        	   HttpHeaders headers = new HttpHeaders();
//		            headers.setContentType(MediaType.IMAGE_JPEG);
//		            return new ResponseEntity<>(images, headers, HttpStatus.OK);
//	        } else {
//	            // Handle scenario where no images are found
//	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//	        }
//	    } else {
//	        // Handle not found scenario
//	        return ResponseEntity.notFound().build();
//	    }
//	}
//
//	
//	/*-----------------------get-image------------------------*/
//    @GetMapping("/image1/{id}")
//    public ResponseEntity<byte[]> getImageById(@PathVariable int id) {
//        Optional<Blogs> blogs = blogsRepository.findById(id);
//
//        if (blogs.isPresent()) {
//        	Blogs blog = blogs.get();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your image
//                    .body(blog.getImage1());
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    
//	/*-----------------------get-image------------------------*/
//    @GetMapping("/image2/{id}")
//    public ResponseEntity<byte[]> getImageById2(@PathVariable int id) {
//        Optional<Blogs> blogs = blogsRepository.findById(id);
//
//        if (blogs.isPresent()) {
//        	Blogs blog = blogs.get();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your image
//                    .body(blog.getImage2());
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    
//	/*-----------------------get-image------------------------*/
//    @GetMapping("/image3/{id}")
//    public ResponseEntity<byte[]> getImageById3(@PathVariable int id) {
//        Optional<Blogs> blogs = blogsRepository.findById(id);
//
//        if (blogs.isPresent()) {
//        	Blogs blog = blogs.get();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your image
//                    .body(blog.getImage3());
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    
//	/*-----------------------get-image------------------------*/
//    @GetMapping("/image4/{id}")
//    public ResponseEntity<byte[]> getImageById4(@PathVariable int id) {
//        Optional<Blogs> blogs = blogsRepository.findById(id);
//
//        if (blogs.isPresent()) {
//        	Blogs blog = blogs.get();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your image
//                    .body(blog.getImage4());
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    
//	/*-----------------------get-image------------------------*/
//    @GetMapping("/image5/{id}")
//    public ResponseEntity<byte[]> getImageById5(@PathVariable int id) {
//        Optional<Blogs> blogs = blogsRepository.findById(id);
//
//        if (blogs.isPresent()) {
//        	Blogs blog = blogs.get();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your image
//                    .body(blog.getImage5());
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//    
//    
//    /*-----------------------get-slider-data-----------------------*/
//    @GetMapping("/blog-data/{id}")
//    public ResponseEntity<Map<String, Object>> getSliderWithImage(@PathVariable int id, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	Optional<Blogs> blogs = blogsRepository.findById(id);
//
//        if (blogs.isPresent()) {
//        	Blogs blog = blogs.get();
//
//            // Create a Map to store both the slider data and the image bytes
//            Map<String, Object> response = new HashMap<>();
//            response.put("id", blog.getId());
//            response.put("name", blog.getName());
//            response.put("text", blog.getText());
//            response.put("image1", Base64.getEncoder().encodeToString(blog.getImage1())); // Convert image bytes to Base64
//            response.put("image2", Base64.getEncoder().encodeToString(blog.getImage2()));
//            response.put("image3", Base64.getEncoder().encodeToString(blog.getImage3()));
//            response.put("image4", Base64.getEncoder().encodeToString(blog.getImage4()));
//            response.put("image5", Base64.getEncoder().encodeToString(blog.getImage5()));
//
//            // Return the response as a JSON object
//            return ResponseEntity.ok().body(response);
//        } else {
//            // Handle not found scenario
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//
//    /*-----------------------update-blog-----------------------*/
//    @PutMapping("/update-blog/{blogId}")
//    public ResponseEntity<String> updateBlog(@PathVariable int blogId,
//                                             @RequestParam(value = "name", required = false) String name,
//                                             @RequestParam(value = "text", required = false) String text,
//                                             @RequestPart(value = "image1", required = false) MultipartFile image1,
//                                             @RequestPart(value = "image2", required = false) MultipartFile image2,
//                                             @RequestPart(value = "image3", required = false) MultipartFile image3,
//                                             @RequestPart(value = "image4", required = false) MultipartFile image4,
//                                             @RequestPart(value = "image5", required = false) MultipartFile image5,
//                                             Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	try {
//            Optional<Blogs> optionalBlog = blogsRepository.findById(blogId);
//
//            if (optionalBlog.isPresent()) {
//                Blogs blog = optionalBlog.get();
//
//                // Update fields only if the corresponding values are provided in the update request
//                if (name != null) {
//                    blog.setName(name);
//                }
//                if (text != null) {
//                    blog.setText(text);
//                }
//                if (image1 != null) {
//                    blog.setImage1(image1.getBytes());
//                }
//                if (image2 != null) {
//                    blog.setImage2(image2.getBytes());
//                }
//                if (image3 != null) {
//                    blog.setImage3(image3.getBytes());
//                }
//                if (image4 != null) {
//                    blog.setImage4(image4.getBytes());
//                }
//                if (image5 != null) {
//                    blog.setImage5(image5.getBytes());
//                }
//
//                blogsRepository.save(blog);
//
//                return new ResponseEntity<>("Blog updated successfully", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Blog not found", HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to update blog", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    
//    
//  	/*-----------------------get-all-blogs-----------------------*/
//  	@GetMapping("/get-all-blog")
//  	public ResponseEntity<Map<String, Object>> getAllBlog() {
//  		Map<String, Object> response = new HashMap<>();
//  		try {
//  			// Retrieve all Services entities using the repository
//  			List<Blogs> blogList = blogsRepository.findAll();
//
//  			// Create a response object with relevant data
//  			List<Map<String, Object>> responseDataList = new ArrayList<>();
//  			for (Blogs services : blogList) {
//  				Map<String, Object> responseData = new HashMap<>();
//  				responseData.put("id", services.getId());
//  				responseData.put("name", services.getName());
//  				responseData.put("text", services.getText());
//  				responseData.put("image1", services.getImage1());
//  				responseData.put("image2", services.getImage2());
//  				responseData.put("image3", services.getImage3());
//  				responseData.put("image4", services.getImage4());
//  				responseData.put("image5", services.getImage5());
//  			
//  				responseDataList.add(responseData);
//  			}
//
//  			// Return the response
//  			response.put("status", true);
//  			response.put("data", responseDataList);
//  			return new ResponseEntity<>(response, HttpStatus.OK);
//  		} catch (Exception e) {
//  			response.put("status", false);
//  			response.put("error", "Error getting all Services: " + e.getMessage());
//  			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//  		}
//  	}
//  	
//    
//    /*-----------------------delete-blog-----------------------*/
//    @DeleteMapping("/delete-blog/{blogId}")
//    public ResponseEntity<String> deleteBlog(@PathVariable int blogId, Authentication authentication) {
//    	LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//    	try {
//            Optional<Blogs> optionalBlog = blogsRepository.findById(blogId);
//
//            if (optionalBlog.isPresent()) {
//                blogsRepository.deleteById(blogId);
//                return new ResponseEntity<>("Blog deleted successfully", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Blog not found", HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to delete blog", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    
//}
