package fi.jpalomaki.template

import javax.servlet.ServletContext
import javax.servlet.ServletException
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

class WebAppInitializer implements WebApplicationInitializer {

    @Override
    def void onStartup(ServletContext servletContext) {
        doBindRootContextTo(servletContext)
        doBindMvcContextTo(servletContext)
    }
    
    def doBindRootContextTo(ServletContext servletContext) {
        def rootContext = new AnnotationConfigWebApplicationContext()
        rootContext.register(RootContext)
        servletContext.addListener(new ContextLoaderListener(rootContext))
    }
    
    def doBindMvcContextTo(ServletContext servletContext) {
        def mvcContext = new AnnotationConfigWebApplicationContext()
        mvcContext.register(MvcContext)
        def dispatcherServlet = servletContext.addServlet("mvc", new DispatcherServlet(mvcContext))
        dispatcherServlet.setLoadOnStartup(1)
        dispatcherServlet.addMapping("/")
    }
}
