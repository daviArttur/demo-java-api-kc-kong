package davi.api.demo.api.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BaseControllerImplementation extends BaseController {}

public class BaseControllerTest {
    private HttpServletRequest httpServletRequestMock;
    private final String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE3NDkwNzgwMjMsImV4cCI6MTc4MDYxNDAyMywiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXSwiVXNlcklkIjoiMTIzMTIzMTIzIn0.EFAqOwmFS6jOtpw8VTl59NgfiTEH3OTsdLXhQS8lXPM";
    private final String userIdInAccessToken = "123123123";
    private BaseController baseController;

    @BeforeEach
    public void beforeEach() {
        httpServletRequestMock = Mockito.mock(HttpServletRequest.class);
        baseController = new BaseControllerImplementation();
        baseController.request = httpServletRequestMock;
    }

    @Test
    public void shouldRetrieveUserIdFromAccessToken() {
        // Arrange
        Mockito.when(httpServletRequestMock.getHeader("Authorization")).thenReturn("Bearer " + accessToken);

        // Act
        var result = baseController.getUserIdFromAccessToken();

        // Assert
        assertThat(result).isEqualTo(userIdInAccessToken);
    }

    @Test
    public void shouldThrowRuntimeExceptionBecauseTokenFormatIsInvalid() {
        // Arrange
        Mockito.when(httpServletRequestMock.getHeader("Authorization")).thenReturn("Bearer break.token." + accessToken);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> baseController.getUserIdFromAccessToken());
    }
}
