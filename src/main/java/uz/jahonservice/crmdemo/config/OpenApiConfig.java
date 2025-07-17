package uz.jahonservice.crmdemo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Jahongir",
                        email = "jahongirjuraqulov5787@gmail.com",
                        url = "https://t.me/jahongirjuraqulov5787"
                ),
                description = "CRM-demo",
                title = "Kuchli kuchli gaplardan uylab turing og`a :)",
                version = "1.0 - demo"
//                license = @License(
//                        name = "litsenziyamiz yuq umman"
//                ),
//                termsOfService = "xizmat kursatish shartlari"
        )
)
public class OpenApiConfig {
}
