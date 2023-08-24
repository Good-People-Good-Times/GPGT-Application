package org.goodpeoplegoodtimes.controller;
import org.goodpeoplegoodtimes.domain.dto.auth.SignupForm;
import org.goodpeoplegoodtimes.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(AuthController.class)
class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    MemberService memberService;
    @Test
    @DisplayName("[GET] 로그인 페이지")
    public void loginPageTest() throws Exception {
        mockMvc.perform(get("/auth/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/login_page"));
    }
    @Test
    @DisplayName("[GET] 로그인 - 에러 발생")
    public void loginPageErrorTest() throws Exception {
        mockMvc.perform(get("/auth/login").param("error", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/login_page"))
                .andExpect(model().attributeExists("errorMsg"));
    }
    @Test
    @DisplayName("[GET] 회원가입 페이지")
    public void singupPageTest() throws Exception {
        mockMvc.perform(get("/auth/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/signup_page"))
                .andExpect(model().attributeExists("form"));
    }
    @Test
    @DisplayName("[POST] 회원가입 페이지 - 회원가입 성공")
    public void signupSuccessTest() throws Exception {

//        SignupForm validSignupForm = new SignupForm(
//            "email@email.com", "12341234", "tester", 1);
//
//        // given
//        given(memberService.save(any(SignupForm.class))).willReturn(1L);
//
//        mockMvc.perform(post("/auth/signup")
//                .flashAttr("signupForm", validSignupForm))
//            .andExpect(status().is3xxRedirection())
//            .andExpect(redirectedUrl("/auth/login"));
    }

    @Test
    @DisplayName("[POST] 회원가입 페이지 - 유효성 검증 실패")
    public void signupValidationFailureTest() throws Exception {

//        SignupForm invalidSignupForm = new SignupForm(
//            "", // 이메일이 비어있음
//            "1234", // 비밀번호가 너무 짧음
//            "", // 이름이 비어있음
//            0); // 나이가 0
//
//        mockMvc.perform(post("/auth/signup")
//                .flashAttr("signupForm", invalidSignupForm))
//            .andExpect(status().isOk())
//            .andExpect(view().name("auth/signup_page"))
//            .andExpect(model().attributeExists("errorMsg", "form"));
    }

    @Test
    @DisplayName("[GET] 이메일 찾기 페이지")
    public void findEmailPageTest() throws Exception {
        mockMvc.perform(get("/auth/find/email/"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/find/find_email_page"));
    }
    @Test
    @DisplayName("[GET] 비밀번호 찾기 페이지")
    public void resetPasswordPageTest() throws Exception {
        mockMvc.perform(get("/auth/reset/password"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/reset/reset_pw_page"));
    }
}