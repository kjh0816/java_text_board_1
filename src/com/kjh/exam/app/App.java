package com.kjh.exam.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kjh.exam.app.dto.Article;
import com.kjh.exam.util.Util;

public class App {

	public static void run() {
		
		
		
		
		System.out.println("=== 텍스트 게시판 시작 ===");
		

		
		Scanner sc = new java.util.Scanner(System.in);
		
		List<Article> articles = new ArrayList<Article>();
		int articleLastId = 0;
		
//		테스트 article 생성 (시작)
		
		for(int i = 0; i < 10; i++) {
			Article article = new Article();
			article.id = articleLastId + 1;
			article.regDate = Util.getNowDateStr();
			article.updateDate = Util.getNowDateStr();
			article.title = "제목" + article.id;
			article.body = "내용" + article.id;
			
			articles.add(article);
			articleLastId++;
			
		}
//		테스트 article 생성 (끝)
		
		
		while(true) {
			
			System.out.println("명령어) ");
			
			String command = sc.nextLine().trim();
			
			if(command.equals("/usr/system/exit")) {
				System.out.println("장비를 정지합니다.");
				break;
			}
			else if(command.equals("/usr/article/write")) {
				System.out.println("제목을 입력해주세요.");
				String title = sc.nextLine().trim();
				System.out.println("내용을 입력해주세요.");
				String body = sc.nextLine().trim();
				
				Article article = new Article();
				article.id = articleLastId + 1;
				article.regDate = Util.getNowDateStr();
				article.updateDate = Util.getNowDateStr();
				article.title = title;
				article.body = body;
				
				articles.add(article);
				
				System.out.printf("%d번 게시물이 생성되었습니다.\n", article.id);
			}
			else if(command.equals("/usr/article/list")) {
				System.out.println("번호 / 작성날짜 / 제목");
				
				// 최신순으로 출력
				for( int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d / %s / %s\n", article.id, article.regDate, article.title);
					
				}
				
			}
			else if(command.startsWith("/usr/article/detail")) {
				
				// 자바에서 ?는 특수기호이기 때문에 문자 자체로 읽히려면 \\를 붙여줘야한다.
				String queryString = command.split("\\?", 2)[1];
				
				
				String[] optionalQueries = queryString.split("&");
				
				int id = 0;
				
				for( String optionalQuery : optionalQueries) {
					String[] optionalQueryBits = optionalQuery.split("=", 2);
					String paramName = optionalQueryBits[0];
					String paramValue = optionalQueryBits[1];
					
					if(paramName.equals("id")) {
						id = Integer.parseInt(paramValue);
					}
					
					
				}
				
				if(id == 0) {
					System.out.println("게시물 번호(id)를 입력해주세요.");
					continue;
				}
				
				Article foundArticle = null;
				
				for( Article article : articles) {
					if(article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				 
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
				System.out.printf("수정날짜 : %s\n", foundArticle.updateDate);
				
				
				
				
				
				
			}
			
		}
		
		
		
		
		
		System.out.println("=== 텍스트 게시판 끝 ===");
		
	}

}
