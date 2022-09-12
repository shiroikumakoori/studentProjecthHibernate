package org.dxc.Service;

import org.dxc.Payload.PostDTO;
import org.dxc.Payload.PostResponse;

public interface PostServiceInterface {
	PostDTO createPost(PostDTO postDto);
	
	PostResponse getAppPosts(int pagNo , int pageSize, String sortBy, String sortDir);
	
	PostDTO getPostById(Long id);
	
	PostDTO updatePost(PostDTO postDto , Long id);
	
	void deletPostById(long id);
}
