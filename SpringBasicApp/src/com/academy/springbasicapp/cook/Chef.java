package com.academy.springbasicapp.cook;

public class Chef {
	//자바에서 특정 객체가 다른 객체를 부품으로 가질때 
	//has 어 관계가 하고, 이때 부품이 되는 객체는
	//멤버변수로 선언
	//아래와 같이 has 관계로 특정 객체를 보유할때, 너무 정확한 자료형을 보유하면,
	//유지 보수성이 떨어진다.-> 해결책 보다 상위 자료형으로 유연한 방법을 이용
	//이유? 상위 자료형일 수록 많은 자료형들을 가리킬 수 있으므로 
	Pan pan; //chef has a Fripan
	
	public void cook() {
//		pan = new ElectPan(); new 연산자를 사용하면 new 뒤에 정확한 자료형을 명시해야 하므로, 
									//이 이유때문에 결합도를 낮추기 힘들어진다.
		
		pan.boil();
	}
	
}
