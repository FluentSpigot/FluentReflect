import io.github.jwdeveloper.reflect.implementation.FluentReflect;
import org.junit.jupiter.api.Test;

public class VersionCompererTests
{
    @Test
    public void ShouldDisplayConsoleForAllCases() throws Exception {
        var reflect = new FluentReflect("1.8.8");


        reflect.findClass().forVersionHigherThen("1.6.6",finder ->
        {
            System.out.println("A");
        });

        reflect.findClass().forVersionLowerThen("1.9.9",finder ->
        {
            System.out.println("B");
        });


        reflect.findClass().forVersion("1.8.8",finder ->
        {
            System.out.println("C");
        });

        reflect.findClass().forVersionRange("1.0.0","2.0.0",finder ->
        {
            System.out.println("D");
        });
    }

    @Test
    public void ShouldNotDisplayConsole() throws Exception {
        var reflect = new FluentReflect("1.0.0");


        reflect.findClass().forVersionHigherThen("1.6.6",finder ->
        {
            System.out.println("A");
        });

        reflect.findClass().forVersionLowerThen("0.9.9",finder ->
        {
            System.out.println("B");
        });


        reflect.findClass().forVersion("1.0.1",finder ->
        {
            System.out.println("C");
        });

        reflect.findClass().forVersionRange("1.0.1","2.0.0",finder ->
        {
            System.out.println("D");
        });
    }
}
