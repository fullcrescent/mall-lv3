package malllv.common;

import io.cucumber.spring.CucumberContextConfiguration;
import malllv.ShopkeeperApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { ShopkeeperApplication.class })
public class CucumberSpingConfiguration {}
