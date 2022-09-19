Este proyecto de spring boot cuanta con los siguientes caracteristicas
Java version = 11
Spring boot version = 2.7.3
Spring cloud version = 2021.0.3
*********SWAGGER**************************
cuenta con swagger que da ciertos problemas con la version, 
se configuro de la siguiente forma:
  <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-boot-starter</artifactId>
        <version>3.0.0</version>
    </dependency>
en la configuracion ya no hace falta el @EnableSwagger2
se tiene que configurar lo siguiente para que no de error al levantar el proyecto
 poner esto en el yamel mvc.pathmatch.matching-strategy: ANT_PATH_MATCHER
 las api que expone son:
 http://url:puerto/context-path/swagger-ui/index.html
 para limitar los endpoint a mostrar 
   public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo())  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.examenes"))     ---> limito solo a ese paquete         
          .build();                                           
    }

para no tener problemas con actuator hay que crear este bean en el archivo de configuracion de actuator
@Bean
	public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
		List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
		Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
		allEndpoints.addAll(webEndpoints);
		allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
		allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
		String basePath = webEndpointProperties.getBasePath();
		EndpointMapping endpointMapping = new EndpointMapping(basePath);
		boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
		return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
	}

*******************************************
Actuator
Framework que nos permite obtener metricas de mi endpoint
para configurarlo
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		si queremos podemos incluir ciertas caracteristicas que queremos monitorear
		management:
    endpoints:
      web:
        exposure:
          include: '*'    //o por ejemplo prometheus,healph info, metrics etc
        base-path: /manage
        URl para chequear las metricas si se definio un context-path examenes y un path para actuator = manage
        
http://localhost:9325/examenes/manage/beans  ---->metricas de beans
    para poder crear metricas propias debemoa agregar la siguiente configuracion
       <dependency>
  <groupId>io.micrometer</groupId>
  <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
por ejemplo si quremos monitorizar el metodo ping del controller homecontroller
debemos anotar este metodo con la siguiente anotacion
	@Timed(value = "getPing")
	entonces si vamos a la url 
http://localhost:9325/examenes/manage/metrics/getPing --> nombre definido con @Timed podemos ver metricas asociada a este metodo como 
cantidad de veces que fue llamado, tiempo de respuesta etc.

*******************************************PROMETHEUS
    metrics.export.prometheus.enabled: true
    endpoint:
      prometheus:
        enabled: true

url para poder ver las estadisticas 
http://localhost:9325/examenes/manage/prometheus


debemos instalar prometheus para eso dockerizamos prometheus

creamos un archivo
'prometheus.yml' con la siguiente configuracion:

global:
  scrape_interval: 5s
  evaluation_interval: 15s
rule_files:
scrape_configs:
  - job_name: 'prometheus'
    static_configs:
    - targets: ['localhost:9325']    ----> si estoy corriendo en un entorno localhost debo tener cuidado ya que si prometheus esta corriendo dockerizado
    no voy a tener acceso desde localhost y tengo que usar 172.0.0.1 que es la direccion desde un docker para acceder al host padre 
    
    docker run -d --name prometheus -p 9090:9090 -p 10087:10087 -v /PATHDONDEGUARDASprometheus.yml/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml
para entrar a prometheus http://localhost:9090/graph?g0.expr=&g0.tab=1&g0.stacked=0&g0.show_exemplars=0&g0.range_input=1h

para inicializar grafana 

lanzar docker con docker run -d -p 3000:3000 grafana/grafana-enterprise
conectar con prometheus ---OJO si lo corres en localhost a prometheus debes poner como direccion donde escucha grafana la direccion Ip de grafana, pero no localhost  
despues poner algunas de las metricas que nos da prometheus y podes probar

