package org.goodpeoplegoodtimes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.dto.party.request.PartyForm;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto;
import org.goodpeoplegoodtimes.service.PartyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/party")
public class PartyController {

    private final PartyService partyService;

    private Pageable pageable;

    @GetMapping({"", "/search"})
    public String displayPartyList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "cond", required = false) String cond,
            @RequestParam(value = "category", required = false) Category category,
            Model model) {

        pageable = PageRequest.of(page, 8);
        Page<PartyListResponseDto> partyPage = getPartyList(cond, category, pageable);

        model.addAttribute("parties", partyPage.getContent());
        model.addAttribute("totalPages", partyPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "party/party_list";
    }

    @GetMapping(value = "/create")
    public String displayPartyCreationForm(Model model) {
        model.addAttribute("partyForm", new PartyForm());
        return "party/party_form";
    }

    @PostMapping(value = "/create")
    public String createParty(PartyForm partyForm, Authentication authentication) {
        Long savedId = partyService.createParty(partyForm, authentication);
        return "redirect:/party/" + savedId;
    }

    @GetMapping(value = "/{id}")
    public String displayPartyDetailPage(@PathVariable("id") String id, Model model) {
        model.addAttribute("detail", partyService.findPartyDetailById(Long.parseLong(id)));
        return "party/party_detail";
    }

    @GetMapping(value = "/scrap")
    public String displayScrapPartyPage() {
        return "scrap/scrap";
    }

    @GetMapping("/my-party")
    public String displayMyPartyList() {
        return "party/myparty/my-party";
    }

    @GetMapping("/party-change")
    public String displayPartyModificationPage() {
        return "party_change";
    }

    @GetMapping("/party-post")
    public String displayPartyPostPage() {
        return "party_create";
    }

    private Page<PartyListResponseDto> getPartyList(String cond, Category category, Pageable pageable) {
        if (cond != null && category == null) return partyService.getPartyList(cond, pageable);
        else if (cond == null & category != null) return partyService.getPartyList(category, pageable);
        else return partyService.getPartyList(pageable);
    }
}
