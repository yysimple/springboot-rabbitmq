package com.jxkj.config;

import com.jxkj.entity.Book;
import com.jxkj.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.EnumTranslationConfiguration;
import org.springframework.data.rest.core.config.MetadataConfiguration;
import org.springframework.data.rest.core.config.ProjectionDefinitionConfiguration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;


/**
 * 配置rest的一些基本属性，且java配置类的优先级要高于xml
 */
@Configuration
public class RestConfig{


    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurerAdapter() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                ProjectionDefinitionConfiguration projectionDefinitionConfiguration = new ProjectionDefinitionConfiguration();
                MetadataConfiguration metadataConfiguration = new MetadataConfiguration();
                EnumTranslationConfiguration enumTranslationConfiguration = new EnumTranslationConfiguration() {
                    @Override
                    public void setEnableDefaultTranslation(boolean enableDefaultTranslation) {

                    }

                    @Override
                    public void setParseEnumNameAsFallback(boolean parseEnumNameAsFallback) {

                    }
                };
                projectionDefinitionConfiguration.addProjection(BookService.class);
                config = new RepositoryRestConfiguration(projectionDefinitionConfiguration,metadataConfiguration,enumTranslationConfiguration);
                config.setBasePath("/api");
            }
        };
    }

}
