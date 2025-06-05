package davi.api.demo.application.useCases;

import davi.api.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNull;

public class GetUserUseCaseTest {

    private UserRepository userRepository;
    private GetUserUseCase useCase;

    @BeforeEach
    public void beforeEach() {
        userRepository = Mockito.mock(UserRepository.class);
        useCase = new GetUserUseCase(userRepository);
    }

    @Test
    public void shouldReturnAnUser() {
        // Arrange
        var input = new GetUserUseCaseInput("123");
        Mockito.when(userRepository.findUserById(input.userId)).thenReturn(null);

        // Act
        var result = useCase.perform(input);

        // Assert
        assertNull(result);
    }
}
