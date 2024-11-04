package com.sns.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimelineController {

	
	@GetMapping("/timeline")
	public String timelin() {
		return "timeline/timeline";
	}
}
