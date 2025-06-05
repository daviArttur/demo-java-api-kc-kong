package davi.api.demo.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.Map;

public abstract class BaseController {

    @Autowired
    protected HttpServletRequest request;
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    public String getUserIdFromAccessToken() {
        var authHeader = request.getHeader("Authorization");
        var token = authHeader.substring(7);
        var claims = getTokenClaims(token);
        return (String) claims.get("UserId");
    }

    private Map getTokenClaims(String token) {
        try {
            String[] parts = token.split("\\.");
            var tokenPayload = parts[1];
            String payload = new String(Base64.getUrlDecoder().decode(tokenPayload));
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(payload, Map.class);
        } catch (Exception e) {
            logger.error("Error decoding access token: {} message: {}", token, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
