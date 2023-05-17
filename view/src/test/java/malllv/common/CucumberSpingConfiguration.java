package malllv.common;

import io.cucumber.spring.CucumberContextConfiguration;
import malllv.ViewApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { ViewApplication.class })
public class CucumberSpingConfiguration {}
