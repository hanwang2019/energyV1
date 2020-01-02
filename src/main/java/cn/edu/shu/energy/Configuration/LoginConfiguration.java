package cn.edu.shu.energy.Configuration;

        import cn.edu.shu.energy.Interceptor.LoginInterceptor;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LoginConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).
                addPathPatterns("/**").
                excludePathPatterns("/css/**").
                excludePathPatterns("/js/**").
                excludePathPatterns("/i/**").
                excludePathPatterns("/fonts/**").
                excludePathPatterns("/login").
                excludePathPatterns("/toLogin").
                excludePathPatterns("/upload").
                excludePathPatterns("/api/**");
        super.addInterceptors(registry);
    }
}
