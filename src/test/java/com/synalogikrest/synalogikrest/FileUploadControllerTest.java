package com.synalogikrest.synalogikrest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class FileUploadControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    MockMultipartFile file
            = new MockMultipartFile(
                    "file",
                    "interview.txt",
                    "text/plain",
                    "I AM TESTING A FILE ".getBytes()
            );

    @Test
    public void whenFileUploaded_thenVerifyStatus()
            throws Exception {
        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/upload-file").file(file)).andExpect(status().isOk());

    }

    @Test
    public void whenFileUploaded_thenVerifyResult() throws Exception {
        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/upload-file").file(file));

        mockMvc.perform(multipart("/upload-file").file(file)).andExpect(status().isOk())
                .andExpect(content().json("[\"Word count 5\",\"Average word length 3.000\",\"Number of words of length 1 is 2\",\"Number of words of length 2 is 1\",\"Number of words of length 4 is 1\",\"Number of words of length 7 is 1\",\"The most frequently occuring word length is 2 for word lengths of 1\"]"));
    }

}
