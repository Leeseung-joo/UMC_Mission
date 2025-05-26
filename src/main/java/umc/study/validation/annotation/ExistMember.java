package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import umc.study.validation.validator.MemberExistValidator;

@Documented
@Constraint(validatedBy = MemberExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME) //해당 어노테이션이 유지되는 시간으로써 런타임까지 유효함
public @interface ExistMember {
    String message() default "존재하지 않는 멤버입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
