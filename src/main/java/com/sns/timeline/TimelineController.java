package com.sns.timeline;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardDTO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TimelineController {

	private final TimelineBO timelineBO;
	
	@GetMapping("/timeline")
	public String timeline(Model model,
			HttpSession session) { // 브레이크 포인트 걸어서 확인
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<CardDTO> cardList = timelineBO.generateCardList(userId); 		
		
		model.addAttribute("cardList", cardList);			
		
		return "timeline/timeline";
	}
}