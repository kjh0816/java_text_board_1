package com.kjh.exam.app;

import java.util.HashMap;
import java.util.Map;

public class Rq {
		
		private Map<String, String> params;
		private String command;
		private String controllerTypeName;
		private String controllerName;
		private String actionMethodName;
		private String queryString = "";
		public boolean isValid = true;
		
		public Rq(String command) {
			
			this.command = command;
			
			params = new HashMap<>();
			
			String[] commandBits = command.split("\\?", 2);
			
			
			
//			쿼리 스트링이 입력된 경우, 쿼리 스트링을 담아서 데이터를 쪼개는 작업을 진행한다.	
			if(commandBits.length == 2) {
				queryString = commandBits[1].trim();
				
				String[] queryStringBits = queryString.split("&");
//				&를 통해 들어온 값이 몇개일지 모르므로 반복문으로 처리한다.
				for(String queryStringBit : queryStringBits) {
					String[] queryStringBitBits = queryStringBit.split("=", 2);
					String paramName = queryStringBitBits[0].trim();
					String paramValue = queryStringBitBits[1].trim();
					
					params.put(paramName, paramValue);
				}
				
			}
			
			
			commandBits = commandBits[0].split("/", 4);
//			"/"로 쪼개진 commandBits는 4개여야 정상이다.
			if(commandBits.length != 4) {
				isValid = false;
				return;
			}
			
			controllerTypeName = commandBits[1].trim();
			controllerName = commandBits[2].trim();
			actionMethodName = commandBits[3].trim();
			
			
						
			
			
		}
		
		public Object getActionPath() {
			return "/" + controllerTypeName + "/" + controllerName + "/" + actionMethodName; 
		}
		
		public int getIntParam(String paramName, int defaultValue) {
//			paramName(id 등)에 해당하는 key가 존재하지 않으면 defaultValue를 return한다. 
			if(params.containsKey(paramName) == false) {
				return defaultValue;
			}
//			paramName에 해당하는 int로 형변환이 되면 숫자를 정상적으로 입력한 경우이고, 형변환이 안 되면 잘못된 값이 들어온 경우이다.
			try {
				return Integer.parseInt(params.get(paramName));
			}catch(NumberFormatException e) {
				return defaultValue;
			}
		}
		
		
}
