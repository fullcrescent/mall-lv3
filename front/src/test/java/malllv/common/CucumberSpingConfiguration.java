package malllv.common;

import io.cucumber.spring.CucumberContextConfiguration;
import malllv.FrontApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { FrontApplication.class })
public class CucumberSpingConfiguration {}
