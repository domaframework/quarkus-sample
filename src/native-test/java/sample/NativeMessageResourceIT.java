package sample;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeMessageResourceIT extends MessageResourceTest {

    // Execute the same tests but in native mode.
}