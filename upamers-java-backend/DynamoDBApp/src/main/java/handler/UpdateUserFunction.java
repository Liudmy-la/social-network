package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import layer.service.APIGatewayService;
import layer.service.APIGatewayServiceImpl;
import layer.service.DynamoDBService;
import layer.service.DynamoDBServiceImpl;

public class UpdateUserFunction implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final DynamoDBService dynamoDBService = new DynamoDBServiceImpl();
    private static final APIGatewayService apiGatewayService = new APIGatewayServiceImpl();

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input,
                                                      final Context context) {
        try {            
            String userId = input.getPathParameters().get("userId"); 
            String requestBody = input.getBody();

            dynamoDBService.updateUser(userId, requestBody);

            return apiGatewayService.getApiGatewayProxyResponseEvent("User updated successfully", 200);
        } catch (Exception e) {
            return apiGatewayService.getApiGatewayProxyResponseEvent(
                    "An error occurred while updating the user: "
                            + e.getClass() + "; message: " + e.getMessage(),
                    503);
        }
    }
}