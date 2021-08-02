package com.kt.AssetSearcher.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kt.AssetSearcher.model.Assets;
import com.kt.AssetSearcher.model.Thumbnail;
import com.kt.AssetSearcher.thread.ThreadWithValue;
 
@RestController
public class IndexController {
    
    @RequestMapping("/")
    public ModelAndView index(ModelAndView mav) {
    	mav.setViewName("index");
        return mav;
    }
    
    @RequestMapping("/search")
    public ModelAndView search(ModelAndView mav,
    	@RequestParam(name="photo", required=false) Optional<String> photo,
    	@RequestParam(name="illust", required=false) Optional<String> illust,
    	@RequestParam(name="icon", required=false) Optional<String> icon,
    	@RequestParam("q") String q
    ) {
    	mav.setViewName("index");
		mav.addObject("mapOfThumbnailList",
			new HashMap<String, List<ThreadWithValue<List<Thumbnail>>>>() {
    			{
    				photo.ifPresent(v->put("写真", Assets.getPhoto(q)));
    				illust.ifPresent(v->put("イラスト", Assets.getIllust(q)));
    				icon.ifPresent(v->put("アイコン", Assets.getIcon(q)));
    			}
    		}
			.entrySet()
			.parallelStream()//Stream<Entry<String, List<ThreadWithValue<List<Thumbnail>>>>>
			.map(elem->{
				List<Thumbnail> value = elem//Entry<String, List<ThreadWithValue<List<Thumbnail>>>>
					.getValue()//List<ThreadWithValue<List<Thumbnail>>>
					.parallelStream()//Stream<ThreadWithValue<List<Thumbnail>>>
					.map(thread->thread.getValue())//Stream<List<Thumbnail>>
					.flatMap(lst -> lst.stream())//Stream<Thumbnail>
					.collect(Collectors.toList());//List<Thumbnail>;
				return Map.entry(elem.getKey(), value);
			})
			.collect(Collectors.toMap(e->e.getKey(), e->e.getValue()))
		);
        return mav; 
    }
}
