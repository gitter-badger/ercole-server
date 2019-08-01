package io.ercole.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * Custom Controller for error handling.
 */
@RestController
public class CustomErrorController implements ErrorController {

	@Value("${server.error.path}")
	private static String errorPath;

	private final ErrorAttributes errorAttributes;

	/**
	 * @param errorAttr 
	 */
	@Autowired
	public CustomErrorController(final ErrorAttributes errorAttr) {
		this.errorAttributes = errorAttr;
	}

	/**
	 * @param request from front-end
	 * @return error attributes in json style
	 */
	@RequestMapping("${server.error.path}")
	public Map<String, Object> error(final WebRequest request) {
		return getErrorAttributes(request, false);
	}

	private Map<String, Object> getErrorAttributes(final WebRequest request, final boolean includeStacktrace) {
		return this.errorAttributes.getErrorAttributes(request, includeStacktrace);
	}

	@Override
	public final String getErrorPath() {
		return errorPath;
	}

}
