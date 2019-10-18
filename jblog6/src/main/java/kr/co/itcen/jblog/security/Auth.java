package kr.co.itcen.jblog.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//source로 하면 컴파일러가 컴파일 하면서 떼버림 에러남 runtime으로 하면 컴파일러가 신경 안씀
public @interface Auth {//어노테이션 인터페이스는 앞에 골뱅이를 붙여야함
	
	
}
