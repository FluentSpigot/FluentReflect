package io.github.jwdeveloper.examples._1_0_0;

public class ExampleClass
{
    private final String name;

    public ExampleClass(String name)
    {
        this.name = name;
    }

    protected String helloWorld()
    {
       return "Hello world my name is "+name;
    }
}
