package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.comment.bo.CommentBO;
import com.sns.post.bo.PostBO;
import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TimelineController {

	private final TimelineBO timelineBO;
	
	@GetMapping("/timeline")
	public String timeline(Model model) { // 브레이크 포인트 걸어서 확인

		List<CardDTO> cardList = timelineBO.generateCardList(); 		
		model.addAttribute("cardList", cardList);			
		return "timeline/timeline";
	}
}