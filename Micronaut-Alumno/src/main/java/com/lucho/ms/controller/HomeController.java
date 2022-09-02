package com.lucho.ms.controller;

import com.lucho.ms.constants.AlumnoConstants;
import com.lucho.ms.util.BuildInfo;
import com.lucho.ms.util.BuildInfoMapper;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/")
public class HomeController {

	@Get(value = "/ping", produces = MediaType.TEXT_PLAIN)
	public HttpResponse<String>  ping() {
		return HttpResponse.status(HttpStatus.OK).body(AlumnoConstants.PING_MESSAGE);
	}
	@Get(value = "/version", produces = MediaType.APPLICATION_JSON)
	public HttpResponse<BuildInfo>  version() {
		BuildInfo buildInfo = BuildInfoMapper.getBuildInfo(AlumnoConstants.NAME_APP);
		return HttpResponse.status(HttpStatus.OK).body(buildInfo);
	}
}
