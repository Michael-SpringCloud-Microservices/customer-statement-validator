package com.rbank.customer.statement.swagger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Michael Philomin Raj
 *
 */

@Controller
@RequestMapping
public class SwaggerUIController {

	public static final String SWAGGER_URL = "/swagger";

	public static final String CONTEXT_ROOT = "/";

	@GetMapping(path={SWAGGER_URL,CONTEXT_ROOT})
	public String index() {
		return "redirect:/swagger-ui.html";
	}
}