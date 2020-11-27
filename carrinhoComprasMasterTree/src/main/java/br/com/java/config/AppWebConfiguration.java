package br.com.java.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import br.com.java.dao.ContaDAO;
import br.com.java.dao.ContaDAOImpl;
import br.com.java.dao.ProdutoDAO;
import br.com.java.dao.ProdutoDAOImpl;

@Configuration
@EnableWebMvc
@ComponentScan ({ "br.com.java","br.com.java.dao", "br.com.java.authentication.MyDBAuthenticationService" })
@PropertySource("classpath:ds-hibernate-cfg.properties")
public class AppWebConfiguration {
	
	@Autowired
	private Environment env;
	
	@Bean(name="AppWeb")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}
	
	// Configuração para Upload.
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();

		// Set Max Size...
		// commonsMultipartResolver.setMaxUploadSize(...);

		return commonsMultipartResolver;
	}
	
	 @Bean(name = "dataSource")
	    public DataSource getDataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	 
	        // See: ds-hibernate-cfg.properties
	        dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
	        dataSource.setUrl(env.getProperty("ds.url"));
	        dataSource.setUsername(env.getProperty("ds.username"));
	        dataSource.setPassword(env.getProperty("ds.password"));
	         
	        System.out.println("## getDataSource: " + dataSource);
	         
	        return dataSource;
	    }
	
	@Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
		
		
        Properties properties = new Properties();
 
        // See: ds-hibernate-cfg.properties
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
         
 
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
         
        // Package contain entity classes
        factoryBean.setPackagesToScan(new String[] { "br.com.java.entity" });
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(properties);
        factoryBean.afterPropertiesSet();
        //
        SessionFactory sf = factoryBean.getObject();
        System.out.println("## getSessionFactory: " + sf);
        return sf;
    }
	@Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
 
        return transactionManager;
    }
	@Bean(name = "dao")
    public ContaDAO getApplicantDAO() {
        return new ContaDAOImpl();
    }
	
	@Bean(name = "dao")
    public ContaDAO getContaDAO()  {
        return new ContaDAOImpl();
    }
	@Bean(name = "produtoDAO")
    public ProdutoDAO getProdutoDAO() {
        return new ProdutoDAOImpl();
    }

}