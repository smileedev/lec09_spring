package com.gn.spring.websocket.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gn.spring.websocket.model.vo.SendMessage;

public class ChattingServer extends TextWebSocketHandler{
	
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	// 클라이언트가 연결되었을 때 설정
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
	}
	
	// 클라이언트가 웹소켓 서버로 메시지를 전송했을 때 설정
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 클라이언트가 보낸 메시지 확인하기
		String payload = message.getPayload();
		
		// Jackson ObjectMapper 객체 생성
		ObjectMapper objectMapper = new ObjectMapper();
		
		// JSON String 형태 데이터 -> 우리가 만든 클래스 형태로 변환
		SendMessage msg = objectMapper.readValue(payload, SendMessage.class);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("res_code", "404");
		resultMap.put("res_msg", "채팅 전송 중 오류가 발생하였습니다.");
		
		TextMessage tm = new TextMessage(objectMapper.writeValueAsString(resultMap));
		
		for(WebSocketSession temp : sessionList) {
			temp.sendMessage(tm);
		}
		
	}

	// 클라이언트가 연결을 끊었을 때 설정
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
	}
	
}
