package kr.co.itcen.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import kr.co.itcen.config.web.FileuploadConfig;
import kr.co.itcen.config.web.MVCConfig;
import kr.co.itcen.config.web.MessageConfig;
import kr.co.itcen.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc // view resolver만들어줌
@ComponentScan({ "kr.co.itcen.jblog.controller" })
@Import({ MVCConfig.class, SecurityConfig.class, MessageConfig.class, FileuploadConfig.class })
public class WebConfig {

}
