package com.example.pocwiremock;

import com.example.pocwiremock.domain.Foo;
import com.example.pocwiremock.resource.FooController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class FooWireMockTest {

    @InjectMocks
    private FooController fooResource;

    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8081).httpsPort(8443));

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(fooResource).build();
    }

    @Test
    public void whenAccessTheEndpointFoo_shouldReturnListOfFoo() throws Exception {
        stubFor(WireMock.get(urlEqualTo("/foo")).willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(LIST_OF_FOO_MOCK)));

        String responseMock = mockMvc.perform(get("/foo").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        TypeReference<List<Foo>> mapType = new TypeReference<List<Foo>>() {};
        List<Foo> listOfFoo = mapper.readValue(responseMock, mapType);
        assertEquals(2, listOfFoo.size());
    }

    @Test
    public void whenAccessTheEndpointFooFromRestTemplate_shouldReturnListOfFoo() throws Exception {
        stubFor(WireMock.get(urlEqualTo("/foo")).willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(LIST_OF_FOO_MOCK)));

        String responseMock = mockMvc.perform(get("/rest-foo").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        TypeReference<List<Foo>> mapType = new TypeReference<List<Foo>>() {};
        List<Foo> listOfFoo = mapper.readValue(responseMock, mapType);
        assertEquals(2, listOfFoo.size());
    }

    @Test
    public void whenAccessTheEndpointFooWithParams_shouldReturnListOfFoo() throws Exception {
        stubFor(WireMock.get(urlEqualTo("/foo/1")).willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", "application/json")
                .withBody(LIST_OF_FOO_MOCK)));

        String responseMock = mockMvc.perform(get("/rest-foo-params/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        TypeReference<List<Foo>> mapType = new TypeReference<List<Foo>>() {};
        List<Foo> listOfFoo = mapper.readValue(responseMock, mapType);
        assertEquals(2, listOfFoo.size());
    }

    @Test
    public void whenAccessOtherTheEndpointFoo_shouldReturnListOfFoo() throws Exception {
        stubFor(WireMock.get(urlEqualTo("/bar")).willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(LIST_OF_FOO_MOCK)));

        String responseMock = mockMvc.perform(get("/bar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        TypeReference<List<Foo>> mapType = new TypeReference<List<Foo>>() {};
        List<Foo> listOfFoo = mapper.readValue(responseMock, mapType);
        assertEquals(2, listOfFoo.size());
    }

    @Test
    public void whenAccessCreateEndpointFoo_shouldReturnAFoo() throws Exception {
        stubFor(WireMock.post(urlEqualTo("/create")).willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(FOO_MOCK)));

        String responseMock = mockMvc.perform(MockMvcRequestBuilders.post("/create-foo")
                .content(mapper.writeValueAsBytes(new Foo("fake_value")))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Foo fooReturned = mapper.readValue(responseMock, Foo.class);
        assertEquals((Integer)1, fooReturned.getId());
        assertEquals("123", fooReturned.getBar());
    }

    /**
     * mocks of response
     */
    private String LIST_OF_FOO_MOCK = "[\n" +
            "    {\n" +
            "        \"bar\": \"1\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"bar\": \"2\"\n" +
            "    }\n" +
            "]";

    private String FOO_MOCK = "{\n" +
            "\t\"id\": 1,\n" +
            "\t\"bar\": \"123\"\n" +
            "}";
}
