import io.github.jwdeveloper.reflect.implementation.FluentReflect;
import org.junit.jupiter.api.Test;

public class Tests {
    @Test
    public void ShouldFindClass() throws Exception {
        var reflect = new FluentReflect("1.8.8");
        var exampleClass = reflect.findClass()
                .forVersion("1.8.8", finder ->
                {
                    finder.withName("resources._v_1_0_0.ExampleClass");
                })
                .find();

        var publicMethod = exampleClass.findMethod()
                .forAnyVersion(finder ->
                {
                    finder.withType(String.class);
                    finder.withPublic();
                    finder.withName("publicStuff");
                    finder.withParameter(int.class, "a");
                    finder.withParameter(String.class, "b");
                    finder.withParameter(boolean.class, "c");
                })
                .find();

        var myStuffMethod = exampleClass.findField()
                .forAnyVersion(finder ->
                {
                    finder.withPrivate();
                    finder.withStatic();
                    finder.withType(Integer.class);
                    finder.withName("myValue");
                })
                .find();

        var myConstructor = exampleClass.findConstructor()
                .forAnyVersion(finder ->
                {
                    finder.withPrivate();
                    finder.withParameter(int.class, "a");
                    finder.withParameter(String.class, "casd");
                    finder.withParameterMatcher(input ->
                    {
                       return new Object[]{input[1],input[2]};
                    });
                })
                .find();

        var instance = myConstructor.newInstance(false,12,"siema");
//    private static Integer myValue = 10;
    }

    /*
     public String publicStuff(int a, String b, boolean c) {
        return "";
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
     */
}
