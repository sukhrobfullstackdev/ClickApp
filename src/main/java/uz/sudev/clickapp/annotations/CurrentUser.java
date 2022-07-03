package uz.sudev.clickapp.annotations;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER}) // PARAMETER - sifatida ishlashi uchun!
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal // manga ContextHolder dan kim kirganini bilip beradi!
public @interface CurrentUser {
}
