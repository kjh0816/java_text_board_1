package com.kjh.exam.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kjh.exam.app.dto.Article;
import com.kjh.exam.util.Util;

public class App {
	
//	App > run()에서 App > getArticleById, articles 배열을 끌어다 쓰기 위해서 App에서 선언 및 초기화 (시작)
	Scanner sc;
	List<Article> articles;
	int articlesLastId;

	App() {
		sc = new Scanner(System.in);
		articles = new ArrayList<>();
		articlesLastId = 0;
	}
	
//	App > run()에서 App > getArticleById, articles 배열을 끌어다 쓰기 위해서 App에서 선언 및 초기화 (끝)

	public void run() {


//		테스트 게시물 생성 (시작)
		for (int i = 0; i < 10; i++) {
			Article article = new Article();
			article.id = articlesLastId + 1;
			article.regDate = Util.getNowDateStr();
			article.updateDate = Util.getNowDateStr();
			article.title = "제목 " + article.id;
			article.body = "내용 " + article.id;
			articles.add(article);
			articlesLastId++;
		}
//		테스트 게시물 생성 (끝)
		
		System.out.println("== 텍스트 게시판 시작 ==");

		while (true) {
			System.out.printf("명령어) ");

			String command = sc.nextLine().trim();

			Rq rq = new Rq(command);

			if (rq.isValid == false) {
				System.out.printf("명령어가 올바르지 않습니다.\n");
				continue;
			}

			if (rq.getActionPath().equals("/usr/article/write")) {
				System.out.printf("제목 : ");
				String title = sc.nextLine().trim();
				System.out.printf("내용 : ");
				String body = sc.nextLine().trim();

				Article article = new Article();
				article.id = articlesLastId + 1;
				article.regDate = Util.getNowDateStr();
				article.updateDate = Util.getNowDateStr();
				article.title = title;
				article.body = body;
				articles.add(article);

				articlesLastId++;

				System.out.printf("%d번 게시물이 생성되었습니다.\n", article.id);
			} else if (rq.getActionPath().equals("/usr/article/list")) {
				System.out.printf("번호 / 작성날자 / 제목\n");

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d / %s / %s\n", article.id, article.regDate, article.title);
				}

			} else if (rq.getActionPath().equals("/usr/system/exit")) {
				System.out.println("프로그램을 종료 합니다.");
				break;
			} else if (rq.getActionPath().equals("/usr/article/detail")) {
				int id = rq.getIntParam("id", 0);

				if (id == 0) {
					System.out.println("id를 입력해주세요.");
					continue;
				}

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
				System.out.printf("수정날짜 : %s\n", foundArticle.updateDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);

			} else if (rq.getActionPath().equals("/usr/article/delete")) {
				int id = rq.getIntParam("id", 0);

				if (id == 0) {
					System.out.println("id를 입력해주세요.");
					continue;
				}

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				articles.remove(foundArticle);

				System.out.printf("%d번 게시물을 삭제하였습니다.\n", id);

			} else if (rq.getActionPath().equals("/usr/article/modify")) {
				int id = rq.getIntParam("id", 0);

				if (id == 0) {
					System.out.println("id를 입력해주세요.");
					continue;
				}

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				System.out.printf("새 제목 : ");
				foundArticle.title = sc.nextLine();
				System.out.printf("새 내용 : ");
				foundArticle.body = sc.nextLine();
				foundArticle.updateDate = Util.getNowDateStr();

				System.out.printf("%d번 게시물을 수정하였습니다.\n", id);

			} else if (rq.getActionPath().equals("/usr/system/exit")) {
				System.out.println("프로그램을 종료 합니다.");
				break;
			}
		}

		System.out.println("== 텍스트 게시판 끝 ==");
	}

	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.id == id) {
				return article;
			}
		}

		return null;
	}

}