package org.goodpeoplegoodtimes.controller;

import org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto;
import org.goodpeoplegoodtimes.service.PartyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(PartyController.class)
@MockBean(JpaMetamodelMappingContext.class)
class PartyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartyService partyService;

    @Test
    @DisplayName("[GET] 파티 리스트")
    @WithMockUser(username = "test", roles = "USER")
    public void getPartyList() throws Exception {
        // Given
        int page = 0;
        PageRequest pageable = PageRequest.of(page, 8);
        Page<PartyListResponseDto> partyPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

        given(partyService.getPartyList(pageable)).willReturn(partyPage);

        // When & Then
        mockMvc.perform(get("/party").param("page", String.valueOf(page)))
            .andExpect(status().isOk())
            .andExpect(view().name("party/party_list"))
            .andExpect(model().attributeExists("parties"));
    }

    @Test
    @DisplayName("[GET] 파티 생성 폼")
    @WithMockUser(username = "test", roles = "USER")
    public void testCreateParty() throws Exception {
        // When & Then
        mockMvc.perform(get("/party/create"))
            .andExpect(status().isOk())
            .andExpect(view().name("party/party_form"));
    }


}