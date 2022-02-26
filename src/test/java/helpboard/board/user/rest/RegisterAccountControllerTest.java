package helpboard.board.user.rest;

import helpboard.board.user.domain.User;
import helpboard.board.user.service.RegisterAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegisterAccountController.class)
class RegisterAccountControllerTest {

    @MockBean
    RegisterAccountService registerAccountService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void registersAccount() throws Exception {
        // given
        var user = User.create("any_username");
        when(registerAccountService.register("any_username", "any_password")).thenReturn(user);

        // when & then
        var payload = "{\"username\": \"any_username\", \"password\": \"any_password\"}";
        mockMvc.perform(post("/user/register").contentType("application/json").content(payload))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(user.getId().toString()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
        ;
    }
}
