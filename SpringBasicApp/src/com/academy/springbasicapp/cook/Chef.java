package com.academy.springbasicapp.cook;

public class Chef {
	//자바에서 특정 객체가 다른 객체를 부품으로 가질때 
	//has 어 관계가 하고, 이때 부품이 되는 객체는
	//멤버변수로 선언
	//아래와 같이 has 관계로 특정 객체를 보유할때, 너무 정확한 자료형을 보유하면,
	//유지 보수성이 떨어진다.-> 해결책 보다 상위 자료형으로 유연한 방법을 이용
	//이유? 상위 자료형일 수록 많은 자료형들을 가리킬 수 있으므로 
	private Pan pan; //chef has a Fripan
	
//	이 setter는 스프링 컨테이너가 인스턴스를 전달할 때 해당 인스턴스를 받기 위한 setter이다. 
//	이와 같이 개발자가 해받ㅇ 클래스에서 직접 객체의 인스턴스를 new 하지 아니하고, 스피일 컨테이너로 부터
//	즉 외부에서 객체의 인스턴스를 주입다는 개발기법을 DI(의존성을 약화시키기 위한 객체 외부 주입)라고 한다.
	public void setPan(Pan pan) {
		this.pan = pan;
	}
	
	public void cook() {
//		pan = new ElectPan(); new 연산자를 사용하면 new 뒤에 정확한 자료형을 명시해야 하므로, 
									//이 이유때문에 결합도를 낮추기 힘들어진다.
		
		pan.boil();
	}
	
}
