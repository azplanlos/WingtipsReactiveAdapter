# WingtipsReactiveAdapter
Adapt Wingtips static tracing helpers to RxJava2

## Usage

Maven users please include the following dependency:

    <dependency>
       <groupId>com.github.azplanlos</groupId>
       <artifactId>WingtipsReactiveAdapter</artifactId>
       <version>1.1</version>
    </dependency>

Please add the jitpack repository to download the correct dependency:

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

Wingtips and rxjava2 Dependencies are now optional, make sure to include the wingtips version you intend to use by adding 
the 
following dependencies to your pom.xml:

    <dependencies>
        <dependency>
            <groupId>com.nike.wingtips</groupId>
            <artifactId>wingtips-core</artifactId>
            <version>0.24.2</version>
        </dependency>
        <dependency>
            <groupId>com.nike.wingtips</groupId>
            <artifactId>wingtips-java8</artifactId>
            <version>0.24.2</version>
        </dependency>
        <dependency>
            <groupId>io.reactivex.rxjava2</groupId>
            <artifactId>rxjava</artifactId>
            <version>2.2.21</version>
        </dependency>
    </dependencies>

You can now use two new .withTracing() methods with Lambda parameters:

    <T> ReactiveConsumerWithTracing<T> withTracing(java.util.function.Consumer<T> origConsumer)

    <T, U> ReactiveFunctionWithTracing<T, U> withTracing(java.util.function.Function<T, U> origFunction)

or if you are using rxjava3:

    <T> Reactive3ConsumerWithTracing<T> withTracing(java.util.function.Consumer<T> origConsumer)

    <T, U> Reactive3FunctionWithTracing<T, U> withTracing(java.util.function.Function<T, U> origFunction)