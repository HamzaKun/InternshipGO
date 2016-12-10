package com.internshipgo.view;


import org.springframework.beans.BeanUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by hamza on 10/12/16.
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String first;
    private String second;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        first = constraintAnnotation.first();
        second = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Object firstObj = null;
            //firstObj = BeanUtils.getProperty(value, first);
            final Object secondObj = null;
            //secondObj = BeanUtils.getProperty(value, second);

            boolean isValid = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);

            if (!isValid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addPropertyNode(second).addConstraintViolation();
            }

            return isValid;
        }
        catch (final Exception ignore) {
            // ignore
        }
        return true;
    }
}