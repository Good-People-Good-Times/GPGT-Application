package org.goodpeoplegoodtimes.controller;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto;
import org.goodpeoplegoodtimes.service.PartyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/party")
public class PartyController {

    private final PartyService partyService;

    @GetMapping("/party_list")
    public String getPartyList(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {

        PageRequest pageable = PageRequest.of(page, 8);
        Page<PartyListResponseDto> partyPage = partyService.getPartyList(pageable);

        model.addAttribute("parties", partyPage.getContent());
        model.addAttribute("totalPages", partyPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "party/party_list";
    }

    @GetMapping(value = "/party_form")
    public String createParty() {
        return "party/party_form";
    }

    @GetMapping("/my-party")
    public String MyPartyList() {
        return "party/myparty/my-party";
    }

    @GetMapping("/party-change")
    public String changeParty() {
        return "party/change/party-change";
    }
}