package org.finalproject;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class FlywayDeferrerPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        excludeDependsOnFlywayFromBean(beanFactory, DataSourceScriptDatabaseInitializer.class);
        excludeDependsOnFlywayFromBean(beanFactory, EntityManagerFactory.class);
    }

    private void excludeDependsOnFlywayFromBean(ConfigurableListableBeanFactory beanFactory, Class<?> beanClass) {
        Stream.of(beanFactory.getBeanNamesForType(beanClass))
                .map(beanFactory::getBeanDefinition)
                .filter(it -> it.getDependsOn() != null && it.getDependsOn().length > 0)
                .forEach(it -> it.setDependsOn(Stream.of(it.getDependsOn()).filter(name -> !name.equals("flyway")).toArray(String[]::new)));
    }
}