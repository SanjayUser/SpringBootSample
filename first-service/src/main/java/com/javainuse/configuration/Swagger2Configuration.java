package com.javainuse.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//import java.lang.String.format;

@Configuration
public class Swagger2Configuration {
	
	private String projectBasePath = "";
	private String path;
	private String controllersPackage = "com.javainuse.swaggertest";
	private String projectVersion = "1";
	
//    @Bean
//    public Docket api() { 
//        return new Docket(DocumentationType.SWAGGER_2)  
//          .select()                                  
//          .apis(RequestHandlerSelectors.any())              
//          .paths(PathSelectors.any())                          
//          .build();                                           
//    }
    
    @Bean
    public Docket getFolderManagementController() { 
        return buildSwaggerDoclet(projectBasePath, path, projectVersion, controllersPackage)
        		.globalOperationParameters(operationParameters());                                          
    }

	private List<Parameter> operationParameters() {
		List<Parameter> headers = new ArrayList<>();
		headers.add(new ParameterBuilder().name("first-request").description("first")
				.modelRef(new ModelRef("String")).parameterType("header").required(true).build());
		return headers;
	}

	private Docket buildSwaggerDoclet(String projectBasePath2, String path2, String projectVersion2,
			String controllersPackage2) {
		//import java.lang.String.format;
		//String projectpath = format("/%s/%s%s", projectBasePath2, projectVersion2, path2);
		return new Docket(DocumentationType.SWAGGER_2)  
		          .groupName(projectBasePath2).select()                                 
		          .apis(RequestHandlerSelectors.basePackage(controllersPackage2))              
		          .paths(PathSelectors.any())                          
		          .build()
		          .apiInfo(new ApiInfoBuilder().version(projectVersion)
		        		  .title(projectBasePath + " API")
		        		  .description("sample project")
		        		  .build());
	}
}
