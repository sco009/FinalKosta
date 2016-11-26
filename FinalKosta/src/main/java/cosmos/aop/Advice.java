package cosmos.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class Advice {
	@Before("execution(* cosmos.subjective..*.*Controller.*(..))")
	public void before(){
		System.out.println("aop실행");
	}
}
