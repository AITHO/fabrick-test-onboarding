package it.aitho.fabrickonboarding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.aitho.fabrickonboarding.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FabrickAccountsController.class)
class FabrickAccountsControllerTest {/* TODO
    @Autowired
    private MockMvc mockMvc;

*/
}