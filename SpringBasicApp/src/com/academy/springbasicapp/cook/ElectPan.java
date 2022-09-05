package com.academy.springbasicapp.cook;
//전기로 음식을 데우는 팬
public class ElectPan implements Pan {
	@Override
	public void boil() {
		System.out.println("전기로 음식을 데워요");
	}

}
