package com.internshipgo.view;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by hamza on 10/12/16.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {
    String first();
    String second();
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
