package client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.johan.client.consumer"}) // Is required in order to pass an instance of ConsoleConsumer to Application
public class ClientApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class);
    }

    @Override
    public void run(String... args) throws Exception {

        var input = new Input();

        new Application(
                new ApacheKafkaAPI(),
                input,
                new ReportDTOHandler(input),
                new ConsoleConsumer(),
                new MongoAdminReportHandler(input, new MongoAdminAPI()));
    }
}
