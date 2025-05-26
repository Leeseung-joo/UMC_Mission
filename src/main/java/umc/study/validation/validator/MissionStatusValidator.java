package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.validation.annotation.ValidMissionStatus;

public class MissionStatusValidator implements ConstraintValidator<ValidMissionStatus, String> {



    @Override
    public void initialize(ValidMissionStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;

        if (value != "IN_PROGRESS") {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.INVALID_STATUS.toString())
                    .addConstraintViolation();
            return false;
        }

        return true;
    }




    }

