package resources._v_1_0_0;

public class ExampleClass extends ExampleClassParent implements ExampleClassInterface {

    private static Integer myValue = 10;


    public enum EnumInside
    {
        A,B
    }


    private ExampleClass(int a, String b)
    {

    }

    private ExampleClass(int a, String b, boolean c)
    {

    }


    private ExampleClass(int a, String b, boolean c, String d)
    {

    }


    public String publicStuff(int a, String b, boolean c) {
        return "it works";
    }

    private String privateStuff(int a, String b, boolean c) {
        return "";
    }

    protected String protectedStuff(int a, String b, boolean c) {
        return "";
    }

    protected String packagePrivateStuff(int a, String b, boolean c) {
        return "";
    }
}
