package com.bikkadit.blog.payloads;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

	private List<PostDto> contents;
	private int pageNumber;
	private int pageSize;
	private boolean lastPage;
	private Long totalElement;
	private int tatalPages;
	
	
}
