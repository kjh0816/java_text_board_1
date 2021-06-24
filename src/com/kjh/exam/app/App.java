package com.kjh.exam.app;

import java.util.Scanner;

public class App {

	public static void run() {
		
		
		Scanner sc = new java.util.Scanner(System.in);
		
		System.out.println("=== 텍스트 게시판 시작 ===");
		
		
		while(true) {
			System.out.println("명령어) ");
			
			String command = sc.nextLine().trim();
			if(command.equals("/usr/system/exit")) {
				System.out.println("장비를 정지합니다.");
				break;
			}
		}
		
		
		
		
		
		System.out.println("=== 텍스트 게시판 끝 ===");
		
	}

}
