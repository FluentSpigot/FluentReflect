import io.github.jwdeveloper.reflect.implementation.FluentReflect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MatchTests {


    @ParameterizedTest
    @ValueSource(strings = {"1.0.0", "1.0.1"})
    public void shouldGetParamsBaseOfVersion(String version) throws Exception {
        var reflect = new FluentReflect(version);


        var classModel = reflect.findClass()
                .forVersion("1.0.0", finder ->
                {
                    finder.withName("resources._v_1_0_0.ExampleClass");
                })
                .forVersion("1.0.1", finder ->
                {
                    finder.withName("resources._v_1_0_1.ExampleClass");
                })
                .find();

        var constructorModel = classModel.findConstructor()
                .forAnyVersion(finder ->
                {
                    finder.withPrivate();
                    finder.withParameter(int.class);
                    finder.withParameter(String.class);
                })
                .find();

        var constructorModelAnotherWay = classModel.findConstructor()
                .forAnyVersion(finder ->
                {
                    finder.withPrivate();
                    finder.withParameterCount(2);
                })
                .find();

        var instance = constructorModel.newInstance(1, "hello");
        Assertions.assertNotNull(instance);

        var method = classModel.findMethod()
                .forVersion("1.0.0", finder ->
                {
                    finder.withPublic();
                    finder.withName("publicStuff");
                })
                .forVersion("1.0.1", finder ->
                {
                    finder.withPublic();
                    finder.withName("publicStuffV1");
                    finder.withParameterMatcher(parameter ->
                    {
                        return new Object[]{parameter[0], parameter[1]};
                    });
                })
                .find();

        var field = classModel.findField()
                .forVersion("1.0.1", finder ->
                {
                    finder.withName("myValue");
                })
                .tryFind();


        var result = method.invoke(instance, 1, "Hello", false);
        if (field != null) {
            Assertions.assertEquals(Integer.class, field.getField().getType());

        }
        Assertions.assertEquals(result, "it works");
    }


}
