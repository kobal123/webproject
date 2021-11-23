package search.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import search.SearchService;

@RestController
@RequestMapping(path = "/api/search")
public class AutoCompleteRestController {
	@Autowired
	private SearchService searchService;
	
	
	
	
	@GetMapping
	@RequestMapping("/{productName}")
	public List<String> getAutoCompleteRecommendations( @PathVariable String productName) {
		
		
		return searchService.searchAutoComplete(productName);
	}
	
	
}
