package util;

import beans.Config;
import beans.WebappConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext webApplicationContext =
                new AnnotationConfigWebApplicationContext();

        webApplicationContext.register(WebappConfig.class, Config.class);

        servletContext.addListener(new ContextLoaderListener(webApplicationContext));

        ServletRegistration.Dynamic dispatcher = servletContext
                .addServlet("dispatcher", new DispatcherServlet(webApplicationContext));

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
