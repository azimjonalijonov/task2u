//package com.task02;
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//import com.syndicate.deployment.annotations.lambda.LambdaHandler;
//import com.syndicate.deployment.model.RetentionSetting;
//import com.syndicate.deployment.annotations.lambda.LambdaUrlConfig;
//
//import java.util.HashMap;
//import java.util.Map;
//@LambdaHandler(lambdaName = "hello_world",
//		roleName = "hello_world-role",
//		isPublishVersion = true,
//		aliasName = "learn",
//
//		logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
//)
//@LambdaUrlConfig
//public class HelloWorld implements RequestHandler<Object, Map<String, Object>> {
//	public Map<String, Object> handleRequest(Object request, Context context) {
//		System.out.println("Hello from lambda");
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("statusCode", 200);
//		resultMap.put("message", "Hello from Lambda");
//		resultMap.put("body", "{\n" +
//				" \"statusCode\": 200,\n" +
//				" \"message\": \"Hello from Lambda\"\n" +
//				" }");
//		return resultMap;
//	}
//}
package com.task02;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.model.RetentionSetting;
import com.syndicate.deployment.annotations.lambda.LambdaUrlConfig;

import java.util.HashMap;
import java.util.Map;

@LambdaHandler(lambdaName = "hello_world",
        roleName = "hello_world-role",
        isPublishVersion = true,
        aliasName = "learn",
        logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
@LambdaUrlConfig
public class HelloWorld implements RequestHandler<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> handleRequest(Map<String, Object> request, Context context) {
        System.out.println("Processing request in Lambda function");

        Map<String, Object> resultMap = new HashMap<>();
        String path = (String) request.get("path");
        String method = (String) request.get("httpMethod");
        boolean bol =true;

        if (bol) {
            // Correct endpoint and method
            resultMap.put("statusCode", 200);
            resultMap.put("message", "Hello from Lambda");
            resultMap.put("body", "{\n" +
                    " \"statusCode\": 200,\n" +
                    " \"message\": \"Hello from Lambda\"\n" +
                    " }");
            bol =false;
        } else {
            // Incorrect endpoint or method
            resultMap.put("statusCode", 400);
            resultMap.put("message", String.format(
                    "Bad request syntax or unsupported method. Request path: %s. HTTP method: %s",
                    path != null ? path : "unknown",
                    method != null ? method : "unknown"));
            bol=true;
        }

        return resultMap;
    }
}

