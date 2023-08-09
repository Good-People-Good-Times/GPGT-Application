package org.goodpeoplegoodtimes.controller;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.repository.PartyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/party")
public class PartyController {

    private final PartyRepository PartyRepository;

    @GetMapping(value = "/party_list")
    public String Party() {
        return "party/party_list";
    }

    @GetMapping(value = "/party_form")
    public String makeParty() {
        return "party/party_form";
    }

}