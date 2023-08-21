package org.goodpeoplegoodtimes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.dto.party.request.PartyForm;
import org.goodpeoplegoodtimes.domain.dto.party.request.PartyUpdateForm;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyDetailResponseDto;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto;
import org.goodpeoplegoodtimes.service.CommentsService;
import org.goodpeoplegoodtimes.service.PartyMemberService;
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

    private static final int PAGE_SIZE = 8;
    private static final String REDIRECT_TO_PARTY = "redirect:/party/";

    private final PartyService partyService;
    private final PartyMemberService partyMemberService;
    private final CommentsService commentsService;

    @GetMapping({"", "/search"})
    public String displayPartyList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "cond", required = false) String cond,
            @RequestParam(value = "category", required = false) Category category,
            Model model) {

        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<PartyListResponseDto> partyPage = partyService.getPartyList(cond, category, pageable);

        model.addAttribute("parties", partyPage.getContent());
        model.addAttribute("totalPages", partyPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("categories", Category.values());
        return "party/party_list";
    }

    @GetMapping(value = "/create")
    public String displayPartyCreationForm(Model model) {
        model.addAttribute("partyForm", new PartyForm());
        return "party/party_form";
    }

    @PostMapping(value = "/create")
    public String createParty(PartyForm partyForm, Authentication authentication) {
        validatePartyForm(partyForm);
        Long savedId = partyService.createParty(partyForm, authentication);
        return REDIRECT_TO_PARTY + savedId;
    }

    @GetMapping(value = "/{partyId}")
    public String displayPartyDetail(@PathVariable Long partyId, Model model, Authentication authentication) {
        model.addAttribute("alreadyPartyJoinApply", partyService.isAlreadyPartyJoinApply(authentication.getName(), partyId));
        model.addAttribute("isOwner", partyService.isOwnerForParty(partyId, authentication.getName()));
        model.addAttribute("applicants", partyMemberService.listPartyMember(partyId));
        model.addAttribute("comments", commentsService.listComments(partyId));
        model.addAttribute("detail", partyService.findPartyDetailById(partyId));
        return "party/party_detail";
    }

    @PostMapping(value = "{partyId}/join/accept")
    public String acceptPartyMember(@PathVariable Long partyId, Long targetId, Authentication authentication) {
        partyMemberService.acceptPartyMember(partyId, targetId, authentication.getName());
        return REDIRECT_TO_PARTY + partyId;
    }

    @PostMapping(value = "{partyId}/join/deny")
    public String denyPartyMember(@PathVariable Long partyId, Long targetId, Authentication authentication) {
        partyMemberService.deletePartyMember(partyId, targetId, authentication.getName());
        return REDIRECT_TO_PARTY + partyId;
    }

    @GetMapping("/my")
    public String displayMyPartyList(@RequestParam(value = "cond", required = false) String cond,
                                     Authentication authentication, Model model) {

        model.addAttribute("myPartyList", partyService.getMyPartyList(authentication.getName(), cond));
        return "party/myparty/my_party_temp";
    }

    @GetMapping(value = "/scrap")
    public String displayScrapPartyPage() {
        return "scrap/scrap";
    }

    @GetMapping("/party-change")
    public String displayPartyModificationPage() {
        return "party/change/party_change";
    }

    @PostMapping(value = "/join/{partyId}")
    public String joinParty(@PathVariable Long partyId, Authentication authentication) {
        partyMemberService.joinParty(partyId, authentication.getName());
        return REDIRECT_TO_PARTY + partyId;
    }

    @GetMapping(value = "/{partyId}/update")
    public String updateParty(@PathVariable("partyId") Long partyId,
                              Authentication authentication, Model model) {

        if (!partyService.isOwnerForParty(partyId, authentication.getName())) {
            return REDIRECT_TO_PARTY;
        }

        PartyDetailResponseDto detail = partyService.findPartyDetailById(partyId);
        model.addAttribute("partyUpdateForm", PartyUpdateForm.builder()
            .partyId(detail.getPartyId())
            .title(detail.getTitle())
            .content(detail.getContent())
            .category(detail.getCategory())
            .build());

        return "party/party_form_update";
    }

    @PostMapping(value = "/{partyId}/update")
    public String updateParty(@PathVariable("partyId") Long partyId, PartyUpdateForm updateForm,
                              Authentication authentication) {

        if (!partyService.isOwnerForParty(partyId, authentication.getName())) {
            return REDIRECT_TO_PARTY;
        }

        partyService.update(updateForm);
        return REDIRECT_TO_PARTY + partyId;
    }

    @GetMapping(value = "/{partyId}/delete")
    public String deleteParty(@PathVariable("partyId") Long partyId, Authentication authentication) {
        if (!partyService.isOwnerForParty(partyId, authentication.getName())) {
            return REDIRECT_TO_PARTY;
        }

        partyService.delete(partyId);
        return REDIRECT_TO_PARTY;
    }

    private void validatePartyForm(PartyForm partyForm) {
        // Add validation logic here
    }
}
