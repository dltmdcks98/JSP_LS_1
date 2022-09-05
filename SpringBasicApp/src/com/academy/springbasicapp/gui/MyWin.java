package com.academy.springbasicapp.gui;
//지금까지 작성했던 GUI  프로그램을 Spring DI를 적용하여 개발해본다.

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComponent;

import lombok.Data;

@Data
public class MyWin {
//	상위 자료형으로 선언한 이유? 결합도를 약화시키기 위해
	private JComponent area;
	private JComponent t_input;
	private JComponent bt;
	
//생성자로 하게되면 context가 적용되기 전에 실행되어서 오류가 남
	public void init() {
// 객체의 생성은 고민할 필요 없다, 스프링 컨테이너가 알아서 주입해줌
		area.setPreferredSize(new Dimension(295,340));
		
//		조립
		setLayout(new FlowLayout());
		add(area);
		add(t_input);
		add(bt);
		
//		윈도우 보이기 
		setVisible(true);
		setSize(300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
