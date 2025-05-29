package umc.study.web.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;
import umc.study.domain.Gender;
import umc.study.domain.enums.Role;
import umc.study.validation.annotation.ExistCategories;

@Getter
@Setter
public class JoinDTO {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private Gender gender;
    @NotNull
    private String birthdate;
    @NotBlank
    private Point location;
    @ExistCategories
    private List<Long> preferFood;
    @NotNull
    Role role;



}
