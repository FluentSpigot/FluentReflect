package io.github.jwdeveloper.reflect;

import io.github.jwdeveloper.descrabble.Descrabble;
import io.github.jwdeveloper.descrabble.api.DescriptionGenerator;
import io.github.jwdeveloper.descrabble.github.DescrabbleGithub;
import io.github.jwdeveloper.descrabble.spigot.DescrabbleSpigot;

import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        var resource =  Main.class.getClassLoader().getResource("DocumentationTemplate.html");
        DescriptionGenerator descriptionGenerator = Descrabble.create()
                .withTemplate(resource.toURI())
                .withPlugin(DescrabbleSpigot.plugin())
                .withPlugin(DescrabbleGithub.plugin())
                .build();

        var path =  System.getProperty("user.dir");
        descriptionGenerator.generate(path);
    }
}