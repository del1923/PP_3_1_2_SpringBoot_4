package web.PP_31._SpringBoot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import web.PP_31._SpringBoot.model.User;
import web.PP_31._SpringBoot.service.UserServices;

@Component
public class UserValidator implements Validator {

    private final UserServices userServices;

    @Autowired
    public UserValidator(UserServices userServices) {
        this.userServices = userServices;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target; //даункастим к Юзеру
        if ((userServices.showByEMail(user.getEmail())).isPresent()) {
            errors.rejectValue("email", "", "e-mail уже используется");
        }
    }
}
