package cn.edu.cqupt.wyglzx.configuration;

import cn.edu.cqupt.wyglzx.interceptor.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  @Autowired
  SecurityInterceptor securityInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(securityInterceptor).addPathPatterns("/**");
  }
}